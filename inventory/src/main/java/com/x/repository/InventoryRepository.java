package com.x.repository;

import java.util.Map;
import java.util.TreeMap;

import com.x.model.Inventory;

public class InventoryRepository {
	private static Map<String, Inventory> map = new TreeMap<>();
	private static Object lock = new Object();
	
	public static Map<String, Inventory> getMap() {
		return map;
	}

	public static Object getLock() {
		return lock;
	}
}
