package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.sss.model.JpaCouponShedules;

public interface CouponScheduleDAO extends JpaRepository<JpaCouponShedules,String>{

}
