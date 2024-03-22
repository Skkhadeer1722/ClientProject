package scrips.datamigration.rtgs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cbm_gl_account_source")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JpaCbmGlAccountSource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String id;
	@Column(name = "gl_account")
	private String glAccount;
	@Column(name = "gl_account_indicator")
	private String glAccountIndicator;
	@Column(name = "gl_account_description")
	private String glAccountDescription;
	@Column(name = "created_date")
	private String createdDate;
	@Column(name = "modified_date")
	private String modifiedDate;
	private String remarks;
	@Column(name ="migrated_date")
	private String migratedDate;


}