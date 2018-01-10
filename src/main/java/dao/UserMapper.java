package dao;

import domain.User;

import java.util.List;

public interface UserMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 查询用户
     * @param user
     * @return
     */
    User query(User user);
}
