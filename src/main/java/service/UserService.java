package service;

import domain.Result;
import domain.User;

import java.util.List;

public interface UserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 查询用户
     * @return
     */
    User login(User user);


}
