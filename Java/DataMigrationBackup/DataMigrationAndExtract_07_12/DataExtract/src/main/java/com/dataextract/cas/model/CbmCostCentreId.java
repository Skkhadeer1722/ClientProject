package com.dataextract.cas.model;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CbmCostCentreId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "GLC_CC_CODE")
	private String glcCcCode;
	@Column(name = "GLC_GL_ACC_NO")
	private String glcGlAccNo;
	
}
