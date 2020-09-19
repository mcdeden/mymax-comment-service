package com.mymax.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymax.entity.ProspectingCustomerProgressComment;
import com.mymax.exception.rest.StoreException;
import com.mymax.helper.DateTimeHelper;
import com.mymax.model.request.CommentRegisterRequestDTO;
import com.mymax.model.response.CommentRegisterResponseDTO;
import com.mymax.repository.MasterReligionRepository;
import com.mymax.repository.ProspectingCustomerProgressCommentRepository;
import com.mymax.repository.ProspectingCustomerProgressRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	ProspectingCustomerProgressRepository prospectingCustomerProgressRepository;
	@Autowired
	MasterReligionRepository masterReligionRepository;
	@Autowired
	private ProspectingCustomerProgressCommentRepository prospectingCustomerProgressCommentRepository;
	
	@Override
	public CommentRegisterResponseDTO create(CommentRegisterRequestDTO request) {
		
		ProspectingCustomerProgressComment newData = new ProspectingCustomerProgressComment();
		String prospectingCustomerProgressCommentId = "PCC" + DateTimeHelper.getCurentTimestamp();
		LocalDate currDate = LocalDate.now();
		LocalTime currTime = LocalTime.now();
		
		newData.setId(prospectingCustomerProgressCommentId);		
		newData.setCommentDate(currDate);
		newData.setCommentTime(currTime);
		newData.setComment(request.getComment());		
		
		try {
			prospectingCustomerProgressCommentRepository.save(newData);
		} catch (Exception e) {
			throw new StoreException(e.getLocalizedMessage());
		}
		
		CommentRegisterResponseDTO response = new CommentRegisterResponseDTO();
		response.setCommentId(prospectingCustomerProgressCommentId);
		response.setProspectingCustomerProgressId(request.getProspectingCustomerProgressId());
		response.setCommentDate(currDate);
		response.setCommentTime(currTime);
		response.setComment(request.getComment());
		
		return response;
	}

	
	@Override
	public CommentRegisterResponseDTO findOneById(String id) {
		CommentRegisterResponseDTO response = new CommentRegisterResponseDTO();
		
		Optional<ProspectingCustomerProgressComment> optionalData =  prospectingCustomerProgressCommentRepository.findById(id);
		if (optionalData.isPresent()) {
			response.setCommentId(optionalData.get().getId());
			response.setProspectingCustomerProgressId(optionalData.get().getProgress().getId());
			response.setCommentDate(optionalData.get().getCommentDate());
			response.setCommentTime(optionalData.get().getCommentTime());
			response.setComment(optionalData.get().getComment());			
		} else {
			response = null;
		}
		
		return response;
	}

	
	@Override
	public void deleteOneById(String id) {
		prospectingCustomerProgressCommentRepository.deleteById(id);
	}


	@Override
	public CommentRegisterResponseDTO update(String id, CommentRegisterRequestDTO request) {
		LocalDate currDate = LocalDate.now();
		LocalTime currTime = LocalTime.now();
		Optional<ProspectingCustomerProgressComment> updateData = prospectingCustomerProgressCommentRepository.findById(id);

	    if (updateData.isPresent()) {
	    	updateData.get().setCommentDate(currDate);
	    	updateData.get().setCommentTime(currTime);
	    	updateData.get().setComment(request.getComment());		
			
			try {
				prospectingCustomerProgressCommentRepository.save(updateData.get());
			} catch (Exception e) {
				throw new StoreException(e.getLocalizedMessage());
			}
			
			CommentRegisterResponseDTO response = new CommentRegisterResponseDTO();
			response.setCommentId(id);
			response.setProspectingCustomerProgressId(request.getProspectingCustomerProgressId());
			response.setCommentDate(currDate);
			response.setCommentTime(currTime);
			response.setComment(request.getComment());
			
			return response;
			
	    } else {
	      return null;
	    }
	}	
	
	@Override
	public Boolean isExistById(String id) {
		if (prospectingCustomerProgressRepository.findById(id).isPresent()) {
			return true;
		} 
		
		return false;
	}

}
