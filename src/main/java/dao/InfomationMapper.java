package dao;

import domain.dto.InfomationDTO;
import domain.vo.InfomationVO;

import java.util.List;

public interface InfomationMapper {
    /**
     * 新增资讯
     * @param dto
     * @return
     */
    int add(InfomationDTO dto);

    /**
     * 更新资讯
     * @param dto
     * @return
     */
    int update(InfomationDTO dto);

    /**
     * 根据条件分页查询资讯
     * @param dto
     * @return
     */
    List<InfomationVO> queryByPage(InfomationDTO dto);

    InfomationVO queryById(Long id);

    /**
     *删除资讯
     */
    int delete(InfomationDTO dto);

}
