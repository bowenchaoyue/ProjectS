package dao;

import domain.Message;
import domain.dto.MessageDTO;
import domain.vo.MessageVO;

import java.util.List;

public interface MessageMapper {
    /**
     * 新增留言
     * @param dto
     * @return
     */
    int add(MessageDTO dto);

    /**
     * 分页查询留言
     * @param dto
     * @return
     */
    List<MessageVO> queryByPage(MessageDTO dto);

    MessageVO queryById(MessageDTO dto);

    /**
     * 删除留言
     * @param dto
     * @return
     */
    int delete(MessageDTO dto);
}
