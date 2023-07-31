package com.uniphore.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniphore.entity.CallDetails;
import com.uniphore.repository.MobileNumber;

@Service
public class AddressValidation {
	
	@Autowired
	MobileNumber mobileNumberRepo;
	
	public String verifyMobileNumber(CallDetails mobile_number, String type) {
		
		String response;
		
		if(type.equalsIgnoreCase("add")) {
			mobileNumberRepo.save(mobile_number);
			response = "Success";
		}else {
		
//		String fromDate = "2023-05-12 18:25:35";
//		String toDate = "2023-07-20 16:01:54";
		LocalDateTime toDate = LocalDateTime.now();
		LocalDateTime fromDate = toDate.minusDays( 7);
        System.out.println("LocalDateTime : " + toDate.minusDays( 7));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(fromDate.withNano(0).toString().replace("T", " "), formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(toDate.withNano(0).toString().replace("T", " "), formatter);
   
		List<String> list = mobileNumberRepo.findByMobile_number(startDateTime, endDateTime, mobile_number.getMobile_number());
		
		System.out.println("date : "+endDateTime);
		System.out.println("Size"+ list.size());
		
		
		if(0 < list.size()) {
			System.out.println("true : ");
			response = "True";
		}else {
			System.out.println("false : ");
			response = "False";
		}
		}
		return response;
	}

}
