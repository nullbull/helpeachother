package com.heo.service.impl;

import com.heo.common.properties.RabbitMqProperties;
import com.heo.common.constant.Constants;
import com.heo.common.utils.RedisLock;
import com.heo.common.utils.RedisUtil;
import com.heo.dao.*;
import com.heo.entity.mapper.ExpressInfo;
import com.heo.entity.mapper.LocationInfo;
import com.heo.entity.mapper.LocationInfoExample;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IBaseService;
import com.heo.service.IEmailService;
import com.heo.service.IKafkaService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/17
 * @Desc
 */
@Component("baseService")
public class BaseService implements IBaseService {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };


    @Value("${rabbit-mq.queue1}")
    protected String QUEUE_A;

    @Value("${rabbit-mq.queue2}")
    protected String QUEUE_B;

    @Value("${rabbit-mq.exchange}")
    protected String EXCHANGE_NAME;

    @Autowired
    protected RabbitMqProperties rabbitMqProperties;

//    protected String QUEUE_A = "create.a";
//
//    protected String QUEUE_B = "create.b";
    @Autowired
    protected ExpressInfoMapper expressInfoMapper;

    @Autowired
    protected LocationInfoMapper locationInfoMapper;

    @Autowired
    protected ExpressMapper expressMapper;

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected IKafkaService kafkaService;

    @Autowired
    protected IEmailService emailService;

    @Autowired
    protected RedisLock redisLock;

    @Autowired
    protected JedisPool jedisPool;

    @Autowired
    protected RedisUtil redisUtil;

    @Autowired
    protected AmqpTemplate amqpTemplate;

    @Override
    public List<LocationInfo> getLocationByPart(Byte part) {
        LocationInfoExample example = new LocationInfoExample();
        example.createCriteria().andPartEqualTo(part);
        return locationInfoMapper.selectByExample(example);
    }
    @Override
    public List<ExpressInfo> getExpressInfo() {
        return expressInfoMapper.getAllExpressInfo();
    }
    public static DateFormat getDateFormat() {
        DateFormat dateFormat = threadLocal.get();
        threadLocal.remove();
        return dateFormat;
    }

    public static Date parse(String textDate) throws ParseException {
        return getDateFormat().parse(textDate);
    }
    @Override
    public ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 获取 多少小时之前的时间
     * @param Hour
     * @return Date
     */
    public Date getPastDateByHour(int Hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -Hour);
        return calendar.getTime();
    }

    public Date getPastDateByDay(int Day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -Day);
        return calendar.getTime();
    }




}
