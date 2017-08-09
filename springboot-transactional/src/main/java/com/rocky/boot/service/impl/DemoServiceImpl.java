package com.rocky.boot.service.impl;


import com.rocky.boot.model.Person;
import com.rocky.boot.repository.PersonRepository;
import com.rocky.boot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	PersonRepository personRepository; //1 直接注入PersonRepository的bean
	
	@Transactional(rollbackFor={IllegalArgumentException.class}) //2 指定特定异常时，数据回滚
	public Person savePersonWithRollBack(Person person){
		Person p =personRepository.save(person);

		if(person.getName().equals("汪云飞")){
			throw new IllegalArgumentException("汪云飞已存在，数据将回滚"); //3 硬编码手动触发异常
		}
		return p;
	}

	@Transactional(noRollbackFor={IllegalArgumentException.class}) //4 指定特定异常时，数据不回滚
	public Person savePersonWithoutRollBack(Person person){
		Person p =personRepository.save(person);
		
		if(person.getName().equals("汪云飞")){
			throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚");
		}
		return p;
	}
}
