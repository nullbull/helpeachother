package com.heo.entity.vo;

import lombok.Data;

/**
 * @Auth justinniu
 * @Date 2018/9/12
 * @Desc
 */
@Data
public class ReturnData {
    private String msg;
    private Byte code;
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
