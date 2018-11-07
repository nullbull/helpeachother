package com.heo.entity.dto;

import java.math.BigDecimal;
import java.util.Date;

public class IncomeStatisEmailDTO {

    private Long providerId;

    private String userName;

    private String nickName;

    /**
     * 接单数
     */
    private Integer amount;

    /**
     * 收入
     */
    private BigDecimal inCome;

    /**
     * 最近的一单收入
     */
    private Date lastOrder;



}
