package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaAccountPositionSource;


public interface JpaAccountPositionSourceDAO extends JpaRepository<JpaAccountPositionSource,String> {

}
