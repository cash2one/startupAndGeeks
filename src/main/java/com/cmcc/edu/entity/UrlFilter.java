package com.cmcc.edu.entity;

import java.io.Serializable;


public class UrlFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String url;
	private String roles;
	private String permissions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles == null ? null : roles.trim();
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions == null ? null : permissions.trim();
	}



}
