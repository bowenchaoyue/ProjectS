package service;

import com.github.pagehelper.PageInfo;
import domain.Team;

public interface TeamService {
    /**
     * 新增成员
     * @param team
     * @return
     */
    int add(Team team);

    /**
     * 根据条件分页查询
     * @param team
     * @return
     */
    PageInfo<Team> queryByPage(Team team);

}
