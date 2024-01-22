package com.rocky.boot.mybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rocky.boot.mybatis.core.Result;
import com.rocky.boot.mybatis.core.ResultGenerator;
import com.rocky.boot.mybatis.model.Person;
import com.rocky.boot.mybatis.service.IPersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author Rocky
 * @date 2017-08-14
 */
@Controller
@RequestMapping("/people")
public class PersonController {

    @Resource
    private IPersonService personService;

    @GetMapping
    @ResponseBody
    public Result queryAll(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Person> personList = personService.queryAll();
        return ResultGenerator.genSuccessResult(new PageInfo<>(personList));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result queryById(@PathVariable int id) {
        return ResultGenerator.genSuccessResult(personService.queryById(id));
    }
}
