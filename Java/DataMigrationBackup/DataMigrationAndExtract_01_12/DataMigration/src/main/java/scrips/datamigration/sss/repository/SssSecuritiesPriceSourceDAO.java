package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesPriceSource;
@Repository
public interface SssSecuritiesPriceSourceDAO extends JpaRepository<JpaSssSecuritiesPriceSource, String> {

}
