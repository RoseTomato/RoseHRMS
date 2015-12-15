package love.drose.hrms.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 控制器基类.
 * Created by lovdrose on 2015/12/14.
 */
public class BaseHandler {

    Logger logger = LogManager.getLogger(BaseHandler.class);

    /**
     * 用户返回客户端的结果
     */
    Map<String, String> result = null;

    /**
     * 跳转到某个模块页面的公共方法
     * @param module - 模块名
     * @param page - 页面名
     * @return
     */
    @RequestMapping(value = "/forward_{module}_{page}", method = RequestMethod.GET)
    public String forward(@PathVariable("module") String module, @PathVariable("page") String page) {
        logger.debug("in <== [module:" + module + ", page:" + page + "]");

        String result = "";

        if (module != null && !module.isEmpty()) {
            result += module;
        }

        if (page != null && !page.isEmpty()) {
            result += "/";
            result += page;
        }

        logger.debug("out ==> [result:" + result + "]");
        return result;
    }

    /** ============================ 页面跳转常量 ====================== */
    protected final String REDIRECT_TO_LOGIN = "redirect:/index.jsp";
    protected final String HOME = "home";
}
