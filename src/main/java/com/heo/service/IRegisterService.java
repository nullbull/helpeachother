package com.heo.service;

import com.heo.entity.mapper.User;
import com.heo.entity.vo.ReturnData;

public interface IRegisterService {
    ReturnData register(User user);
}
