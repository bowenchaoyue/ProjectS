package dao;

import domain.Team;

import java.util.List;

public interface TeamMapper {
    /**
     * 新增成员
     * @param team
     * @return
     */
    int add(Team team);

    /**
     * 更新成员
     * @param team
     * @return
     */
    int update(Team team);

    /**
     * 删除成员
     * @param team
     * @return
     */

    int delete(Team team);

    /**
     * 查询成员
     * @param team
     * @return
     */
    List<Team> queryByPage(Team team);
}
