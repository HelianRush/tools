package temp_test;

import com.fool.ToolsApplication;
import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;
import com.fool.service.impl.FoolPermissionServiceImpl;
import com.fool.service.impl.FoolRoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ToolsApplication.class)
public class testShiro1 {

    @Test
    public void tryShiro1() {
//        String username = "admin";
//        String password = "admin";
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//
//        // SecurityUtils.setSecurityManager(securityManager); // 注入SecurityManager
//        Subject subject = SecurityUtils.getSubject(); // 获取Subject单例对象
//        subject.login(token); // 登陆
    }

    @Test
    public void getPerms() {
        FoolRoleServiceImpl rservice = new FoolRoleServiceImpl();
        FoolPermissionServiceImpl pservice = new FoolPermissionServiceImpl();

        List<FoolRole> roles = rservice.SelectRoleByUserId("1");
        List<FoolPermission> permissions = pservice.SelectPermissionByRoles(roles);

        for (FoolPermission permission : permissions) {
            System.out.println(permission.toString());
        }

    }

}
