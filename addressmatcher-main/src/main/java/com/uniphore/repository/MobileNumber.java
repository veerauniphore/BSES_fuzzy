package com.uniphore.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uniphore.entity.AddressEntity;
import com.uniphore.entity.CallDetails;

@Repository
public interface MobileNumber extends CrudRepository<CallDetails, Integer> {
	
	
	@Query(value = "SELECT mobile_number FROM call_details WHERE created_dateTime BETWEEN ?1 AND ?2 and mobile_number = ?3", nativeQuery = true)
	List<String> findByMobile_number(LocalDateTime fromDate, LocalDateTime toDate, String mobile_number);
	
//	@Modifying
//	@Query(value = "INSERT INTO call_details (mobile_number) VALUES (?)", nativeQuery = true)
//	int insertData(String mobileNumber);
	
	
	
//	List<AddressEntity> findByMobile_number(String pincode);

//	public void saveAll(String mobileNumber); 
}
