package com.heo.entity.mapper;

import java.math.BigDecimal;
import java.util.Date;

public class Express {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.user_id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.phone
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.express_type
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private Byte expressType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.get_code
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private String getCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.price
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.location_id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private Byte locationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.message
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.created_at
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.updated_at
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private Date updatedAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.status
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.id
     *
     * @return the value of express.id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.id
     *
     * @param id the value for express.id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.user_id
     *
     * @return the value of express.user_id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.user_id
     *
     * @param userId the value for express.user_id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.phone
     *
     * @return the value of express.phone
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.phone
     *
     * @param phone the value for express.phone
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.express_type
     *
     * @return the value of express.express_type
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public Byte getExpressType() {
        return expressType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.express_type
     *
     * @param expressType the value for express.express_type
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setExpressType(Byte expressType) {
        this.expressType = expressType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.get_code
     *
     * @return the value of express.get_code
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public String getGetCode() {
        return getCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.get_code
     *
     * @param getCode the value for express.get_code
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setGetCode(String getCode) {
        this.getCode = getCode == null ? null : getCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.price
     *
     * @return the value of express.price
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.price
     *
     * @param price the value for express.price
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.location_id
     *
     * @return the value of express.location_id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public Byte getLocationId() {
        return locationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.location_id
     *
     * @param locationId the value for express.location_id
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setLocationId(Byte locationId) {
        this.locationId = locationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.message
     *
     * @return the value of express.message
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.message
     *
     * @param message the value for express.message
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.created_at
     *
     * @return the value of express.created_at
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.created_at
     *
     * @param createdAt the value for express.created_at
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.updated_at
     *
     * @return the value of express.updated_at
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.updated_at
     *
     * @param updatedAt the value for express.updated_at
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.status
     *
     * @return the value of express.status
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.status
     *
     * @param status the value for express.status
     *
     * @mbg.generated Sat Sep 29 12:02:42 GMT+08:00 2018
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}