package service.impl;

import dao.SeriesMapper;
import domain.Series;
import org.springframework.stereotype.Service;
import service.SeriesService;

import javax.annotation.Resource;
import java.util.List;

@Service("seriesService")
public class SeriesServiceImpl implements SeriesService {

    @Resource
    private SeriesMapper seriesMapper;


    public int add(Series series) {
        return seriesMapper.add(series);
    }

    public List<Series> queryByPage(Series series) {
        return seriesMapper.queryByPage(series);
    }
}
