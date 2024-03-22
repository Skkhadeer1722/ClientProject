package scrips.datamigration.jpa.cbm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cbm_sora_rate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(JpaCbmSoraRateId.class)
public class JpaCbmSoraRate {
	@Id
	@Column(name="reference_rate")
	private String referenceRate;
	@Id
	@Column(name="value_date")
	private Integer valueDate;
	@Column(name="publication_date")
	private Integer publicationDate;
	@Column(name="rate")
	private Double rate;
	@Column(name = "action")
	private String action;
	@Column(name = "status")
	private String status;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "modified_date")
	private Date modifiedDate;
	@Column(name = "approved_by")
	private String approvedBy;
	@Column(name = "approved_date")
	private Date approvedDate;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "effective_date")
	private Date effectiveDate;
	@Column(name = "approval_remark")
	private String approvalRemark;
	@Column(name = "workflow_status_id")
	private Integer workflowStatusId;
}
