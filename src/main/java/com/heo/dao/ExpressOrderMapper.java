package com.heo.dao;

import com.heo.entity.mapper.ExpressOrder;
import com.heo.entity.mapper.ExpressOrderExample;
import java.util.List;

import com.heo.entity.vo.ExpressOrderVO;
import org.apache.ibatis.annotations.Param;

public interface ExpressOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    long countByExample(ExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    int deleteByExample(ExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    int insert(ExpressOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    int insertSelective(ExpressOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    List<ExpressOrder> selectByExample(ExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    ExpressOrder selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    int updateByExampleSelective(@Param("record") ExpressOrder record, @Param("example") ExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    int updateByExample(@Param("record") ExpressOrder record, @Param("example") ExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    int updateByPrimaryKeySelective(ExpressOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_order
     *
     * @mbg.generated Fri Sep 21 15:30:28 GMT+08:00 2018
     */
    int updateByPrimaryKey(ExpressOrder record);

    /*
       根据Express id 获取ExpressOrder
     */
    ExpressOrder selectByExpressId(Long id);
    /*
     * 封装一些 订单双方信息
     */
    ExpressOrderVO selectExpressOrderAndUserName(Long id);

//    List<ExpressOrder> selectByProviderId(Long id);
//    List<ExpressOrder> selectByProviderId(Long id);
}