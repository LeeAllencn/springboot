package com.rocky.boot.Repository;

import com.rocky.boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Rocky on 2017/8/6.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    //1 使用方法名查询，接受一个name参数
    List<Person> findByAddress(String name);

    //2 使用方法名查询，接受name和address参数
    Person findByNameAndAddress(String name,String address);

    //3 使用@Query查询，参数按照名称绑定
    @Query("select p from Person p where p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name")String name, @Param("address")String address);

    //4 使用@NamedQuery查询，注意在实体类中做@NamedQuery的定义
    Person withNameAndAddressNamedQuery(String name,String address);
}
