package com.uniphore.service;

import java.util.List;

import com.uniphore.dto.AddressDto;
import com.uniphore.dto.OutputDto;

public interface AddressFinderService {

	OutputDto getAddress(AddressDto dto);

	
}
