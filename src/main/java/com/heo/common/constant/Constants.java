package com.heo.common.constant;

import com.heo.common.utils.RedisUtil;

import java.util.HashMap;

/**
 * 通用常量信息
 * 所有的变量必须是final static的
 * @author justinniu
 * @desc
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGENUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGESIZE = "pageSize";

    /**
     * 排序列
     */
    public static String ORDERBYCOLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String ISASC = "isAsc";

    public static final Byte SUCCESS_CODE = 1;

    public static final Byte FAIL_CODE = 0;

    public static final Byte ORDER_INVALID = 0;

    public static final Byte ORDER_NEW = 1;

    public static final Byte ORDER_PICK_UP = 2;

    public static final Byte ORDER_FINISH = 3;

    public static final Byte ORDER_DELETE = 4;

    public final static Byte PROVIDER = 2;

    public final static Byte NEEDER = 1;

    public final static Byte STATIS_HOUR = 12;
    public final static HashMap<Byte, String> EXPRESS_INFO = new HashMap<>();
    static {
        EXPRESS_INFO.put((byte) 1, "圆通快递");
        EXPRESS_INFO.put((byte) 2, "京东快递");
        EXPRESS_INFO.put((byte) 3, "顺丰快递");
        EXPRESS_INFO.put((byte) 4, "中通快递");
        EXPRESS_INFO.put((byte) 5, "申通快递");
        EXPRESS_INFO.put((byte) 6, "百世快递");
        EXPRESS_INFO.put((byte) 7, "天天快递");
        EXPRESS_INFO.put((byte) 8, "天猫快递");
    }
    public final static String REDIS_KEY = "expressOrder:message:";

    public final static String KAFKA_KEY = "email";




}
