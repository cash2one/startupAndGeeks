package com.chuanggu.app.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chuanggu.app.entity.Resource;

/**
 * 多叉树类
 */
public class MultipleTree {
	
	@SuppressWarnings("rawtypes")
	public static String getTree(List<Resource> dataList) {

		// 节点列表（散列表，用于临时存储节点对象）
		HashMap<Long,Node> nodeList = new HashMap<Long,Node>();
		// 根节点
		Node root = null;
		// 根据结果集构造节点列表（存入散列表）
		for (Resource r:dataList ) {
			Node node = new Node();
			node.id =  r.getId();
			node.name = r.getName();
			node.url = r.getUrl();
			node.parentId = r.getParentId();
			node.type = r.getTypes();
			nodeList.put(node.id, node);
		}
		// 构造无序的多叉树
		Set<?> entrySet = nodeList.entrySet();
		for (Iterator<?> it = entrySet.iterator(); it.hasNext();) {
			Node node = (Node) ((Map.Entry) it.next()).getValue();
			if (node.parentId == 0 || node.parentId.equals("")) {
				root = node;
			} else {
				((Node) nodeList.get(node.parentId)).addChild(node);
			}
		}
		// 输出无序的树形菜单的JSON字符串
		//System.out.println(root.toString());
		// 对多叉树进行横向排序
		root.sortChildren();
		// 输出有序的树形菜单的JSON字符串
		//System.out.println(root.toString());
		
		return root.toString();

	}

}

/**
 * 节点类
 */
class Node {
	/**
	 * 节点编号
	 */
	public Long id;
	/**
	 * 节点内容
	 */
	public String name;
	/**
	 * 请求的URL
	 **/
	public String url;
	/**
	 * 父节点编号
	 */
	public Long parentId;
	/*
	 * 
	 * 节点的类型
	 */
	public String type;
	/**
	 * 孩子节点列表
	 */
	private Children<Node> children = new Children<Node>();

	// 先序遍历，拼接JSON字符串
	public String toString() {
		String result = "{" + "\"id\": " + id  + ",\"name\" :\"" +name + "\", \"url\" : \"" + url;

		if (children != null && children.getSize() != 0) {
			result += "\", \"children\": " + children.toString();
		} else {
			result += "\", \"children\": []"  ;
		}

		return result + "}";
	}

	// 兄弟节点横向排序
	public void sortChildren() {
		if (children != null && children.getSize() != 0) {
			children.sortChildren();
		}
	}

	// 添加孩子节点
	public void addChild(Node node) {
		this.children.addChild(node);
	}
}

/**
 * 孩子列表类
 */
class Children<T> {
	private List<T> list = new ArrayList<T>();

	public int getSize() {
		return list.size();
	}

	public void addChild(T node) {
		list.add(node);
	}

	// 拼接孩子节点的JSON字符串
	public String toString() {
		String result = "[";
		for (Iterator<T> it = list.iterator(); it.hasNext();) {
			result += ((Node) it.next()).toString();
			result += ",";
		}
		result = result.substring(0, result.length() - 1);
		result += "]";
		return result;
	}

	// 孩子节点排序
	@SuppressWarnings("unchecked")
	public void sortChildren() {
		// 对本层节点进行排序
		// 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器
		Collections.sort(list, new NodeIDComparator());
		// 对每个节点的下一层节点进行排序
		for (Iterator<T> it = list.iterator(); it.hasNext();) {
			((Node) it.next()).sortChildren();
		}
	}
}

/**
 * 节点比较器
 */
@SuppressWarnings("rawtypes")
class NodeIDComparator implements Comparator {
	// 按照节点编号比较
	public int compare(Object o1, Object o2) {
		Long j1 =  ((Node) o1).id;
		Long j2 =  ((Node) o2).id;
		return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
	}
}
