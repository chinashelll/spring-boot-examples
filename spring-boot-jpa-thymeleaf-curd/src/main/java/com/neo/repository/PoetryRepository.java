package com.neo.repository;

import com.neo.entity.Poetry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author xiefei
 * @create 2018-11-06 2:43 PM
 * @desc
 **/
public interface PoetryRepository extends ElasticsearchRepository<Poetry,String> {
}
