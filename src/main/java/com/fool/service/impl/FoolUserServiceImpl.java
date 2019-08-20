package com.fool.service.impl;

import com.fool.entity.FoolUser;
import com.fool.mapper.FoolUserMapper;
import com.fool.service.FoolUserService;
import com.fool.sys_common.SystemCode;
import com.fool.sys_tool.SaltTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FoolUserServiceImpl implements FoolUserService {

    private static Logger logger = LoggerFactory.getLogger(FoolUserServiceImpl.class);

    @Autowired
    FoolUserMapper userMapper;

    /**
     * 查询用户是否存在
     *
     * @param username
     * @return
     */
    @Override
    public boolean CheckName(String username) {
        boolean flag = true;
        FoolUser user = userMapper.QueryByUsername(username);
        if (null != user)
            flag = false;
        // logger.info("Method CheckName() return :" + flag);
        return flag;
    }

    /**
     * 用户注册
     *
     * @param foolUser
     * @return
     */
    @Override
    public boolean SignIn(FoolUser foolUser) {

        // 资源
        String password = foolUser.getPassword(); // 传入的密码
        String salt = SaltTools.CreateSalt(); // 盐
        Date date = new Date(); // 日期
        // 封装数据
        //foolUser.setUserId(SystemCode.GetID(this.getClass()));
        foolUser.setUserId(SystemCode.GetIDL());
        foolUser.setAccount(foolUser.getUsername());
        foolUser.setPassword(SaltTools.EncryptSha256(password, salt)); // 加密 MD5
        foolUser.setPlaintext(password); // 密码明文
        foolUser.setSalt(salt);
        foolUser.setLockStatus(true);
        foolUser.setUserCreateTime(date);
        foolUser.setUserLastLoginTime(date);
        int flog = userMapper.insertSelective(foolUser);
        if (0 < flog)
            return true;
        else return false;
    }

    /**
     * 查询用户列表，用于用户、角色、权限配置时展示
     * 获取：user_id、username
     *
     * @return
     */
    @Override
    public List<FoolUser> queryUserListForConfig() {
        return userMapper.queryUserListForConfig();
    }
}
