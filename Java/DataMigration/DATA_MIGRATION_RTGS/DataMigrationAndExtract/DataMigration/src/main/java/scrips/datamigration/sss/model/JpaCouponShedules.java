package scrips.datamigration.sss.model;

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
@Table(name = "securities_coupon_schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JpaCouponShedules {
	@Id
	@Column(unique = false)
	private int id;
	@Column(name = "securities_code")
	private String securitiesCode;
	@Column(name = "coupon_date")
	private String couponDate;
	@Column(name = "coupon_rate")
	private String couponRate;
//	@OneToOne(cascade = CascadeType.MERGE)
//	@JsonIgnore
//	private JpaSssSecuritiesCode security;
}