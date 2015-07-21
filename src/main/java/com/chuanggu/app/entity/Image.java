package com.chuanggu.app.entity;

public class Image {

	private int x;

	private int y;


	private int width;

	private int height;
	
	private int widthZoom;

	private int heightZoom;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidthZoom() {
		return widthZoom;
	}

	public void setWidthZoom(int widthZoom) {
		this.widthZoom = widthZoom;
	}

	public int getHeightZoom() {
		return heightZoom;
	}

	public void setHeightZoom(int heightZoom) {
		this.heightZoom = heightZoom;
	}
}
