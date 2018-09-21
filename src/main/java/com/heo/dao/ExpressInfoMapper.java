package com.heo.dao;

import com.heo.entity.mapper.ExpressInfo;
import com.heo.entity.mapper.ExpressInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    long countByExample(ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    int deleteByExample(ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    int insert(ExpressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    int insertSelective(ExpressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    List<ExpressInfo> selectByExample(ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    ExpressInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    int updateByExampleSelective(@Param("record") ExpressInfo record, @Param("example") ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    int updateByExample(@Param("record") ExpressInfo record, @Param("example") ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    int updateByPrimaryKeySelective(ExpressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express_info
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    int updateByPrimaryKey(ExpressInfo record);

    List<ExpressInfo> getAllExpressInfo();

}