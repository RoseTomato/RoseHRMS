package love.drose.hrms.controllers;

import love.drose.hrms.models.Manager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 后台控制器
 * Created by lovedrose on 12/15/15.
 */
@Controller
public class BackstageHandler extends BaseHandler {
    /**
     * 登陆
     * @param manager
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Manager manager, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        logger.debug("in <==[account:" + manager.getAccount() + ", password:" + manager.getPassword() + "]");
        String message = null;
        try {
            if (bindingResult.hasErrors()) {
                logger.error("out ==>");
                return REDIRECT_TO_LOGIN;
            }

            // 使用权限工具进行登陆，登陆成功后跳到shiro配置的successUrl
            SecurityUtils.getSubject().login(new UsernamePasswordToken(manager.getAccount(), manager.getPassword()));

        }catch (UnknownAccountException e0){
            message = "UnknownAccountException";
        }catch(IncorrectCredentialsException e1) {
            message = "IncorrectCredentialsException";
        }catch (AuthenticationException e2) {
            message = "AuthenticationException";
        }

        if (message != null && !message.isEmpty()) {
            logger.error("out ==> message:" + message);
            redirectAttributes.addAttribute("message",message);
            return REDIRECT_TO_LOGIN;
        }

        logger.debug("out ==>");
        return HOME;
    }
}
