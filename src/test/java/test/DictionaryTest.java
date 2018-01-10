package test;

import domain.Dictionary;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.DictionaryService;

import java.util.List;

public class DictionaryTest extends BaseTestController {
    @Autowired
    private DictionaryService dictionaryService;


    @Test
    public void test1(){
        Dictionary dictionary = new Dictionary();
        String key = "公司文化";
        String value = "创新，创造，家与国";
        dictionary.setKey(key);
        dictionary.setValue(value);
        dictionaryService.add(dictionary);
        dictionary.setKey("公司愿景");
        dictionary.setValue("成就客户，实现自己");
        dictionaryService.add(dictionary);
    }

    @Test
    public void test2(){
        Dictionary dictionary = new Dictionary();
        dictionary.setKey("公司文化");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dictionary);
    }


}
