package com.dataextract.sgs.model;

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
@Table(name = "almt_mas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(SssAllotmentId.class)
public class SssAllotment {
	@Id
	@Column(name = "ALM_ISIN")
	private String almIsin;
	@Id
	@Column(name = "ALM_ALMT_DATE")
	private String almAlmtDate;
	@Id
	@Column(name = "ALM_ISIN_TYPE")
	private String almIsinType;
	@Column(name = "ALM_AUCTION_DATE")
	private String almAcutionDate;
	@Column(name = "ALM_FIRST_ALMT")
	private String almFirstAlmt;
	@Column(name = "ALM_STAT")
	private String almStat;
	@Column(name = "ALM_UPD_DT_STAMP")
	private String almUpdDtStamp;
}