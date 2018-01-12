package test;

import com.github.pagehelper.PageInfo;
import domain.Picture;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.PictureService;

public class PictureTest extends BaseTestController {

    @Autowired
    private PictureService pictureService;

    @Test
    public void test(){
        Byte type = 2;
        Picture picture = new Picture();
        picture.setPicUrl("http://xxxx.jpg");
        picture.setType(type);
        picture.setCreateId(1234567L);
        pictureService.add(picture);
    }

    @Test
    public void test1(){
        Byte type =3;
        Picture picture = new Picture();
        picture.setId(5L);
        picture.setType(type);
        picture.setUpdateId(321L);
        pictureService.update(picture);
    }

    @Test
    public void test2(){
        Byte type = 1;
        Picture picture = new Picture();
        picture.setPageNum(1);
        picture.setPageSize(5);
        picture.setType(type);
        picture.setCreateId(123L);
        PageInfo<Picture> pageInfo = pictureService.queryByPage(picture);
    }

}
