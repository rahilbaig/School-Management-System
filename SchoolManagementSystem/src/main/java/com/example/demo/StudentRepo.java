package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepo extends CrudRepository<Student, Integer> {

	@Query(value = "select * from student where name=?1 and id=?2",nativeQuery = true)
	public Student getByIdOrName(String name,Integer id);
	
	@Query(value = "select * from student where name=?1 and id=?2",nativeQuery = true)
	public Student deletestu(String name,Integer id);
}
