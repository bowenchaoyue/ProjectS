package test;

import com.github.pagehelper.PageInfo;
import domain.dto.MessageDTO;
import domain.vo.MessageVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.MessageService;

public class MessageTest extends BaseTestController {
    @Autowired
    private MessageService messageService;

    @Test
    public void test1(){
        MessageDTO dto = new MessageDTO();
        dto.setEmail("bcd.qqcom");
        dto.setMessage("bbbbb");
        dto.setName("li");
        dto.setTelephone("15927435286");
        messageService.add(dto);
    }

    @Test
    public void test2(){
        MessageDTO dto = new MessageDTO();
        dto.setPageNum(1);
        dto.setPageSize(5);
        PageInfo<MessageVO> messageVOPageInfo = messageService.queryByPage(dto);
    }
}
