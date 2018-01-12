package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.InfomationMapper;
import domain.dto.InfomationDTO;
import domain.vo.InfomationVO;
import org.springframework.stereotype.Service;
import service.InfomationService;

import javax.annotation.Resource;
import java.util.List;

@Service("infomationService")
public class InfomationServiceImpl implements InfomationService {

    @Resource
    private InfomationMapper infomationMapper;

    public int add(InfomationDTO infomationDTO) {
        return infomationMapper.add(infomationDTO);
    }

    public PageInfo<InfomationVO> queryByPage(InfomationDTO dto) {
        //插件分页
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        //根据条件进行查询
        List<InfomationVO> infomationVOS = infomationMapper.queryByPage(dto);
        //如何需要将结果进行处理，就加上dealWithVO方法
        PageInfo<InfomationVO> pageInfo = new PageInfo<InfomationVO>(infomationVOS);
        return pageInfo;
    }

    public InfomationVO queryById(Long id) {
        return infomationMapper.queryById(id);
    }

    public int update(InfomationDTO dto) {
        return infomationMapper.update(dto);
    }

    public int delete(InfomationDTO dto) {
        return infomationMapper.delete(dto);
    }
}
