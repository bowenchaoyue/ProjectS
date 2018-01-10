package dao;

import domain.Dictionary;

import java.util.List;

public interface DictionaryMapper {
    /**
     * 新增字典
     * @param dictionary
     * @return
     */
    int add(Dictionary dictionary);

    /**
     * 更新字典
     * @param dictionary
     * @return
     */
    int update(Dictionary dictionary);

    /**
     * 删除字典
     * @param dictionary
     * @return
     */

    int delete(Dictionary dictionary);

    /**
     * 根据key来查找字典
     * @param dictionary
     * @return
     */
    List<Dictionary> queryByKey(Dictionary dictionary);
}
