package it.jac.blog.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.jac.blog.enums.Category;
import it.jac.blog.enums.Status;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table
@Getter
@Setter
public class Article extends AuditModel {
	@Column(length = 50)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String text;

	@Column
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "article_tag", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "article_id")
	private List<Comment> comments;

	@ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "article_category", joinColumns = @JoinColumn(name = "article_id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private List<Category> categories;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
}
