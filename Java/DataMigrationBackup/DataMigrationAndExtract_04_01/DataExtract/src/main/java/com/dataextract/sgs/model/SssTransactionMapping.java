package com.dataextract.sgs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ssss_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SssTransactionMapping {
	@Id
	@Column(name = "SSD_SSSS_REF_NO")
	private String ssdSsssRefNo;
	@Column(name = "SSD_CCY_CODE")
	private String ssdCcyCode;
	@Column(name = "SSD_DEAL_PRICE")
	private String ssdDealPrice;
	@Column(name = "SSD_REL_SSSS_REF_NO")
	private String ssdRelvSsssRefNo;
	@Column(name = "SSD_DELV_RECV_DT_STAMP")
	private String ssdDelvRecvDtStamp;
	@Column(name = "SSD_RECV_MSG_SNDER")
	private String ssdRecvMsgSnder;
	@Column(name = "SSD_RECV_MSG_TYPE")
	private String ssdRecvMsgType;
	@Column(name = "SSD_UPD_DT_STAMP")
	private String ssdUpdDtStamp;
	
	@Column(name = "SSD_ACCRUED_INT")
	private Integer ssdAccruedint;
	@MapsId
	@OneToOne
	@JoinColumn(name="SSD_SSSS_REF_NO",referencedColumnName = "SST_SSSS_REF_NO")
	@JsonIgnore
	private SssTransaction transaction;
}
