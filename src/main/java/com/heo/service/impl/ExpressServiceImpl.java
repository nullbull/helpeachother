package com.heo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.heo.common.constant.Constants;
import com.heo.common.redis.ERedisKey;
import com.heo.common.utils.StringUtils;
import com.heo.common.utils.security.ShiroUtils;
import com.heo.entity.dto.ExpressAndNameDTO;
import com.heo.entity.dto.ExpressQueryDTO;
import com.heo.entity.mapper.Express;
import com.heo.entity.mapper.ExpressExample;
import com.heo.entity.vo.ExpressVO;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IExpressService;
//import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.Configuration;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Auth justinniu
 * @Date 2018/9/17
 * @Desc
 */
@Service
public class ExpressServiceImpl extends BaseService implements IExpressService {

    private Logger logger = LoggerFactory.getLogger(ExpressServiceImpl.class);
    private static Random random = new Random(47);
    private String CACHE_VALUE = "OK";
    private String CACHE_KEY =  "AlreadyCache";
    private int pageSize = 10;
    @Override
    public ReturnData createExpress(Express express) {
        ReturnData rd = getReturnData();
        String methoDesc = "创建代送快递信息";
        try {
            express.setUserId(ShiroUtils.getUserId());
            express.setExpressStatus(Constants.ORDER_NEW);
            express.setPhone(ShiroUtils.getUser().getPhone());
            if (StringUtils.isEmpty(express.getPhone()) || null == express.getLocationId() || null == express.getUserId() || null == express.getPrice() || null == express.getExpressType() || null == express.getGetCode()) {
                rd.setMsg("关键信息不能为空");
                logger.info(methoDesc + "失败 关键信息不能为空 Express:{}", express);
                return rd;
            }
            if (!valid(express)) {
                rd.setMsg("该快递信息已被提交");
                logger.info(methoDesc + "失败，该快递信息已被提交 Express:{}", express);
                return rd;
            }
            express.setCreatedAt(new Date());
            express.setUpdatedAt(new Date());
            expressMapper.insertSelective(express);
            rd.setMsg("成功");
            rd.setCode(Constants.SUCCESS_CODE);
            logger.info(methoDesc + "成功");
        }catch (Exception e) {
            rd.setMsg("未知错误");
            logger.error(methoDesc + "未知异常， e:{}", e);
        }
        return rd;
    }

    @Override
    public ReturnData getExpressList(@NotNull ExpressQueryDTO expressQureyDTO) {
        String methodDesc = "分页查询Express列表";
        ReturnData rd = getReturnData();
        int limit = 1;

        try {
            logger.info(methodDesc + "开始>>>>>>>>>> expressQueryDTO:{}", expressQureyDTO);
            if (null != expressQureyDTO.getLimit()) {
                limit = expressQureyDTO.getLimit();
            }
            PageHelper.startPage(limit, pageSize);
            List<ExpressAndNameDTO> expressList = expressMapper.selectByExpressQureyDTO(expressQureyDTO);
            List<ExpressVO> expressVOList = expressList.stream().map(e ->{
                ExpressVO expressVO = new ExpressVO();
                expressVO.setId(e.getId());
                expressVO.setCreatedAt(e.getCreatedAt());
                expressVO.setExpressName(Constants.EXPRESS_INFO.get(e.getExpressType()));
                expressVO.setMessage(e.getMessage());
                expressVO.setPrice(e.getPrice());
                expressVO.setNickName( null == e.getNickName() ? e.getUserName() + "@" + random.nextInt(10000) : e.getNickName());
                expressVO.setLocationName(null == e.getLocationName() ? "" : e.getLocationName());
                return expressVO;
            }).collect(Collectors.toList());

            /**
             * 将数据缓存到数据库里
             * 如果已经缓存了, 就不添加缓存
             */
            if (!CACHE_VALUE.equals(redisUtil.get(CACHE_VALUE))) {
                expressVOList.forEach(vo -> {
                    redisUtil.set(ERedisKey.EXPRESS_ORDER, vo.getId().toString(), vo);
                });
                redisUtil.set(CACHE_KEY, CACHE_VALUE,   24 * 60 * 60 * 1000);
            }

            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(expressVOList);
            logger.info(methodDesc + "完成>>>>>>>>>>>>>>>> expressVOList:{}", expressVOList);
        } catch (Exception e) {
             rd.setMsg("未知系统异常");
             logger.error(methodDesc + "未知系统异常 >>>>>>>>>>>> e:{}", e);
        }
        return rd;
    }

