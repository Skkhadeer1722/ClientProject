package scrips.datamigration.sss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.sss.model.JpaTaxScheme;

public interface TaxSchemeDAO extends JpaRepository<JpaTaxScheme, String> {
	List<JpaTaxScheme> findByTaxSchemeId(String taxSchemeId);

}