package com.mymax.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CommentRegisterRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@NotBlank(message="is required.")
	private String prospectingCustomerProgressId;
	
	@NotBlank(message="is required.")
	private String commentUserId;	
	
	@NotBlank(message="is required.")
	@Size(min=3, max=200, message="must be 3-200 characters long.")
	private String comment;	
	
}
