package com.heo.dao;

import com.heo.entity.mapper.Express;
import com.heo.entity.mapper.ExpressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    long countByExample(ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    int deleteByExample(ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    int insert(Express record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    int insertSelective(Express record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    List<Express> selectByExample(ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    Express selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    int updateByExampleSelective(@Param("record") Express record, @Param("example") ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    int updateByExample(@Param("record") Express record, @Param("example") ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    int updateByPrimaryKeySelective(Express record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Fri Sep 28 12:16:46 GMT+08:00 2018
     */
    int updateByPrimaryKey(Express record);
}