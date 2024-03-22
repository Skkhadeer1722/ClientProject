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
@Table(name = "stf_depo_rate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CbmDepositRate {
	@Id
	@Column(name = "SDR_PERIOD")
	private String sdrPeriod;
	@Column(name = "SDR_DEPO_RATE")
	private Double sdrDepoRate;
}
