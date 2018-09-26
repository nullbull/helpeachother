package com.heo.service.impl;

import com.heo.entity.mapper.LocationInfo;
import com.heo.entity.mapper.LocationInfoExample;
import com.heo.service.BaseService;
import com.heo.service.ILocationInfoService;
import com.heo.service.ILogininforService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/24
 * @Desc
 */
@Service
public class LocaitonInfoServiceImpl extends BaseService implements ILocationInfoService {
    private static Logger logger = LoggerFactory.getLogger(LocaitonInfoServiceImpl.class);
    @Override
    public List<LocationInfo> getLocationsByPart(Byte partId) {
        String methodDesc = "根据宿舍区域查询宿舍";
        List<LocationInfo> temp = null;
        try {
            logger.info(methodDesc + "开始, partId:{}", partId);
            LocationInfoExample example = new LocationInfoExample();
            example.createCriteria().andPartEqualTo(partId);
            temp = locationInfoMapper.selectByExample(example);
            logger.info(methodDesc + "完成, result :{}", temp);

        } catch (Exception e) {
            logger.error(methodDesc + "失败， e{}", e);
        }
        return temp;
    }

    @Override
    public LocationInfo getLocationById(Byte id) {
        String methodDesc = "根据宿舍公寓号获取宿舍信息";
        LocationInfo info = null;
        try {
            logger.info(methodDesc + "开始， id {}", id);
            info = locationInfoMapper.selectByPrimaryKey(id);
            logger.info(methodDesc + " 完成， info {}", info);
        } catch (Exception e) {
            logger.error(methodDesc + "未知异常, e", e);
        }
        return info;
    }

    @Override
    public LocationInfo getLocationByName(String name) {
        String methodDesc = "根据公寓名获取宿舍信息";
        LocationInfo info = null;
        try {
            logger.info(methodDesc + "开始， name {}", name);
            LocationInfoExample example = new LocationInfoExample();
            example.createCriteria().andNameEqualTo(name);
            List<LocationInfo> temp = locationInfoMapper.selectByExample(example);
            if (!temp.isEmpty())
                info = temp.get(0);
            logger.info(methodDesc + " 完成， info {}", info);
        }catch (Exception e) {
            logger.error(methodDesc + "未知异常, e", e);
        }
        return info;
    }
}
