package test;

import domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;
import utils.MD5Util;

public class UserTest extends BaseTestController{
    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        try {
            user.setPassword(MD5Util.MD5(user.getPassword()));
        }catch (Exception e){

        }
        userService.add(user);
    }

    @Test
    public void test2(){
        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        User login = userService.login(user);
        String s = null;
        try {
             s = MD5Util.MD5(user.getPassword());
        }catch (Exception e){

        }
        if(login.getPassword().equals(s)){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }

}
