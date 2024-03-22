package scrips.datamigration.sss.model;

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
public class JpaSssSecuritiesFileSource {
	private String IsInCd;

	private String description;
	private String isinType;
	private String issueCode;
	private String isinStatus;
	private String taxable;
	private String tradable;
	private String creditRating;
	private String eligibleForIlf;
	private String ilfHaircutSetting;
	private String haircutPercentageForIlf;
	private String maturityDate;
	private String optionalMaturityDate;
	private String auctionDate;
	private String firstIssuanceDate;
	private String firstIssuanceAmount;
	private String reOpeningAuctionDate;
	private String reOpeningAllotmentDate;
	private String reOpeningTotalAllotmentAmount;
	private String totalIssuedAmount;
	private String outstandingNominalAmount;
	private String amountAllocatedToMas;
	private String amountAllocatedToOthers;
	private String masAppliedAmount;
	private String unitSize;
	private String denomination;
	private String currencyCode;
	private String redemptionPrice;
	private String couponRate;
	private String exDatePeriod;
	private String exDate;
	private String couponPaymentFrequency;
	private String lastCouponDate;
	private String nextCouponDate;
	private String optionalCouponDate;
	private String daysOfAccruedInterest;
	private String tenure;
	private String averageYield;
	private String cutoffYield;
	private String averageYieldPrice;
	private String cutoffYieldPrice;
	private String reOpeningMasAppliedAmount;
	private String totalRedeemedAmount;
	private String isinSubType;
}
