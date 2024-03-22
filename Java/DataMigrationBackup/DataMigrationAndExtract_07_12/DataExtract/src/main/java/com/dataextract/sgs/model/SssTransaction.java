package com.dataextract.sgs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ssss_txn")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SssTransaction {
	@Id
	@Column(name = "SST_SSSS_REF_NO")
	private String sstSsssRefNo;
	@Column(name = "SST_ISIN")
	private String sstIsin;
	@Column(name = "SST_TXN_TYPE")
	private String sstTxnType;
	@Column(name = "SST_SETT_DATE")
	private String sstSettDate;
	@Column(name = "SST_TRAD_DATE")
	private String sstTradDate;
	@Column(name = "SST_SETT_AMT")
	private Integer sstSettAmt;
	@Column(name = "SST_NOM_AMT")
	private Integer sstNomAmt;
	@Column(name = "SST_DELV_AGENT")
	private String sstDelvAgent;
	@Column(name = "SST_RECV_AGENT")
	private String sstRecvAgent;
	@Column(name = "SST_UPD_DT_STAMP")
	private Date sstUpdDtStamp;
	@Column(name = "SST_RECV_CUSTODY")
	private String sstRecvCustody;
	@Column(name = "SST_DELV_CUSTODY")
	private String sstDelvCustody;
	@OneToOne(mappedBy="transaction")
	private SssTransactionMapping transactionMapping;
}
