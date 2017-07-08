package com.chulung.tank.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 * @说明 元素唯一的链表
 * @作者 chulung
 * @创建时间 2014年2月11日 上午9:56:22
 * @遗留问题
 * @param <E>
 */
public class SetLinkList<E> extends LinkedList<E> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 添加时判断元素是否存在 存在则不添加 不存在添加到结尾
	 */
	public boolean add(E e) {
		if (indexOf(e) == -1) {
			return super.add(e);
		}
		return false;
	}
	
	/**
	 * 
	 * @return 最后一个元素 没有则返回null
	 */
	public E get() {
		try {
		return super.getLast();
		} catch (NoSuchElementException e){
			return null;
		}
	}
}
