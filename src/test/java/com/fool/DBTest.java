package com.fool;

import com.fool.entity.FoolUser;
import com.fool.mapper.FoolUserMapper;
import com.fool.sys_tool.SaltTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ToolsApplication.class)
public class DBTest {

    @Autowired
    private FoolUserMapper foolUserMapper;

    @Test
    public void db_queryAll_user() {


//        List<Map<String, Object>> foolUsers = foolUserMapper.QueryByUsernameToMany(new FoolUser());
//        System.out.println("List<FoolUser> foolUsers QueryByUsernameToMany");
//
//        for (Map<String, Object> map : foolUsers) {
//        }
//
//        FoolUser user = new FoolUser();
//        user.setUserId("1");
////        user.setUsername("admin");
//
//        System.out.println("FoolUser foolUsers QueryByUsername");
//        FoolUser foolUser = foolUserMapper.QueryByUsername(user);
//        System.out.println(foolUser);

    }

    @Test
    public void random16() {
//        SaltTools salt = new SaltTools();
//        for (int i = 0; i < 4; i++) {
//            System.out.println(salt.CreateSalt());
//        }
    }

}

