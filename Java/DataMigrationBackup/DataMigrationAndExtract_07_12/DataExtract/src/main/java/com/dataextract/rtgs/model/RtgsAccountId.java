package com.dataextract.rtgs.model;

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

public class RtgsAccountId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "AD_ACC_NO")
	private String adAccNo;
	@Column(name = "AD_MBR_CODE")
	private String adMbrCode;
	@Column(name = "AD_CCY_CODE")
	private String adCcyCode;

}
