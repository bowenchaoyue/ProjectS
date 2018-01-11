package test;

import com.github.pagehelper.PageInfo;
import domain.Cooperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.CooperationService;


public class CooperationTest extends BaseTestController{

    @Autowired
    private CooperationService cooperationService;

    @Test
    public void test1(){
        Byte type = 2;
        Cooperation cooperation = new Cooperation();
        cooperation.setName("yyyy平台");
        cooperation.setProfile("在什么方面领先");
        cooperation.setType(type);
        cooperation.setContent("这里是正文，此处省去一千字");
        cooperationService.add(cooperation);
    }

    @Test
    public void test2(){
        Byte type = 1;
        Cooperation cooperation = new Cooperation();
        cooperation.setType(type);
        cooperation.setPageNum(1);
        cooperation.setPageSize(5);
        PageInfo<Cooperation> pageInfo = cooperationService.queryByPage(cooperation);

    }

}
