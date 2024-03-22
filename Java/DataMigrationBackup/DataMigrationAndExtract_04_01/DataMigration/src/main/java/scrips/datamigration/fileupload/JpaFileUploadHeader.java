package scrips.datamigration.fileupload;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="FILE_UPLOAD_HEADER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JpaFileUploadHeader implements Serializable{
  
	private static final long serialVersionUID = 1L;
	@Id
    private Integer fileUploadId;
    private String fileUploadCode;
    private String ipAddress;
    private String filePath;
    private String fileName;
    private String draftTableName;
    private String liveTableName;
    private String fileDelimiter;
    private Integer fileHeader;
    private Integer isActive;
    private String procedureName;

}
