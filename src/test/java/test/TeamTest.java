package test;


import com.github.pagehelper.PageInfo;
import domain.Team;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.TeamService;

public class TeamTest extends BaseTestController {

    @Autowired
    private TeamService teamService;

    @Test
    public void test1(){
        Team team = new Team();
        team.setName("王博士");
        team.setTitle("博士生，XX教授");
        team.setProfile("在深深公司，做出什么神产品");
        teamService.add(team);
    }

    @Test
    public void test2(){
        Team team = new Team();
        team.setPageNum(1);
        team.setPageSize(10);
        PageInfo<Team> pageInfo = teamService.queryByPage(team);

    }


}
