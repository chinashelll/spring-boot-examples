package com.neo.service;

import com.neo.entity.Poetry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xiefei
 * @create 2018-11-06 2:46 PM
 * @desc
 **/
public interface PoetryService {
    /**
     * 新增诗歌
     * @param poetry
     */
    public void save(Poetry poetry);

    /**
     * 分页匹配搜索诗歌
     * @param key
     * @param pageable
     * @return
     */
    public Page<Poetry> serachPoetry(String key, Pageable pageable);


    /**
     * 分页匹配搜索诗歌
     * @param pageable
     * @return
     */
    public Page<Poetry> serachAll(Pageable pageable);

    /**
     * 根据id删除
     * @param id
     */
    public void delete(String id);

    /**
     * list添加方法
     * @param list
     */
    public void addList(List<Poetry> list);

    public Page<Poetry> serachByCondition(Poetry poetry,Pageable pageable);
}
