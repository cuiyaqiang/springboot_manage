/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.guye.sun.common.utils.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.guye.sun.common.utils.collect.ListUtils;
import org.dozer.DozerBeanMapper;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 *  
 * 1. 持有Mapper的单例. 
 * 2. 返回值类型转换.
 * 3. 批量转换Collection中的所有对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 
 * @author calvin
 * @version 2013-01-15
 */
public class BeanMapper {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = ListUtils.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}
	
	public static void main(String[] args) {
		/*City city = new City();
		city.setId(222L);
		city.setCityName("郑州市");*/
		
		/*CityVO cityVO = new CityVO();
		BeanMapper.copy(city, cityVO);
		System.out.println(cityVO.getCityName()+cityVO.getId());*/
		
		/*Map cityVO = BeanMapper.map(city, Map.class);
		System.out.println(cityVO.get("cityName"));*/
		
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		set.add("e");
		set.add("f");
		List list = BeanMapper.mapList(set, List.class);
		System.out.println(list.size());
	}
}