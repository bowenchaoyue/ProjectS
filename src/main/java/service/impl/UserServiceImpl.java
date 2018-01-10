package service.impl;

import dao.UserMapper;
import domain.Result;
import domain.User;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 新增用户
     * @param user
     * @return
     */
    public int add(User user) {
        return userMapper.add(user);
    }

    public User login(User user) {
        return userMapper.query(user);
    }


}
