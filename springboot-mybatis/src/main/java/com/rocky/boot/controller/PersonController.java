package com.rocky.boot.controller;

import com.rocky.boot.model.Person;
import com.rocky.boot.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Rocky on 2017-08-14.
 */
@Controller
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person queryById(@PathVariable int id) {
        return personService.queryById(id);
    }
}
