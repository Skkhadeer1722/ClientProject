package scrips.datamigration.rtgs.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.AccountSummaryReportDto;
import scrips.datamigration.rtgs.model.JpaAccount;

@Repository
public interface AccountSummaryReportDAO extends JpaRepository<JpaAccount, String> {

//	@Query("select new scrips.datamigration.reports.dto.AccountSummaryReportDto( b,a from(select count(case when x.accountType ="
//			+ "AGDA" + " then '' else x.accountType end) AS l_count )from JpaAccount x)as b cross join,\r\n"
//			+ " select count(case when s.accountType =" + "AGDA"
//			+ " then '' else s.accountType end) AS field_name) from JpaAccountSource s) as a,\r\n")
//	AccountSummaryReportDto getAccountCount(@Param("date") Date date);

}
