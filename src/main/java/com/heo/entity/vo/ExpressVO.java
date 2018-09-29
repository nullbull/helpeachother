package com.heo.entity.vo;

import com.heo.entity.mapper.Express;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auth justinniu
 * @Date 2018/9/28
 * @Desc
 */
@Data
public class ExpressVO  {
    private String locationName;
    private String nickName;
    private String expressName;
    private BigDecimal price;
    private String message;
    private Date createdAt;
}
