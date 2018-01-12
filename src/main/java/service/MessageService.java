package service;

import com.github.pagehelper.PageInfo;
import domain.Result;
import domain.dto.MessageDTO;
import domain.vo.MessageVO;

public interface MessageService {

    Result add(MessageDTO dto);

    public PageInfo<MessageVO> queryByPage(MessageDTO dto);
}
