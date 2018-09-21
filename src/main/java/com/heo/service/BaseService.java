package com.heo.service;

import com.heo.common.constant.Constants;
import com.heo.dao.ExpressInfoMapper;
import com.heo.dao.ExpressMapper;
import com.heo.dao.ExpressOrderMapper;
import com.heo.dao.LocationInfoMapper;
import com.heo.entity.mapper.ExpressInfo;
import com.heo.entity.mapper.LocationInfo;
import com.heo.entity.mapper.LocationInfoExample;
import com.heo.entity.vo.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/17
 * @Desc
 */
@Component
public class BaseService {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };


    @Autowired
    public ExpressInfoMapper expressInfoMapper;

    @Autowired
    public LocationInfoMapper locationInfoMapper;

    @Autowired
    public ExpressMapper expressMapper;

    @Autowired
    public ExpressOrderMapper  expressOrderMapper;


    public List<LocationInfo> getLocationByPart(Byte part) {
        LocationInfoExample example = new LocationInfoExample();
        example.createCriteria().andPartEqualTo(part);
        return locationInfoMapper.selectByExample(example);
    }

    public List<ExpressInfo> getExpressInfo() {
        return expressInfoMapper.getAllExpressInfo();
    }
    public static DateFormat getDateFormat() {
        return (DateFormat) threadLocal.get();
    }

    public static Date parse(String textDate) throws ParseException {
        return getDateFormat().parse(textDate);
    }

    public ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }


}
