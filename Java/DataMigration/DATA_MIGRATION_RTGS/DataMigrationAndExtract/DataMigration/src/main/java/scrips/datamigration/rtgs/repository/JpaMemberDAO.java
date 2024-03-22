package scrips.datamigration.rtgs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaMember;

public interface JpaMemberDAO extends JpaRepository<JpaMember, Long>{

	JpaMember findBymemberCode(String memberCode);

	List<JpaMember> findByMemberCode(String memberId);

}

