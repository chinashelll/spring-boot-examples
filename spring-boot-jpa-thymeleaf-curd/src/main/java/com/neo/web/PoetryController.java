package com.neo.web;

/**
 * @author xiefei
 * @create 2018-11-06 2:51 PM
 * @desc
 **/

import com.neo.entity.Poetry;
import com.neo.service.PoetryService;
import com.neo.util.PageUtil;
import com.neo.util.PageVo;
import com.neo.util.Result;
import com.neo.util.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/poetry")
public class PoetryController {

    @Resource
    private PoetryService poetryService;

    @RequestMapping("list")
    public Result<Page> list(Model model, @ModelAttribute PageVo pageVo,
                             @ModelAttribute Poetry poetry){
        Page<Poetry> page = poetryService.serachByCondition(poetry,PageUtil.initPage(pageVo));
        model.addAttribute("list",page);
        return new ResultUtil<Page>().setData(page);
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "poetry/add";
    }

    @RequestMapping("/save")
    public Result<String> save(Model model, @Param("poetry") Poetry poetry){
        poetryService.save(poetry);
        return new ResultUtil<String>().setData("success");
    }

    @RequestMapping("/delete")
    public Result<String> delete(Model model, @Param("ids") String[] ids){
        for (String id : ids) {
            poetryService.delete(id);
        }
        return new ResultUtil<String>().setData("success");
    }

}
