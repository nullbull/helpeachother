package com.heo.service.impl;

import com.heo.common.constant.Constants;
import com.heo.common.utils.NiuUtils;
import com.heo.common.utils.StringUtils;
import com.heo.entity.mapper.Express;
import com.heo.entity.mapper.ExpressExample;
import com.heo.entity.mapper.ExpressOrder;
import com.heo.entity.vo.ReturnData;
import com.heo.service.BaseService;
import com.heo.service.IExpressService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/17
 * @Desc
 */
public class ExpressService extends BaseService implements IExpressService {

    private Logger logger = LoggerFactory.getLogger(ExpressService.class);
    @Override
    public ReturnData createExpress(Express express) {
        ReturnData rd = getReturnData();
        String methoDesc = "创建代送快递信息";
        try {
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
            if (null != expressOrderMapper.selectByExpressId(express.getId())) {
                rd.setMsg("操作失败，需要双方电话沟通");
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
    public ReturnData deleteExpress(Express express) {
        ReturnData rd = getReturnData();
        String methodDesc = "删除订单";
        ExpressOrder expressOrder = expressOrderMapper.selectByExpressId(express.getId());
        try {
            if (null != expressOrder) {
                if (expressOrder.getStatus() == Constants.ORDER_PICK_UP) {
                    rd.setMsg("订单正在进行中，不能删除");
                    logger.info(methodDesc + "失败， 订单正在进行中，不能删除");
                    return rd;
                }
                expressOrder.setStatus(Constants.ORDER_DELETE);
                expressOrderMapper.updateByPrimaryKeySelective(expressOrder);
            }
            express.setStatus(Constants.ORDER_DELETE);
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

    private ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }
    private boolean valid(Express express) {
        ExpressExample example = new ExpressExample();
        example.createCriteria().andExpressTypeEqualTo(express.getExpressType()).andGetCodeEqualTo(express.getGetCode());
        example.setOrderByClause("created_at desc");
        List<Express> expressList = expressMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(expressList))
            return true;

        //判断是否是同一天
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(new Date());
        int y1 = calendar.get(Calendar.YEAR);
        int d1 = calendar.get(Calendar.DAY_OF_YEAR);
        calendar2.setTime(expressList.get(0).getCreatedAt());
        int y2 = calendar2.get(Calendar.YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        if (y1 == y2 && d1 == d2)
            return false;
        return true;
    }

}
