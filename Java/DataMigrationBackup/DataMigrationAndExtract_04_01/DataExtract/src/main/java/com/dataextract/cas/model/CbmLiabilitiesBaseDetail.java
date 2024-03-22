package com.dataextract.cas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "liabilities_base")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CbmLiabilitiesBaseDetail {
	@Id
	@Column(name = "LB_MBR_CODE")
	private String lbMbrCode;	
	@Column(name = "LB_START_DATE")
	private String lbStartDate;
	@Column(name = "LB_END_DATE")
	private String lbEndDate;
	@Column(name = "LB_VALUE")
	private Integer lbValue;
	
}
