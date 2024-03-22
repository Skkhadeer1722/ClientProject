package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesPrice;
@Repository
public interface SssSecuritiesPriceDAO extends JpaRepository<JpaSssSecuritiesPrice, String>{

	JpaSssSecuritiesPrice findBySecuritiesCode(String securitiesCode);

}

