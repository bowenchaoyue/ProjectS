package service;

import com.github.pagehelper.PageInfo;
import domain.Cooperation;

public interface CooperationService {
    /**
     * 新增合作伙伴
     * @param cooperation
     * @return
     */
    int add(Cooperation cooperation);

    /**
     * 更新合作伙伴
     * @param cooperation
     * @return
     */
    int update(Cooperation cooperation);

    /**
     * 删除合作伙伴
     * @param cooperation
     * @return
     */
    int delete(Cooperation cooperation);

    /**
     * 分页查询
     * @param cooperation
     * @return
     */
    PageInfo<Cooperation> queryByPage(Cooperation cooperation);
}
