package scrips.datamigration.fileupload;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="FILE_UPLOAD_DETAILS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JpaFileUploadDetails implements Serializable{
   
	private static final long serialVersionUID = 1L;
	@Column(name ="FILE_UPLOAD_ID")
    private Integer fileUploadId;
    @Column(name ="FILE_UPLOAD_CODE")
    private String fileUploadCode;
    @Id
    @Column(name ="SEQUENCE_NO")
    private Integer sequenceNo;
    @Column(name ="FILE_HEADER_NAME")
    private String fileHeaderName;
    @Column(name ="TABLE_FIELD_NAME")
    private String tableFieldName;
    @Column(name ="DATA_TYPE")
    private String dataType;
    @Column(name ="DATA_SIZE")
    private Integer dataSize;
    @Column(name ="IS_NOT_NULL")
    private Integer isNotNull;
    private String dataFormat;
    private String pkTable;
    private String pkField;

   

}
