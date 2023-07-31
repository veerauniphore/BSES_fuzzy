package com.uniphore.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniphore.dto.AddressDto;
import com.uniphore.dto.OutputDto;
import com.uniphore.entity.CallDetails;
import com.uniphore.service.AddressFinderService;
import com.uniphore.service.AddressValidation;
import com.uniphore.util.PropertiesLoader;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

@RestController
@RequestMapping("/bses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BsesController {

	@Autowired
	AddressFinderService service;
	
	@Autowired
	AddressValidation addressValidation;
	
	
	private static final PropertiesLoader propLoader = PropertiesLoader.getInstance();
	
	@PostMapping("/addressfinder")
	public OutputDto getData(@RequestBody AddressDto dto) 
	{
		
		return service.getAddress(dto);
	}
	
	@PostMapping("/addressValidation")
	public String addressValidation(@RequestBody CallDetails request, @RequestParam("type") String type) 
	{
		 System.out.println("type : " + type);
		return addressValidation.verifyMobileNumber(request,type);
	}
}
