package com.cmcc.edu.entity;

import java.io.Serializable;

public class Resource implements Serializable {
	private Long id;
	private String name;
	private String types;
	private ResourceType type = ResourceType.menu; // 资源类型
	private String url;
	private Long parentId;
	private String parentIds;
	private String permission;
	private Boolean available;
	private String makeSelfAsParentIds;

	private static final long serialVersionUID = 1L;

	public static enum ResourceType {
		menu("菜单"), button("按钮");

		private final String info;

		private ResourceType(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds == null ? null : parentIds.trim();
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String makeSelfAsParentIds() {
		return getParentIds() + getId() + "/";
	}

	public String getMakeSelfAsParentIds() {
		return makeSelfAsParentIds;
	}

	public void setMakeSelfAsParentIds() {
		this.makeSelfAsParentIds = getParentIds() + getId() + "/";
	}

	public boolean isRootNode() {
		return parentId == 0;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;

	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

}
