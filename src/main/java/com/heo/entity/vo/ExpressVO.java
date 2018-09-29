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
    /**
     * id
     */
    private Long id;
    /**
     * 宿舍名
     */
    private String locationName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 快递名称
     */
    private String expressName;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 备注
     */
    private String message;
    /**
     * 创建时间
     */
    private Date createdAt;
}
