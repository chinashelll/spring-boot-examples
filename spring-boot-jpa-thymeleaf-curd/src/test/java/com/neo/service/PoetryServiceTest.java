package com.neo.service;

import java.util.ArrayList;
import java.util.List;

import com.neo.entity.Poetry;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author xiefei
 * @create 2018-11-06 4:28 PM
 * @desc
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoetryServiceTest {
    @Resource
    private PoetryService poetryService;

    @Test
    public void add(){
        List<Poetry> list = new ArrayList<>();
        for(int i=0;i<100000;i++){
            Poetry poetry = new Poetry();
            poetry.setTitle("蝶恋花·伫倚危楼风细细"+i);
            poetry.setContent("伫倚危楼风细细，望极春愁，黯黯生天际。\n" +
                    "草色烟光残照里，无言谁会凭阑意。\n" +
                    "拟把疏狂图一醉，对酒当歌，强乐还无味。\n" +
                    "衣带渐宽终不悔，为伊消得人憔悴。");
            poetry.setAuthor("柳永");
            poetry.setDynasty("中国");
            poetry.setCreatedBy("xf");
            //poetryService.save(poetry);
            list.add(poetry);
        }
        Long time1 = System.currentTimeMillis();
        poetryService.addList(list);
        System.out.println(System.currentTimeMillis()-time1);
    }
}
