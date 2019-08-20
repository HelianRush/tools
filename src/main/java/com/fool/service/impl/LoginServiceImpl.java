package com.fool.service.impl;

import com.fool.entity.FoolUser;
import com.fool.mapper.FoolUserMapper;
import com.fool.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LoginServiceImpl implements LoginService {


    @Autowired
    private FoolUserMapper userMapper;

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    @Override
    public FoolUser QueryByUsername(String username) {
        return userMapper.QueryByUsername(username);
    }

    /**
     * 根据用户信息查询
     *
     * @param foolUser
     * @return
     */
    @Override
    public FoolUser QueryByUserAccount(FoolUser foolUser) {
        return userMapper.FindByUsername(foolUser);
    }

}
