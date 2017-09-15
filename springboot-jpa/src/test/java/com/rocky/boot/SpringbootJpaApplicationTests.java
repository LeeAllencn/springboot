package com.rocky.boot;

import com.rocky.boot.Repository.PersonRepository;
import com.rocky.boot.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void contextLoads() {
	}

	// 基本查询
	@Test
	public void testBaseQuery() throws Exception {
		Person person=new Person(8L,"TT",30,"海口");
		personRepository.findAll();
		personRepository.findOne(8L);
		personRepository.save(person);
		personRepository.delete(person);
		personRepository.count();
		personRepository.exists(8L);
		personRepository.
		// ...
	}
}
