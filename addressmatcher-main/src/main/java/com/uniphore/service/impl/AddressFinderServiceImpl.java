package com.uniphore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniphore.dto.AddressDto;
import com.uniphore.dto.OutputDto;
import com.uniphore.entity.AddressEntity;
import com.uniphore.repository.AddressRepository;
import com.uniphore.service.AddressFinderService;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

@Service
public class AddressFinderServiceImpl implements AddressFinderService {

	@Autowired
	AddressRepository addressrepo;
	
	@Override
	public OutputDto getAddress(AddressDto dto)
	{
		OutputDto returndto= new OutputDto();
		List<String> arealist=new ArrayList<>();
		System.out.println("pincode and area "+  dto.getPincode()+ " "+dto.getArea());
		if(dto.getPincode()!=null && dto.getArea() != null)
		{
			List<AddressEntity> addresslist=addressrepo.findByPincode(dto.getPincode());//   get data by pincode
			List<AddressEntity> addresslineonelist =new ArrayList<>();
			List<String> addresslineonearealist =new ArrayList<>();
			List<AddressEntity> addresslinetwolist =new ArrayList<>();
			List<String> addresslinetwoarealist =new ArrayList<>();
			List<AddressEntity> addresslinethreelist =new ArrayList<>();
			List<String> addresslinethreearealist =new ArrayList<>();
			List<String> tempmatch =new ArrayList<>();
			String addressLineOne = "";
			String addressLineTwo = "";
			String addressLineThree = "";
			for(AddressEntity address: addresslist) {
				
				if(address.getAddresslineone() != null){
					addressLineOne = address.getAddresslineone();
				}
				if(address.getAddresslinetwo() != null){
					addressLineTwo = address.getAddresslinetwo();
				}
				if(address.getAddresslinethree() != null){
					addressLineThree = address.getAddresslinethree();
				}
			
				if(FuzzySearch.ratio(dto.getArea().toLowerCase(),addressLineOne.toLowerCase())==100)
				{
					System.out.println("addressLineOne 100: "+addressLineOne);
					addresslineonelist.add(address); //exact match in address1 
					addresslinetwoarealist.add(addressLineTwo); //picking address2
					addresslinethreearealist.add(addressLineThree); //picking address3
				}
				else if(FuzzySearch.ratio(dto.getArea().toLowerCase(),addressLineOne.toLowerCase())>=60)
				{
					arealist.add(addressLineOne); //near match in address1
				}
				else if(FuzzySearch.ratio(dto.getArea().toLowerCase(),addressLineTwo.toLowerCase())==100)
				{
					System.out.println("addressLineTwo  100: "+addressLineTwo);
					addresslinetwolist.add(address); //exact match in address2 picking address1
					addresslineonearealist.add(addressLineOne);
					addresslinethreearealist.add(addressLineThree);
				}
				else if(FuzzySearch.ratio(dto.getArea().toLowerCase(),addressLineTwo.toLowerCase())>=60)
				{
					System.out.println("addressLineTwo  60: "+addressLineTwo);
					arealist.add(addressLineTwo); 	//near  match in address2
					
				}
				else if(FuzzySearch.ratio(dto.getArea().toLowerCase(),addressLineThree.toLowerCase())==100)
				{
					System.out.println("addressLineThree 100: "+addressLineThree);

					addresslinethreelist.add(address); 
					addresslineonearealist.add(addressLineOne);
					addresslinetwoarealist.add(addressLineTwo);
				}
				else if(FuzzySearch.ratio(dto.getArea().toLowerCase(),addressLineThree.toLowerCase())>=60)
				{
					System.out.println("addressLineThree 60: "+addressLineThree);
					arealist.add(addressLineThree); 	//near  match in address3
					
				}
				
				
			}
			
			if(addresslinetwolist.size()==1)	//only one addresstwo matched
			{
				System.out.println("addresslinetwolist 1: "+addresslinetwolist.size());
				returndto.setStatus("matched");
				tempmatch.add(addresslinetwolist.get(0).getAddresslineone()+"-"+addresslinetwolist.get(0).getAddresslinetwo()+"-"+addresslinetwolist.get(0).getAddresslinethree());
				returndto.setArea(tempmatch);
				returndto.setObject_id(addresslinetwolist.get(0).getObject_id());
			}
			else if(addresslinetwolist.size()>1) //more than one addresstwo matched
			{
				System.out.println("addresslinetwolist 1+: "+addresslinetwolist.size());
				returndto.setStatus("matched");
				if(dto.getMatchedarea()!=null&& !dto.getMatchedarea().isEmpty())//if previous matched data present
				{
					for(AddressEntity entity: addresslinetwolist)
					{
						if(entity.getAddresslineone().toLowerCase().equals(dto.getMatchedarea().toLowerCase()))
						{
							System.out.println("addresslinetwolist 1+: "+entity.getAddresslineone()+"- "+entity.getAddresslinetwo()+"-"+entity.getAddresslinethree());
							tempmatch.add(entity.getAddresslineone()+"-"+entity.getAddresslinetwo()+"-"+entity.getAddresslinethree());
							returndto.setArea(tempmatch);
							returndto.setObject_id(entity.getObject_id());
						}
					}
				}
				else
					returndto.setArea(addresslineonearealist.stream().distinct().collect(Collectors.toList()));// sending address1 
			}
			else if(addresslineonelist.size()==1) //only one address1 matched
			{
				System.out.println("addresslineonelist 1: "+addresslineonelist.size());
				tempmatch.add(addresslineonelist.get(0).getAddresslineone()+"-"+addresslineonelist.get(0).getAddresslinetwo()+"-"+addresslineonelist.get(0).getAddresslinethree());
				returndto.setArea(tempmatch);
				returndto.setStatus("matched");
				returndto.setObject_id(addresslineonelist.get(0).getObject_id());
			}
			else if(addresslineonelist.size()>1)//more than one addressone matched
			{
				System.out.println("addresslineonelist 1: "+addresslineonelist.size());
				returndto.setStatus("matched");
				if(dto.getMatchedarea()!=null&& !dto.getMatchedarea().isEmpty()) //if previous matched data present
				{
					for(AddressEntity entity: addresslineonelist)
					{
						if(entity.getAddresslinetwo().toLowerCase().equals(dto.getMatchedarea().toLowerCase()))
						{
							tempmatch.add(entity.getAddresslineone()+"-"+entity.getAddresslinetwo()+"-"+entity.getAddresslinethree());
							returndto.setArea(tempmatch);
							returndto.setObject_id("");
							returndto.setObject_id(entity.getObject_id());
						}
					}
				}
				else
					returndto.setArea(addresslinetwoarealist.stream().distinct().collect(Collectors.toList()));
			}
			else if(addresslinethreelist.size()==1)	//only one addressthree matched
			{
				System.out.println("addresslinethreelist one" );
				returndto.setStatus("matched");
				tempmatch.add(addresslinethreelist.get(0).getAddresslineone()+"-"+addresslinethreelist.get(0).getAddresslinetwo()+"-"+addresslinethreelist.get(0).getAddresslinethree());
				returndto.setArea(tempmatch);
				returndto.setObject_id(addresslinethreelist.get(0).getObject_id());
			}
			else if(addresslinethreelist.size()>1) //more than one addressthree matched
			{
				
				System.out.println("addresslinethreelist multiple");
				returndto.setStatus("matched");
				if(dto.getMatchedarea()!=null&& !dto.getMatchedarea().isEmpty())//if previous matched data present
				{
					System.out.println("addresslinethreelist 1");
					for(AddressEntity entity: addresslinethreelist)
					{
						if(entity.getAddresslineone().toLowerCase().equals(dto.getMatchedarea().toLowerCase()))
						{
							System.out.println("addresslinethreelist 2");
							tempmatch.add(entity.getAddresslineone()+"-"+entity.getAddresslinetwo()+"-"+entity.getAddresslinethree());
							returndto.setArea(tempmatch);
							returndto.setObject_id(entity.getObject_id());
						}
					}
				}
				else
					returndto.setArea(addresslinetwoarealist.stream().distinct().collect(Collectors.toList()));// sending address1 
			}
			else
			{
				
				returndto.setArea(arealist.stream().distinct().collect(Collectors.toList()));
				if(returndto.getArea().size()>=1) {
					returndto.setStatus("matched");
				}
			}
			return returndto;
		}else if(dto.getPincode()!=null) {
			List<AddressEntity> addresslist=addressrepo.findByPincode(dto.getPincode());//   get data by pincode
			List<String> tempmatch =new ArrayList<>();
			for(AddressEntity address: addresslist) {
			if(addresslist.size() == 1) {
				returndto.setStatus("matched");
				tempmatch.add(address.getAddresslineone()+"-"+address.getAddresslinetwo()+"-"+address.getAddresslinethree());
				returndto.setArea(tempmatch);
				returndto.setObject_id(addresslist.get(0).getObject_id());
				
			}else if(addresslist.size() < 7) {
				returndto.setStatus("matched");
				tempmatch.add(address.getAddresslinethree());
				returndto.setArea(tempmatch);
			}
			else {
				returndto.setStatus("not matched");
			}
			}
		}
		return returndto;
	}
}
