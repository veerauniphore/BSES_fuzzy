package com.uniphore.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uniphore.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Integer>{

	List<AddressEntity> findByPincode(String pincode);
}
