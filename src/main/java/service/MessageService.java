package service;

import com.github.pagehelper.PageInfo;
import domain.dto.MessageDTO;
import domain.vo.MessageVO;

public interface MessageService {

    int add(MessageDTO dto);

    public PageInfo<MessageVO> queryByPage(MessageDTO dto);
}
