package love.drose.hrms.shiro;

import love.drose.hrms.models.Manager;
import love.drose.hrms.models.Role;
import love.drose.hrms.services.ManagerRoleService;
import love.drose.hrms.services.ManagerService;
import love.drose.hrms.services.RoleService;
import love.drose.hrms.utils.MD5Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by lovedrose on 12/15/15.
 */
@Service
public class MyShiro extends AuthorizingRealm {

    Logger logger = (Logger) LogManager.getLogger(MyShiro.class);

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerRoleService managerRoleService;
    /**
     * 权限验证
     * @param principals － 管理员名
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("in <== [principals:" + principals + "]");

        // 获取登陆时输入的账号
        String account = (String) principals.fromRealm(getName()).iterator().next();
        // 到数据库中查询是否有此对象
        Manager manager = managerService.findByProperty("account", account);

        if (manager != null) {
            // 权限信息对象info，用来存放查出的用户所有的角色及权限
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 获取管理员关联的角色集合
            Set<String> roles = managerRoleService.findRoleNamesByManagerId(manager.getId());
            info.setRoles(roles);

            logger.debug("out ==>");
            return info;
        }
        logger.debug("out ==> null");

        return null;
    }

    /**
     * 登录验证
     * @param token － 凭证
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String password = String.valueOf(((UsernamePasswordToken) token).getPassword());
        logger.debug("in ==> [Principal:"+token.getPrincipal()+", password:" + password + "]");
        // UsernamePasswordToken 对象用来存放提交的登陆信息
        UsernamePasswordToken upt = (UsernamePasswordToken) token;

        // 获取登陆时输入的密码，并使用md5算法进行加密，在赋值给token的password属性
        String md5Password = MD5Util.getMD5String(password);
        ((UsernamePasswordToken) token).setPassword(md5Password.toCharArray());

        // 查出是否有此管理员
        Manager manager = managerService.findByProperty("account", upt.getUsername());

        if(manager != null) {
            logger.debug("out ==>");
            return new SimpleAuthenticationInfo(manager.getAccount(), manager.getPassword(), getName());
        }

        logger.debug("out ==> null");
        return null;
    }
}
