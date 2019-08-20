package com.fool.service;

import com.fool.entity.FoolUser;

import java.util.List;

public interface FoolUserService {

    boolean CheckName(String username);

    boolean SignIn(FoolUser foolUser);

    List<FoolUser> queryUserListForConfig();
}
