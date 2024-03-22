package com.dataextract.sgs.model;

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

public class SssAllotmentModel {
	private String almIsin;
	private String almAcutionDate;
	private String almUpdDtStamp;
	private Double aldAlmtPrice;
	private Integer aldNomAmt;
	private String almFirstAlmt;
	private Integer aldSettAmt;
//	private String almStat;
}