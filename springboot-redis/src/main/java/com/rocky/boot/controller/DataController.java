package com.rocky.boot.controller;

import com.rocky.boot.dao.PersonDao;
import com.rocky.boot.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class DataController {
	
	@Autowired
	PersonDao personDao;
	
	@RequestMapping("/set") //1 设置字符及对象
	public void set(){
		Person person = new Person("1","wyf", 32);
		personDao.save(person);
		personDao.stringRedisTemplateDemo();
	}
	
	@RequestMapping("/getStr") //2 获得字符
	public String getStr(){
		return personDao.getString();
	}
	
	@RequestMapping("/getPerson") //3 获得对象
	public Person getPerson(){
		return personDao.getPerson();
	}

	@RequestMapping("/uid")
	String uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId();
	}
}
