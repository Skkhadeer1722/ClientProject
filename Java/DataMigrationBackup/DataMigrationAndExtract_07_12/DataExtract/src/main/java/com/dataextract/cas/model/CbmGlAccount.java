package com.dataextract.cas.model;

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
@Table(name = "gl_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CbmGlAccount {
	@Id
	@Column(name = "GL_ACC_NO")
	private String glAccNo;
	@Column(name = "GL_DESC")
	private String glDesc;
}
