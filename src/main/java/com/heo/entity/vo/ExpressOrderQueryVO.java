package com.heo.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Auth justinniu
 * @Date 2018/9/24
 * @Desc
 */
@Data
public class ExpressOrderQueryVO {
    private Long id;
    private Byte type;
    private Byte status;
    private Date beginTime;
    private Date endTime;
}
