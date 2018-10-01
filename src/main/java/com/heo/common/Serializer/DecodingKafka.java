package com.heo.common.Serializer;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @Auth justinniu
 * @Date 2018/9/4
 * @Desc
 */
public class DecodingKafka implements Deserializer<Object> {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return BeanUtil.byte2Object(bytes);
    }

    @Override
    public void close() {

    }
}
