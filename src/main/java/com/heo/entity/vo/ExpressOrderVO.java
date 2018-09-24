package com.heo.entity.vo;

import com.heo.entity.mapper.Express;
import com.heo.entity.mapper.ExpressOrder;

import java.util.Date;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
public class ExpressOrderVO extends ExpressOrder {
    public String neederName;
    public String providerName;
    public String neederPhone;
    public String providerPhone;
    public String expressType;
    public String expressName;
    public String getCode;
    public Byte locationId;
    public String locationName;
    public Date createdAtStr;
    public Date updatedAtStr;
}
