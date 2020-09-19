package com.mymax.model.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class CommentRegisterResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String commentId;
	private String prospectingCustomerProgressId;
	private LocalDate commentDate;
	private LocalTime commentTime;
	private String comment;	
	
}
