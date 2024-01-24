package com.rocky.boot.service;


import com.rocky.boot.model.Person;

/**
 * @author rocky
 */
public interface DemoService {
	/**
	 * save
	 * @param person person
	 * @return Person
	 */
	Person save(Person person);

	/**
	 * remove
	 * @param id id
	 */
	void remove(Long id);

	/**
	 * findOne
	 * @param person person
	 * @return Person
	 */
	Person findOne(Person person);

}
