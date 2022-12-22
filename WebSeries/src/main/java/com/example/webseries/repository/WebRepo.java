package com.example.webseries.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.webseries.entity.Web;

public interface WebRepo extends JpaRepository<Web, Integer> {
	
	Optional<Web> findBynameLike(String name);
	
	@Modifying
	@Transactional
	@Query("update Web set name = ?1 where id = ?2")
	void updateSeriesName(String name, int id);
	

}
