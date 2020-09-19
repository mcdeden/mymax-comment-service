package com.mymax.service;

import com.mymax.model.request.CommentRegisterRequestDTO;
import com.mymax.model.response.CommentRegisterResponseDTO;

public interface CommentService {

	CommentRegisterResponseDTO create(CommentRegisterRequestDTO request);
	CommentRegisterResponseDTO findOneById(String id);
	void deleteOneById(String id);
	CommentRegisterResponseDTO update(String id, CommentRegisterRequestDTO request);
	Boolean isExistById(String id);
	
}
