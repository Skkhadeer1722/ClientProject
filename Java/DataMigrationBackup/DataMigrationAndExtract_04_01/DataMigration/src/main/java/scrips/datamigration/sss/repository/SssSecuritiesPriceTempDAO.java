package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesPriceTemp;
@Repository
public interface SssSecuritiesPriceTempDAO extends JpaRepository<JpaSssSecuritiesPriceTemp, String>{

}

