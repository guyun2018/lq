package com.lqstar.service;

import com.lqstar.mapper.TestMapper;
import com.lqstar.model.Boy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/13/013 22:22
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;
    @Override
    public Boy getInfo(int id) {
        return testMapper.select(id);
    }
}
