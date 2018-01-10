package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.TeamMapper;
import domain.Team;
import org.springframework.stereotype.Service;
import service.TeamService;

import javax.annotation.Resource;
import java.util.List;


@Service("teamService")
public class TeamServiceImpl implements TeamService {

    @Resource
    private TeamMapper teamMapper;

    public int add(Team team) {
        return teamMapper.add(team);
    }

    public PageInfo<Team> queryByPage(Team team) {
        PageHelper.startPage(team.getPageNum(),team.getPageSize());
        List<Team> teams = teamMapper.queryByPage(team);
        PageInfo<Team> pageInfo = new PageInfo<Team>(teams);
        return pageInfo;
    }
}
