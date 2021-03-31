package it.jac.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")

@Entity
@Table
@Getter
@Setter
public class Image extends AuditModel {
	@Column(name = "title", length = 30)
	private String title;

	@Column(name = "src")
	private String src;

	@Column(name = "filename")
	private String filename;

	@Column(name = "alt", length = 30)
	private String alt;
}
