package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaMemberLiaison;

public interface MemberLiaisonDAO extends JpaRepository<JpaMemberLiaison, Long>{

}
