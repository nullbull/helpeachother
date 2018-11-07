package com.heo.entity.vo;

import com.heo.entity.mapper.Express;
import lombok.Data;

import java.util.Date;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
@Data
public class ExpressOrderVO {
    public String neederName;
    public String providerName;
    public String neederPhone;
    public String providerPhone;
    public Byte expressType;
    public String expressName;
    public String getCode;
    public Byte locationId;
    public String locationName;
    public Date createdAtStr;
    public Date updatedAtStr;
}
