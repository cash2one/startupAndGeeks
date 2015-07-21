package com.cmcc.edu.util;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.whalin.MemCached.MemCachedClient;

public class MemcacheUtil{
	
	static MemCachedClient memCachedClient;
	
	static{
		memCachedClient = SpringContextHolder.getBean("memCachedClient");
	}

	

	/**
	 * 移除缓存
	 * @param key
	 */
	public static boolean removeCached(String key) {
		return memCachedClient.delete(key);
	}

	/**
	 * 存入缓存
	 * @param key
	 * @param value
	 */
	public static void addCached(String key, Object value) {
		memCachedClient.add(key, value, Integer.MAX_VALUE);
	}

	/**
	 * 存入缓存
	 * @param key
	 * @param value
	 * @param time
	 */
	public static  void addCached(String key, Object value, Date exTime) {
		memCachedClient.add(key, value, exTime);
	}

	/**
	 * 放入缓存
	 * 
	 * @param key
	 * @param value
	 */
	public static void setCached(String key, Object value) {
		memCachedClient.set(key, value, Integer.MAX_VALUE);
	}

	/**
	 * 放入缓存并且指定缓存时间
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void setCached(String key, Object value, int time) {
		memCachedClient.set(key, value, time);
	}

	/**
	 * 放入缓存并且指定缓存时间
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void setCached(String key, Object value, Date time) {
		memCachedClient.set(key, value, time);
	}

	/**
	 * 取缓存
	 * @param key
	 * @return
	 */
	public static Object getCached(String key) {
		return memCachedClient.get(key);
	}

	/**
	 * 清空缓存 慎用
	 */
	public static void clearCached() {
		memCachedClient.flushAll();
	}

	/**
	 * 从memcached 中检索以指定key为开头的一组key值
	 */
	@SuppressWarnings("rawtypes")
	public static Set filterCachedKey(String keyStart) {
		Set<String> keys = new HashSet<String>();
		try {
			int limit = 0;
			Map<String, Integer> dumps = new HashMap<String, Integer>();
			Map slabs = memCachedClient.statsItems();
			if (slabs != null && slabs.keySet() != null) {
				Iterator itemsItr = slabs.keySet().iterator();
				while (itemsItr.hasNext()) {
					String server = itemsItr.next().toString();
					Map itemNames = (Map) slabs.get(server);
					Iterator itemNameItr = itemNames.keySet().iterator();
					while (itemNameItr.hasNext()) {
						String itemName = itemNameItr.next().toString();
						String[] itemAtt = itemName.split(":");
						if (itemAtt[2].startsWith("number"))
							dumps.put(itemAtt[1], Integer.parseInt(itemAtt[1]));
					}
				}
				if (!dumps.values().isEmpty()) {
					Iterator<Integer> dumpIter = dumps.values().iterator();
					while (dumpIter.hasNext()) {
						int dump = dumpIter.next();
						Map cacheDump = memCachedClient.statsCacheDump(dump,limit);
						Iterator entryIter = cacheDump.values().iterator();
						while (entryIter.hasNext()) {
							Map items = (Map) entryIter.next();
							Iterator ks = items.keySet().iterator();
							while (ks.hasNext()) {
								String k = (String) ks.next();
								if (k.startsWith(keyStart)
										&& memCachedClient.keyExists(k)) {
									keys.add(k);
								}
							}
						}
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return keys;
	}
}
