package com.rocky.boot.service.impl;

import com.rocky.boot.model.Person;
import com.rocky.boot.repository.PersonRepository;
import com.rocky.boot.service.DemoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author rocky
 */
@Service
public class DemoServiceImpl implements DemoService {
	
	@Resource
	PersonRepository personRepository;

	/**
	 * 1 @CachePut缓存新增的或者更新的数据到缓存，其中缓存名称为people，数据的key是person的id
	 * @param person person
	 * @return Person
	 */
	@Override
	@CachePut(value = "people", key = "#person.id")
	public Person save(Person person) {
		Person p = personRepository.save(person);
		System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
		return p;
	}

	/**
	 * 2 @CacheEvict从缓存people中删除key为id的数据
	 * @param id id
	 */
	@Override
	@CacheEvict(value = "people")
	public void remove(Long id) {
		System.out.println("删除了id、key为"+id+"的数据缓存");
		//这里不做实际删除操作
	}

	/**
	 * 3 @Cacheable缓存key为person的id数据到缓存people中
	 * @param person person
	 * @return Person
	 */
	@Override
	@Cacheable(value = "people", key = "#person.id")
	public Person findOne(Person person) {
		Person p = personRepository.findOne(person.getId());
		System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
		return p;
	}

}
