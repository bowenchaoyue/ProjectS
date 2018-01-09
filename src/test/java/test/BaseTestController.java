package test;
import com.github.pagehelper.PageInfo;
import domain.dto.MessageDTO;
import domain.vo.MessageVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:dispatcher-servlet.xml"
})
public class BaseTestController {


}
