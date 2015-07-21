/**
 * @Title:TreeModel.java
 * @Package:com.yuhoo.power4j.core.controls
 * @Description:TODO
 * @version 1.0
 *
 */
package com.cmcc.edu.util;


public class TreeNode {
	private Long id;
	private Long parentId;
	private String name;
	private String url;
	private String icon;
	private Boolean checked;
	private Boolean isParent;
	private String target;
	private Boolean open;


	
	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Long getId() {
		return id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	

}
