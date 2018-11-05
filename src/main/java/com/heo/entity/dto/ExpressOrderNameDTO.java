package com.heo.entity.dto;

import com.heo.entity.mapper.Express;

public class ExpressOrderNameDTO extends Express{

    /**
     * 发布者姓名
     */
    private String neederName;

    /**
     * 接单者姓名
     */
    private String providerName;

    /**
     * 快递名称
     */
    private String expressName;

    /**
     * 宿舍名称
     */
    private String locationName;

}
