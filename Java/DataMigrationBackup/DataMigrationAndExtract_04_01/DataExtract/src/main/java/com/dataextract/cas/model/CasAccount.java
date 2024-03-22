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
@Table(name = "acc_definition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(CasAccountId.class)
public class CasAccount {
	@Id
	@Column(name = "AD_ACC_NO")
	private String adAccNo;
	@Id
	@Column(name = "AD_CCY_CODE")
	private String adCcyCode;
	@Id
	@Column(name = "AD_ACC_TYPE")
	private String adAccType;
	@Column(name = "AD_MBR_CODE")
	private String adMbrCode;
	@Column(name = "AD_DESC")
	private String adDesc;

	@Column(name = "AD_DEFAULT_ACC")
	private String adDefaultAcc;
	@Column(name = "AD_SWIFT_BIC_ADDR")
	private String adSwiftBicAddr;
	@Column(name = "AD_STAT")
	private String adStat;

	@Column(name = "AD_EOD_STAT_SENT")
	private String abEodStatSent;
	@Column(name = "AD_GL_CAT")
	private String adGlCat;
	@Column(name = "AD_GL_ACC_CC")
	private String adGlAccCc;
	@Column(name = "AD_GL_ACC_NO")
	private String adGlAccNo;
	@Column(name = "AD_STAT_SENT_MED")
	private String adStatSentMed;

}
