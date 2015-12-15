import love.drose.hrms.models.ManagerRole;
import love.drose.hrms.models.Role;
import org.junit.Test;

/**
 * Created by lovedrose on 12/15/15.
 */
public class TestRoleService extends BaseTest {

    @Test
    public void testSave() {
        Role role = new Role();
        role.setName("超级管理员");
        roleService.save(role);
    }

    @Test
    public void addManagerRole() {
        ManagerRole managerRole = new ManagerRole(1, 1);
        managerRoleService.save(managerRole);
    }

    @Test
    public void testFindRoleNamesByManagerId() {
        System.out.println(managerRoleService.findRoleNamesByManagerId(1));
    }
}
