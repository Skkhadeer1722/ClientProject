package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.sss.model.JpaSecuritiesCoupon;

public interface SecuritiesCouponDAO extends JpaRepository<JpaSecuritiesCoupon, String> {
	JpaSecuritiesCoupon findBySecuritiesCode(String securitiesCode);

}
