package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ManagerRepo extends CrudRepository<Manager, Integer> {

	@Query(value="select * from manager where email=?1 or username=?2",nativeQuery = true) 
	public Manager validate(String email,String password);
}
