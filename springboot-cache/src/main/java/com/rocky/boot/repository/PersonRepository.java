package com.rocky.boot.repository;

import com.rocky.boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rocky
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
	

}
