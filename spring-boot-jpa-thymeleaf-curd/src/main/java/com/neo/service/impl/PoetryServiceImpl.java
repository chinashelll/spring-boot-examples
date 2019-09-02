package com.neo.service.impl;

import com.google.gson.Gson;
import com.neo.entity.Poetry;
import com.neo.repository.PoetryRepository;
import com.neo.service.PoetryService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiefei
 * @create 2018-11-06 2:46 PM
 * @desc
 **/
@Log
@Slf4j
@Service
public class PoetryServiceImpl implements PoetryService {

    @Resource
    private PoetryRepository poetryRepository;
    @Resource
    private ElasticsearchTemplate esTemplate;

    @Override
    public void save(Poetry poetry) {
        poetryRepository.save(poetry);
    }

    @Override
    public Page<Poetry> serachPoetry(String key, Pageable pageable) {
        //多字段搜索
        QueryBuilder qb = QueryBuilders.multiMatchQuery(key, "dynasty","author","title","content");
        return poetryRepository.search(qb,pageable);
    }

    @Override
    public Page<Poetry> serachAll(Pageable pageable) {
        return poetryRepository.findAll(pageable);
    }

    @Override
    public void delete(String id) {
        poetryRepository.deleteById(id);
    }

    @Override
    public void addList(List<Poetry> list) {
        List<IndexQuery> list1 = new ArrayList<>();
        Gson gson = new Gson();
        for(Poetry poetry : list){
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(poetry.getId());
            indexQuery.setObject(poetry);
            indexQuery.setIndexName("poetry");
            indexQuery.setType("poetry");
            list1.add(indexQuery);
        }
        esTemplate.bulkIndex(list1);
    }

    @Override
    public Page<Poetry> serachByCondition(Poetry poetry, Pageable pageable) {
        //多字段搜索
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotEmpty(poetry.getDynasty())) {
            boolQueryBuilder.must(new WildcardQueryBuilder("dynasty",("*"+poetry.getDynasty()+"*")));
        }
        if (StringUtils.isNotEmpty(poetry.getAuthor())) {
            boolQueryBuilder.must(new WildcardQueryBuilder("author",("*"+poetry.getAuthor()+"*")));
        }
        if (StringUtils.isNotEmpty(poetry.getTitle())) {
            boolQueryBuilder.must(new WildcardQueryBuilder("title",("*"+poetry.getTitle()+"*")));
        }
        //方式1
        return poetryRepository.search(boolQueryBuilder, pageable);

        //方式2
        /*SearchQuery searchQuery =  new
                NativeSearchQueryBuilder().withFilter(boolQueryBuilder).build();
        return esTemplate.queryForPage(searchQuery,Poetry.class);*/
    }
}
