package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
@Component
public interface TeacherRepo extends CrudRepository<Teacher, Integer> {

	@Query(value="select * from teacher where email=?1 or username=?2",nativeQuery = true) 
	public Teacher validate(String email,String password);
}
