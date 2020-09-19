package com.mymax.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mymax.exception.rest.DataNotFoundException;
import com.mymax.exception.rest.DeleteException;
import com.mymax.model.request.CommentRegisterRequestDTO;
import com.mymax.model.response.CommentRegisterResponseDTO;
import com.mymax.service.CommentService;

@RestController
@RequestMapping("progress")
public class CommentController {

	@Autowired
	private CommentService service;

	@PostMapping(value = "register")
	public ResponseEntity<CommentRegisterResponseDTO> store(@Valid @RequestBody CommentRegisterRequestDTO request) {		

		CommentRegisterResponseDTO response = service.create(request);

		return new ResponseEntity<CommentRegisterResponseDTO>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<CommentRegisterResponseDTO> getDetailProspecting(@PathVariable String id) {		

		CommentRegisterResponseDTO response = service.findOneById(id);

		return new ResponseEntity<CommentRegisterResponseDTO>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteProspecting(@PathVariable String id) {		

		try {
			service.deleteOneById(id);
		    return new ResponseEntity<String>("Data deleted",HttpStatus.NO_CONTENT);
		} catch (Exception e) {
		    throw new DeleteException(e.getLocalizedMessage());
		}
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<CommentRegisterResponseDTO> updateProspecting(@RequestParam String id, @Valid @RequestBody CommentRegisterRequestDTO request) {		

		if (!service.isExistById(id)) {
		   	throw new DataNotFoundException("Prospecting customer with id: " + id + " is not found");
		}	
		
		CommentRegisterResponseDTO response = service.update(id,request);

		return new ResponseEntity<CommentRegisterResponseDTO>(response, HttpStatus.OK);
	}
	
}
