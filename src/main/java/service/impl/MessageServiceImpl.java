package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.MessageMapper;
import domain.Result;
import domain.dto.MessageDTO;
import domain.vo.MessageVO;
import org.springframework.stereotype.Service;
import service.MessageService;

import javax.annotation.Resource;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    /**
     * 新增留言
     * @param dto
     * @return
     */
    public Result add(MessageDTO dto) {
        int result = messageMapper.add(dto);
        return new Result(true);
    }

    /**
     * 分页查询留言
     * @param dto
     * @return
     */
    public PageInfo<MessageVO> queryByPage(MessageDTO dto){
        //分页插件
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        //查询
        List<MessageVO> messageVOS = messageMapper.queryByPage(dto);
        //分页信息
        PageInfo<MessageVO> pageInfo = new PageInfo<MessageVO>(messageVOS);
        return pageInfo;
    }
}
