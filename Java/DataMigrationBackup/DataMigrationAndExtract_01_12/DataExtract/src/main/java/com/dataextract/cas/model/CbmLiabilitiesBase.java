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
@Table(name = "liabilities_base")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(CbmLiabilitiesBaseId.class)
public class CbmLiabilitiesBase {
	@Id
	@Column(name = "LB_MBR_CODE")
	private String lbMbrCode;
	@Id
	@Column(name = "LB_CCY_CODE")
	private String lbCcyCode;
	@Id
	@Column(name = "LB_TYPE")
	private String lbType;
	@Column(name = "LB_UPD_DT_STAMP")
	private String lbUpdDtStamp;
}
