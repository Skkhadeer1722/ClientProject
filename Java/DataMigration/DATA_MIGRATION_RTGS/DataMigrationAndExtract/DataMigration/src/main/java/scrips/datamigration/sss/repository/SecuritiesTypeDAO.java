package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.sss.model.JpaSecuritiesType;

public interface SecuritiesTypeDAO extends JpaRepository<JpaSecuritiesType, String> {

}