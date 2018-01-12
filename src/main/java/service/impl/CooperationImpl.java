package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.CooperationMapper;
import domain.Cooperation;
import org.springframework.stereotype.Service;
import service.CooperationService;

import javax.annotation.Resource;
import java.util.List;

@Service("cooperationService")
public class CooperationImpl implements CooperationService {

    @Resource
    private CooperationMapper cooperationMapper;

    public int add(Cooperation cooperation) {
        return cooperationMapper.add(cooperation);
    }

    public int update(Cooperation cooperation) {
        return cooperationMapper.update(cooperation);
    }

    public int delete(Cooperation cooperation) {
        return cooperationMapper.delete(cooperation);
    }

    public PageInfo<Cooperation> queryByPage(Cooperation cooperation) {
        PageHelper.startPage(cooperation.getPageNum(),cooperation.getPageSize());
        List<Cooperation> cooperations = cooperationMapper.queryByPage(cooperation);
        PageInfo<Cooperation> pageInfo = new PageInfo<Cooperation>(cooperations);
        return pageInfo;
    }

    public Cooperation queryById(Cooperation cooperation) {
        return cooperationMapper.queryById(cooperation);
    }
}
