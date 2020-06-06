package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
	
	@Query("from Status where username=?1 order by id")
	List<Status> findByUsernameSorted(String username);
}
