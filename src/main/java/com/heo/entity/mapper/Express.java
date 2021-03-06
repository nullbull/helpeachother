package com.heo.entity.mapper;

import java.math.BigDecimal;
import java.util.Date;

public class Express {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.id
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.user_id
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.phone
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.express_type
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Byte expressType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.get_code
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private String getCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.price
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.location_id
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Byte locationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.message
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.created_at
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.updated_at
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Date updatedAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.express_status
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Byte expressStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.provider_id
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Long providerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.order_status
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Byte orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.score
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Byte score;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.start_at
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Date startAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column express.end_at
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    private Date endAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.id
     *
     * @return the value of express.id
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
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
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.express_status
     *
     * @return the value of express.express_status
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public Byte getExpressStatus() {
        return expressStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.express_status
     *
     * @param expressStatus the value for express.express_status
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public void setExpressStatus(Byte expressStatus) {
        this.expressStatus = expressStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.provider_id
     *
     * @return the value of express.provider_id
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public Long getProviderId() {
        return providerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.provider_id
     *
     * @param providerId the value for express.provider_id
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.order_status
     *
     * @return the value of express.order_status
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.order_status
     *
     * @param orderStatus the value for express.order_status
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.score
     *
     * @return the value of express.score
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public Byte getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.score
     *
     * @param score the value for express.score
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public void setScore(Byte score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.start_at
     *
     * @return the value of express.start_at
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public Date getStartAt() {
        return startAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.start_at
     *
     * @param startAt the value for express.start_at
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column express.end_at
     *
     * @return the value of express.end_at
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public Date getEndAt() {
        return endAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column express.end_at
     *
     * @param endAt the value for express.end_at
     *
     * @mbg.generated Sat Nov 03 21:29:44 GMT+08:00 2018
     */
    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
}