package com.csse.common.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.*;
import java.util.Map;

/**
 * 常用的反射方法
 * 
 * @author shihaizhou
 * @since
 */
public class ReflectionUtil {
	/**
	 * 得到某个对象的公共属性
	 * 
	 * @param owner
	 * @param fieldName
	 * @return 该属性对象
	 */
	public static Object getProperty(Object owner, String fieldName) throws Exception{
		try {
			return BeanUtils.getProperty(owner, fieldName);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 得到某类的静态公共属性
	 * 
	 * @param className
	 *            类名
	 * @param fieldName
	 *            属性名
	 * @return 该属性对象
	 */
	public static Object getStaticProperty(String className, String fieldName) throws Exception{
		try {
			Class<?> ownerClass = Class.forName(className);
			Field field = ownerClass.getField(fieldName);
			Object property = field.get(ownerClass);
			
			return property;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 执行某对象方法
	 * 
	 * @param owner
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数
	 * @return 方法返回值
	 */
	@SuppressWarnings("unchecked")
	public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception{
		Class<? extends Object> ownerClass = owner.getClass();
		
		Class[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method;
		try {
			method = ownerClass.getMethod(methodName, argsClass);
			return method.invoke(owner, args);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 执行某类的静态方法
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @return 执行方法返回的结果
	 */
	public static Object invokeStaticMethod(String className, String methodName) throws Exception{
		return invokeStaticMethod(className, methodName, new Object[0]);
	}

	/**
	 * 执行某类的静态方法
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数数组
	 * @return 执行方法返回的结果
	 */
	@SuppressWarnings("unchecked")
	public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception{
		try {
			Class<?> ownerClass = Class.forName(className);

			Class[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}

			Method method = ownerClass.getMethod(methodName, argsClass);

			return method.invoke(null, args);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 执行某类的静态方法
	 * 
	 * @param
	 *
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数数组
	 * @return 执行方法返回的结果
	 */
	@SuppressWarnings("unchecked")
	public static Object invokeStaticMethod(Class clazz, String methodName, Object[] args)throws Exception {
		try {

			Class[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}

			Method method = clazz.getMethod(methodName, argsClass);

			return method.invoke(null, args);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 新建实例
	 * 
	 * @param className
	 *            类名
	 * @param args
	 *            构造函数的参数
	 * @return 新建的实例
	 */
	@SuppressWarnings("unchecked")
	public static Object newInstance(String className, Object[] args) throws Exception{
		try {
			Class<?> newoneClass = Class.forName(className);
	
			Class[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}
	
			Constructor<?> cons = newoneClass.getConstructor(argsClass);
	
			return cons.newInstance(args);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 新建实例
	 * 
	 * @param className
	 *            类名
	 * @return 新建的实例
	 */
	public static Object newInstance(String className) throws Exception{
		return newInstance(className, new Object[0]);
	}

	/**
	 * 是不是某个类的实例
	 * 
	 * @param obj
	 *            实例
	 * @param cls
	 *            类
	 * @return 如果 obj 是此类的实例，则返回 true
	 */
	public static boolean isInstance(Object obj, Class<?> cls) {
		return cls.isInstance(obj);
	}

	/**
	 * 得到数组中的某个元素
	 * 
	 * @param array
	 *            数组
	 * @param index
	 *            索引
	 * @return 返回指定数组对象中索引组件的值
	 */
	public static Object getByArray(Object array, int index) {
		return Array.get(array, index);
	}
	
	public static Object setObjectProperty(Object object,Map<String,Object> map){
		Method[] mothods = object.getClass().getMethods();
		for(Method method : mothods){
			String methodName = method.getName();
			if(methodName.startsWith("set")){
				String keyFirst = methodName.substring(3,4);
				String keySecond = methodName.substring(4);
				Object value = map.get(keyFirst.toLowerCase()+keySecond);
				if(value != null)
				try {
					method.invoke(object, value);
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {
				}
			}
		}
		return object;
	}
}