package com.rocky.boot.service;


import com.rocky.boot.model.Person;

public interface DemoService {
	public Person save(Person person);
	
	public void remove(Long id);
	
	public Person findOne(Person person);

}
