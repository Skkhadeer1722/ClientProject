package com.dataextract.cas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gl_cc_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(CbmCostCentreId.class)
public class CbmCostCentre {
	@Id
	@Column(name = "GLC_CC_CODE")
	private String glcCcCode;
	@Id
	@Column(name = "GLC_GL_ACC_NO")
	private String glcGlAccNo;
}
