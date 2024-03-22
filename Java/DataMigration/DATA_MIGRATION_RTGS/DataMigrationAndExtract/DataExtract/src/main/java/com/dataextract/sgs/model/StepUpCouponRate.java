package com.dataextract.sgs.model;

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
@Table(name = "stepup_coupon_rate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StepUpCouponRate {
	@Id
	@Column(name = "SCR_ISIN_CODE")
	private String scrIsinCode;
	@Column(name = "SCR_COUPON_DATE")
	private String scrCouponDate;
	@Column(name = "SCR_COUPON_RATE")
	private Double scrCouponRate;
}
