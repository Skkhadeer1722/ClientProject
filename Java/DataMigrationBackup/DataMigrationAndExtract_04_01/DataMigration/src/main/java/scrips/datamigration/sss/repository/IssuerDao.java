package scrips.datamigration.sss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.sss.model.JpaIssuer;

public interface IssuerDao extends JpaRepository<JpaIssuer, String>{

	List<JpaIssuer> findByIssuerCode(String issuerCode);
}
