package com.zyme.core.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domain_pages")
public class ScrapeData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String domain;
	private String url;
	private String content;
	private String crawl_status;
	private Date created_on;
	private Date modified_on;
	
	public ScrapeData() {
	}

	public ScrapeData(int id, String domain, String url, Date created_on, String content, Date modified_on) {
		super();
		this.id = id;
		this.domain = domain;
		this.url = url;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCrawl_status() {
		return crawl_status;
	}

	public void setCrawl_status(String crawl_status) {
		this.crawl_status = crawl_status;
	}

}
