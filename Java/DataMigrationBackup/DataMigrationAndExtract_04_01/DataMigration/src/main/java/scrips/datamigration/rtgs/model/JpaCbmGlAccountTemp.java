package scrips.datamigration.rtgs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cbm_gl_account_temp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JpaCbmGlAccountTemp {
		@Id
		private String id;
		@Column(name="gl_account_indicator")
		private String glAccountIndicator;
		@Column(name="gl_account")
		private String glAccount;
		@Column(name="gl_account_description")
		private String glAccountDescription;
		@Column(name="created_date")
		private Date createdDate;
		@Column(name="modified_date")
		private Date modifiedDate;
		private String remarks;
		
}