package com.rocky.boot.service;


import com.rocky.boot.model.Person;

public interface DemoService {
	public Person savePersonWithRollBack(Person person);
	public Person savePersonWithoutRollBack(Person person);

}
