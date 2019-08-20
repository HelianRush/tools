package temp_test;

import com.fool.ToolsApplication;
import com.fool.sys_tool.SaltTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ToolsApplication.class)
public class SaltTest {

    @Test
    public void tryShiro1() {
        String password = "123456";
        String salt = "ABCD";
        SaltTools.EncryptSha256(password, salt);
    }
}
