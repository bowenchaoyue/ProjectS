package controller;


import com.google.common.collect.Maps;
import domain.Constants;
import domain.Result;
import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import utils.MD5Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/backend")
public class BackendController {

    @Resource
    private UserService userService;

    //记录用户数量
    private Map<String,Integer> userIntegerMap = Maps.newConcurrentMap();
    //记录用户的session
    private Map<String,HttpSession> userHttpSessionMap =Maps.newConcurrentMap();

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    /**
     * 登录验证接口
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(HttpServletRequest request,String name,String password){
        User user = new User();
        user.setName(name);
        User login = userService.login(user);
        //比较密码是否相同
        String encodePassword = null;
        try {
            encodePassword = MD5Util.MD5(password);
        }catch (Exception e){

        }
        if(login.getPassword().equals(encodePassword)){
            Integer integer = userIntegerMap.get(login.getName());
            if (integer == null){
                userIntegerMap.put(login.getName(),1);
            }else {
              //用户已经登录了
            }
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_KEY,login);
        }else {
            //登录失败
        }
        //相同登录成功
        //不同登录失败
        // TODO: 2018/1/10 是让前端进行跳转控制，还是在这里，登录成功了就跳转首页，登录失败就重定向登录页面 
        // TODO: 2018/1/10 还需要考虑一个问题，同一个账号如何保证一个登录 得做一个全局的控制
//        HttpSession session = request.getSession();
//        session.setAttribute("user",login);
        return  new Result(true);
    }


    /**
     * 退出接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/doLogout")
    @ResponseBody
    public Result doLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return new Result(true);
    }


}
