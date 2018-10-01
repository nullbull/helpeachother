package com.heo.service;

import javax.mail.MessagingException;

public interface IEmailService {
    public boolean sendRegistEmail(String id, String to) throws MessagingException;
    public boolean sendFinshOrderEmail(String content, String sendTo);

    void sendHtmlEmail(String s, String s1, String test, String test1, String test2, boolean b);
}
