package com.dataextract.rtgs.model;

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
@Table(name = "acc_definition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(RtgsAccountId.class)
public class RtgsAccount {
	@Id
	@Column(name = "AD_ACC_NO")
	private String adAccNo;
	@Id
	@Column(name = "AD_MBR_CODE")
	private String adMbrCode;
	@Id
	@Column(name = "AD_CCY_CODE")
	private String adCcyCode;
//	@Column(name = "AD_INTR_CR_OPT")
//	private String adIntrCrOpt;
	@Column(name = "AD_TYPE")
	private String adType;
	@Column(name = "AD_STAT")
	private String adStat;


}
