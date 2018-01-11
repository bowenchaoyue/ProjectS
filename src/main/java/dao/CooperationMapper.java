package dao;

import domain.Cooperation;

import java.util.List;

public interface CooperationMapper {
    /**
     * 新增
     * @param cooperation
     * @return
     */
    int add(Cooperation cooperation);

    /**
     * 更新
     * @param cooperation
     * @return
     */
    int update(Cooperation cooperation);

    /**
     * 删除
     * @param cooperation
     * @return
     */
    int delete(Cooperation cooperation);

    /**
     * 查询
     * @param cooperation
     * @return
     */
    List<Cooperation> queryByPage(Cooperation cooperation);
}
