package com.heo.service;

import javax.mail.MessagingException;

public interface IEmailService {
    public void sendRegistEmail(String id, String to) throws MessagingException;
    public boolean sendFinshOrderEmail(String content, String sendTo);
}
