package com.heo.service.impl;

import com.heo.entity.mapper.User;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IRegisterService;

public class RegisterServiceImpl implements IRegisterService {
    @Override
    public ReturnData register(User user) {
        return new ReturnData();
    }
}