    @Override
    public ReturnData modifyExpress(Express express) {
        ReturnData rd = getReturnData();
        String methodDesc = "修改代送快递信息";
        try {
            if (StringUtils.isEmpty(express.getPhone()) || null == express.getLocationId() || null == express.getUserId() || null == express.getPrice() || null == express.getExpressType() || null == express.getGetCode()) {
                rd.setMsg("关键信息不能为空");
                logger.info(methodDesc + "失败 关键信息不能为空 Express:{}", express);
                return rd;
            }
            //todo 有人接单，需要双方打电话修改
            Express old = expressMapper.selectByPrimaryKey(express.getId());
            if (old.getOrderStatus().equals(Constants.ORDER_INVALID)) {
                rd.setMsg("订单以生效，需要与对方电话沟通");
                logger.info(methodDesc + "失败， 已生成订单，需要双方电话修改 Express：{}", express);
                return rd;
            }
            expressMapper.updateByPrimaryKeySelective(express);
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setMsg("修改成功");
            logger.info(methodDesc + "成功");

        }catch (Exception e) {
            rd.setMsg("未知错误");
            logger.error(methodDesc + "未知异常， e:{}", e);
        }
        return rd;
    }

    @Override
    public ReturnData deleteExpress(Long id) {
        ReturnData rd = getReturnData();
        String methodDesc = "删除订单";
        Express express = expressMapper.selectByPrimaryKey(id);
        if (express == null) {
            rd.setMsg("记录不存在");
            logger.info(methodDesc + "失败 ExpressId : {}", id);
            return rd;
        }
        try {

            if (express.getOrderStatus().equals(Constants.ORDER_PICK_UP)) {
                rd.setMsg("订单正在进行中，不能删除");
                logger.info(methodDesc + "失败， 订单正在进行中，不能删除");
                return rd;
            }
            express.setExpressStatus(Constants.ORDER_DELETE);
            expressMapper.updateByPrimaryKeySelective(express);
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setMsg("订单删除完成");
            logger.info(methodDesc + "完成");
        } catch (Exception e) {
            rd.setMsg("未知错误");
            logger.error(methodDesc + "未知异常， e:{}", e);
        }

        return rd;
    }

    private boolean valid(Express express) {
        ExpressExample example = new ExpressExample();
        example.createCriteria().andExpressTypeEqualTo(express.getExpressType()).andGetCodeEqualTo(express.getGetCode());
        example.setOrderByClause("created_at desc");
        List<Express> expressList = expressMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(expressList)) {
            return true;
        }

        //判断是否是同一天
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(new Date());
        int y1 = calendar.get(Calendar.YEAR);
        int d1 = calendar.get(Calendar.DAY_OF_YEAR);
        calendar2.setTime(expressList.get(0).getCreatedAt());
        int y2 = calendar2.get(Calendar.YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        if (y1 == y2 && d1 == d2) {
            return false;
        }
        return true;
    }

    /**
     * 验证查询日期是否正确
     * @param qureyDTO
     * @return
     */
    private boolean validTime(ExpressQueryDTO qureyDTO) {
        if (null != qureyDTO.getBeginTime() && null != qureyDTO.getEndTime()) {
            if (qureyDTO.getEndTime().getTime() > qureyDTO.getBeginTime().getTime()) {
                return true;
            }
            return false;
        }
        return true;
    }
    private boolean validPrice(ExpressQueryDTO qureyDTO) {
        if (null != qureyDTO.getLowPrice() && null != qureyDTO.getHighPrice()) {
            if (qureyDTO.getLowPrice().compareTo(qureyDTO.getHighPrice()) == 1) {
                return true;
            }
            return false;
        }
        return true;
    }

}
