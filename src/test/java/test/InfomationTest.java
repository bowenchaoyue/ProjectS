package test;

import com.github.pagehelper.PageInfo;
import domain.Infomation;
import domain.dto.InfomationDTO;
import domain.vo.InfomationVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.InfomationService;

public class InfomationTest extends BaseTestController {
    @Autowired
    private InfomationService infomationService;

    @Test
    public void test1(){
        Byte b = 0;
        InfomationDTO dto = new InfomationDTO();
        dto.setAuthor("李四");
        dto.setIntroduction("简要介绍什么的");
        dto.setContent("话说什么什么，巴拉巴拉芭芭拉");
        dto.setPublish(b);
        dto.setTitle("今日重磅消息");
        dto.setSource("腾讯新闻");
        dto.setCategoryId(1L);
        infomationService.add(dto);
    }

    @Test
    public void test2(){
        InfomationDTO dto = new InfomationDTO();
        dto.setPageNum(1);
        dto.setPageSize(5);
        PageInfo<InfomationVO> pageInfo = infomationService.queryByPage(dto);

    }

    @Test
    public void test3(){
        InfomationDTO dto = new InfomationDTO();
        dto.setId(2L);
        infomationService.delete(dto);

    }
}
