package scrips.datamigration.jpa.fileupload;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for MEMBER_TEMP DB table
 * @author Siva Kuruva
 */

@Transactional
public interface FileUploadExecutionRepository extends JpaRepository<JpaFileUploadExecution,Long>{
}
