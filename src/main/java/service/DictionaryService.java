package service;

import domain.Dictionary;

import java.util.List;

public interface DictionaryService {
    /**
     * 新增字典
     * @param dictionary
     * @return
     */
    int add(Dictionary dictionary);

    /**
     * 根据key值查找字典
     * @param dictionary
     * @return
     */
    List<Dictionary> queryByKey(Dictionary dictionary);

    /**
     * 更新
     * @param dictionary
     * @return
     */
    int update(Dictionary dictionary);

    /**
     * 删除
     * @param dictionary
     * @return
     */
    int delete(Dictionary dictionary);
}
