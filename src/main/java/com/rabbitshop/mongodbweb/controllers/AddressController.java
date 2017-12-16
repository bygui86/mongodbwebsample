package com.rabbitshop.mongodbweb.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rabbitshop.mongodbweb.dtos.AddressDto;
import com.rabbitshop.mongodbweb.facades.AddressFacade;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * The Controller implementation is non-public – this is because it doesn’t need to be.
 *
 * Usually, the controller is the last in the chain of dependencies – it receives HTTP requests from the Spring front controller
 * (the DispathcerServlet) and simply delegates them forward to a service layer. If there is no use case where the controller has
 * to be injected or manipulated through a direct reference, then I prefer not to declare it as public.
 *
 * The request mappings are straightforward – as with any controller, the actual value of the mapping, as well as the HTTP method,
 * are used to determine the target method for the request. @RequestBody will bind the parameters of the method to the body of the
 * HTTP request, whereas @ResponseBody does the same for the response and return type.
 *
 * They also ensure that the resource will be marshalled and unmarshalled using the correct HTTP converter. Content negotiation
 * will take place to choose which one of the active converters will be used, based mostly on the Accept header, although other
 * HTTP headers may be used to determine the representation as well.
 */
@Controller
@RequestMapping("/address")
class AddressController {
	
	@Resource
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	private AddressFacade addressFacade;
	
	@GetMapping
	// @ResponseStatus(HttpStatus.OK) // Already default for a GET
	@ResponseBody
	public List<AddressDto> getAll() {

		return getAddressFacade().getAll();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	// @ExceptionHandler
	public AddressDto getById(@PathVariable("id") final String addressId) {

		// TODO improve this check to let it be generic
		if (StringUtils.isEmpty(addressId)) {
			throw new IllegalArgumentException("Address ID must not be null");
		}
		
		// TODO return an Error object if Address not found
		return getAddressFacade().getById(addressId).get();
	}
	
	// TODO test failing insert
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AddressDto create(@RequestBody final AddressDto newAddress) {

		// TODO improve this check to let it be generic
		if (newAddress == null) {
			throw new IllegalArgumentException("Address must not be null");
		}
		if (StringUtils.isEmpty(newAddress.getStreet())) {
			throw new IllegalArgumentException("Address.street must not be null");
		}
		if (newAddress.getStreet().split(" ").length < 2) {
			throw new IllegalArgumentException("Address.street must contain Street name and number separated by a whitespace");
		}
		
		// TODO return an Error object if Address not found
		return getAddressFacade().create(newAddress).get();
	}

	// TODO implement PATCH > (partial)update

	// TODO implement PUT > completeUpdate
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK) // ACCEPTED should be used when an async action is triggered
	public void deleteAll() {
		
		getAddressFacade().removeAll();
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") final String addressId) {

		// TODO improve this check to let it be generic
		if (StringUtils.isEmpty(addressId)) {
			throw new IllegalArgumentException("Address ID must not be null");
		}
		
		getAddressFacade().removeById(addressId);
	}
	
}
