package test;

import domain.Series;
import org.junit.Test;
import service.SeriesService;

import javax.annotation.Resource;
import java.util.List;

public class SeriesTest extends BaseTestController{

    @Resource
    private SeriesService seriesService;

    @Test
    public void test(){
        Series series = new Series();
        series.setName("产品C系列");
        seriesService.add(series);
    }

    @Test
    public void test1(){
        Series series = new Series();
        seriesService.queryByPage(series);
    }

}
