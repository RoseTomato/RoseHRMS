import love.drose.hrms.services.ManagerRoleService;
import love.drose.hrms.services.ManagerService;
import love.drose.hrms.services.RoleService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lovedrose on 12/15/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BaseTest {

    @Autowired
    protected ManagerService managerService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected ManagerRoleService managerRoleService;
}
