import love.drose.hrms.models.Manager;
import love.drose.hrms.services.ManagerService;
import love.drose.hrms.utils.MD5Util;
import org.junit.Test;

/**
 * Created by lovedrose on 12/15/15.
 */
public class TestManagerService extends BaseTest {

    @Test
    public void testSave() {
        Manager manager = new Manager();
        manager.setAccount("admin");
        manager.setPassword(MD5Util.getMD5String("admin"));
        manager.setName("Rose");
        managerService.save(manager);
    }
}
