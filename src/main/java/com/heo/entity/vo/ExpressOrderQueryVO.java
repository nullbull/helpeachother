package com.heo.entity.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auth justinniu
 * @Date 2018/9/24
 * @Desc
 */
@Data
public class ExpressOrderQueryVO {

    /**
     *发布者id/跑腿id
     */
    @NotNull
    @Min(1)
    private Long id;

    /**
     *  发布者/跑腿者类型
     */
    @NotNull
    private Byte type;
    /**
     * 服务单状态
     */
    private Byte status;
    /**
     * 开始时间
     */
    private Date beginTime;
    /**
     * 结束时间
     */
    private Date endTime;
}
