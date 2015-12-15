package love.drose.hrms.services.impl;

import love.drose.hrms.models.ManagerRole;
import love.drose.hrms.services.ManagerRoleService;
import love.drose.hrms.services.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lovedrose on 12/15/15.
 */
@Service("managerRoleService")
public class ManagerRoleServiceImpl extends BaseService<ManagerRole> implements ManagerRoleService {

    Logger logger = LogManager.getLogger(ManagerRoleServiceImpl.class);

    @Autowired
    private RoleService roleService;

    @Override
    public Set<String> findRoleNamesByManagerId(Integer id) {
        logger.debug("<==[managerId:" + id + "]");
        Set<String> result = new HashSet<String>();
        List<ManagerRole> list = findAllByProperty("managerId", id);
        if (list != null && list.size() > 0) {
            for (ManagerRole mr : list) {
                result.add(roleService.findById(mr.getRoleId()).getName());
            }
        }
        logger.debug("==>");
        return result;
    }
}
