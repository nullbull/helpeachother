package com.heo.common.Serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @Auth justinniu
 * @Date 2018/9/4
 * @Desc
 */
public class EncodingKafka implements Serializer<Object> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        return BeanUtil.bean2Byte(o);
    }

    @Override
    public void close() {

    }
}
