package com.cmcc.edu.util;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class ZNode implements Serializable {
	/**
	 * 节点ID
	 */
	private String id;

	/**
	 * 节点名称
	 */
	private String name;

	/**
	 * 是否展开
	 */
	private boolean open;

	/**
	 * 是否父节点
	 */
	private boolean isParent;

	/**
	 * 子节点集合
	 */
	private List<ZNode> children;
	
	
	/**
	 * 是否展开
	 */
	private boolean checked;

	/**
	 * 图标
	 */
	private String icon;

	public List<ZNode> getChildren() {
		return children;
	}

	public void setChildren(List<ZNode> children) {
		this.children = children;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
	
	
}
