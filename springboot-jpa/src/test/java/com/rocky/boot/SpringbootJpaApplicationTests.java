package com.rocky.boot;

import com.rocky.boot.repository.PersonRepository;
import com.rocky.boot.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void contextLoads() {
	}

	// 基本查询测试
	@Test
	public void testBaseQuery() {
		Person person=new Person(8L,"TT",30,"海口");
		personRepository.findAll();
		personRepository.findOne(8L);
		personRepository.save(person);
		personRepository.delete(person);
		personRepository.count();
		personRepository.exists(8L);
		// ...
	}

	//分页查询测试
	@Test
	public void testPageQuery() {
		int page=0,size=2;
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(page, size, sort);
		Page<Person> record1 = personRepository.findAll(pageable);
		System.out.println(record1);
		Page<Person> record2 = personRepository.findByAge(27, pageable);
		System.out.println(record2);
	}
}
