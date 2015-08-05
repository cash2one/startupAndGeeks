package com.chuanggu.app.util;

public enum ItemState {
	    Created("1", "生成"),
		Waiting4Verify("2", "待审核"),
		Waiting4Roadshow("3", "待路演"),
		RoadShow("4", "路演完成"),
		FianceDone("5", "融资完成");
		
		private final String value;
		private final String name;
		
		private ItemState(String value, String name) {
			this.value = value;
			this.name = name;
		}
		public String value() {
			return this.value;
		}
		public String getName() {
			return name;
		}
		
}
