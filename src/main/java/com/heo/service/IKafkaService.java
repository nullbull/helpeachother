package com.heo.service;

/**
 * @Auth justinniu
 * @Date 2018/9/30
 * @Desc
 */
public interface IKafkaService {

    boolean sendMessage(Long id, String value);
}
