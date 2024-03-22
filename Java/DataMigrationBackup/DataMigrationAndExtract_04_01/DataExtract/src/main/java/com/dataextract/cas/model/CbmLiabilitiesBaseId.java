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
public class CbmLiabilitiesBaseId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "LB_MBR_CODE")
	private String lbMbrCode;
	@Column(name = "LB_CCY_CODE")
	private String lbCcyCode;
	@Column(name = "LB_TYPE")
	private String lbType;
}
