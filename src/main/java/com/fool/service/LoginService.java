package com.fool.service;

import com.fool.entity.FoolUser;


public interface LoginService {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    FoolUser QueryByUsername(String username);

    /**
     *
     * @param foolUser
     * @return
     */
    FoolUser QueryByUserAccount(FoolUser foolUser);
}
