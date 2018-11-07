package com.heo.entity.dto;

import lombok.Data;

import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auth justinniu
 * @Date 2018/9/28
 * @Desc
 */
@Data
public class ExpressQueryDTO {

    private Byte expressStatus;

    private Byte expressType;

    @Past
    private Date beginTime;

    @Past
    private Date endTime;

    private BigDecimal lowPrice;

    private BigDecimal highPrice;

    private Integer limit;
}
