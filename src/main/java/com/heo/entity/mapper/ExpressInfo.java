package com.heo.entity.mapper;

public class ExpressInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express_info.id
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express_info.name
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express_info.location
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    private String location;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express_info.id
     *
     * @return the value of express_info.id
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express_info.id
     *
     * @param id the value for express_info.id
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express_info.name
     *
     * @return the value of express_info.name
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express_info.name
     *
     * @param name the value for express_info.name
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express_info.location
     *
     * @return the value of express_info.location
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express_info.location
     *
     * @param location the value for express_info.location
     *
     * @mbg.generated Mon Sep 17 15:38:34 GMT+08:00 2018
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}