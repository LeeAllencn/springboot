package com.rocky.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rocky.boot.model.Person;
import com.rocky.boot.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rocky on 2017-08-14.
 */
@Controller
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping
    @ResponseBody
    public PageInfo<Person> queryAll(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Person> personList = personService.queryAll();
        return new PageInfo<Person>(personList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person queryById(@PathVariable int id) {
        return personService.queryById(id);
    }
}
