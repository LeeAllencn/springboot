package com.rocky.boot.controller;

import com.rocky.boot.model.Person;
import com.rocky.boot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@Autowired
	DemoService demoService;
	
	@RequestMapping("/rollback")
	public Person rollback(Person person){ //1 回滚情况
		
		return demoService.savePersonWithRollBack(person);
	}
	
	@RequestMapping("/norollback")
	public Person noRollback(Person person){//2 不回滚情况
		
		return demoService.savePersonWithoutRollBack(person);
		
		
	}

}
