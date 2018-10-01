package com.heo.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auth justinniu
 * @Date 2018/10/1
 * @Desc
 */
@Data
public class ExpressOrderEmailDTO {
    private String providerName;
    private String nickName;
    private Date finishTime;
    private BigDecimal price;
    private Long expressOrderId;

}
