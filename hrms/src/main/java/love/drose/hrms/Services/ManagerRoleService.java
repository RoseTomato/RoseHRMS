package love.drose.hrms.services;

import love.drose.hrms.models.ManagerRole;

import java.util.Set;

/**
 * Created by lovedrose on 12/15/15.
 */
public interface ManagerRoleService extends IService<ManagerRole> {
    /**
     * 根据管理员id获取角色名集合
     * @param id
     * @return
     */
    Set<String> findRoleNamesByManagerId(Integer id);

}
