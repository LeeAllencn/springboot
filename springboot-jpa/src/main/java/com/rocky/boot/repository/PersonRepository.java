package com.rocky.boot.repository;

import com.rocky.boot.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Rocky
 * @date 2017/8/6
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * 根据地址查询
     * @param address 地址
     * @return List
     */
    List<Person> findByAddress(String address);

    /**
     * 根据名称和地址查询
     * @param name 名称
     * @param address 地址
     * @return Person
     */
    Person findByNameAndAddress(String name,String address);

    /**
     * 使用@Query查询,参数按照名称绑定
     * @param name 名称
     * @param address 地址
     * @return Person
     */
    @Query("select p from Person p where p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name")String name, @Param("address")String address);

    /**
     * 使用@NamedQuery查询，注意在实体类中做@NamedQuery的定义
     * @param name 名称
     * @param address 地址
     * @return Person
     */
    Person withNameAndAddressNamedQuery(String name,String address);

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return Page
     */
    @Override
    Page<Person> findAll(Pageable pageable);

    /**
     * 根据年龄分页查询
     * @param age 年龄
     * @param pageable 分页参数
     * @return Page
     */
    Page<Person> findByAge(int age, Pageable pageable);
}
