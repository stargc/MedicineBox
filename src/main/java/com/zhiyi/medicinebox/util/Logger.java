//package com.zhiyi.medicinebox.util;
//
///**
// * @author guanchen
// * @creation 2016年4月19日 下午1:57:43
// */
//public class Logger {
//
//	// /***
//	// * 用于输出 超严重的错误事；
//	// * 内容会输出在控制台，同时保存在log文件中
//	// * @param classname log发生的类名
//	// * @param message 需要显示的内容
//	// */
//	// public static void fatal(Class classname, String message){
//	// org.apache.log4j.Logger.getLogger(classname).fatal(message);
//	// }
//	//
//	// /***
//	// * 用于输出 超严重的错误事；
//	// * 内容会输出在控制台，同时保存在log文件中
//	// * @param classname log发生的类名
//	// * @param e 需要显示的内容
//	// */
//	// public static void fatal(Class classname, Exception e){
//	// org.apache.log4j.Logger.getLogger(classname).fatal(e);
//	// org.apache.log4j.Logger.getLogger(classname).error(e.getMessage(), e);
//	// }
//
//	/***
//	 * 用于输出 为严重错误 主要是程序的错误； 内容会输出在控制台，同时保存在log文件中
//	 *
//	 * @param classname
//	 *            log发生的类名
//	 * @param message
//	 *            需要显示的内容
//	 */
//	public static void error(Class<?> classname, String message) {
//		org.apache.log4j.Logger.getLogger(classname).error(message);
//	}
//
//	/***
//	 * 用于输出 不为严重错误 主要是程序的错误； 内容会输出在控制台，同时保存在log文件中
//	 *
//	 * @param classname
//	 *            log发生的类名
//	 * @param e
//	 *            需要显示的内容
//	 */
//	public static void error(Class<?> classname, Exception e) {
//		org.apache.log4j.Logger.getLogger(classname).error(e);
//		org.apache.log4j.Logger.getLogger(classname).error(e.getMessage(), e);
//	}
//
//	// /***
//	// * 用于输出 一般警告，比如session丢失；
//	// * 内容会输出在控制台，同时保存在log文件中
//	// * @param classname log发生的类名
//	// * @param message 需要显示的内容
//	// */
//	// public static void warn(Class<?> classname, String message){
//	// org.apache.log4j.Logger.getLogger(classname).warn(message);
//	// }
//	//
//	// /***
//	// * 用于输出 一般警告，比如session丢失；
//	// * 内容会输出在控制台，同时保存在log文件中
//	// * @param classname log发生的类名
//	// * @param e 需要显示的内容
//	// */
//	// public static void warn(Class<?> classname, Exception e){
//	// org.apache.log4j.Logger.getLogger(classname).warn(e);
//	// org.apache.log4j.Logger.getLogger(classname).error(e.getMessage(), e);
//	// }
//
//	/***
//	 * 用于输出一般要显示的信息，比如登录登出； 内容会输出在控制台，同时保存在log文件中
//	 *
//	 * @param classname
//	 *            log发生的类名
//	 * @param message
//	 *            需要显示的内容
//	 */
//	public static void info(Class<?> classname, String message) {
//		org.apache.log4j.Logger.getLogger(classname).info(message);
//	}
//
//	/***
//	 * 用于输出 一般要显示的信息，比如登录登出； 内容会输出在控制台，同时保存在log文件中
//	 *
//	 * @param classname
//	 *            log发生的类名
//	 * @param e
//	 *            需要显示的内容
//	 */
//	public static void info(Class<?> classname, Exception e) {
//		org.apache.log4j.Logger.getLogger(classname).info(e);
//		org.apache.log4j.Logger.getLogger(classname).error(e.getMessage(), e);
//	}
//
//	/***
//	 * 用于输出 程序的调试信息； 内容只会显示在控制台； 只有在log4j.properties中打开debug模式时才会显示。
//	 *
//	 * @param classname
//	 *            log发生的类名
//	 * @param message
//	 *            需要显示的内容
//	 */
//	public static void debug(Class<?> classname, String message) {
//		org.apache.log4j.Logger.getLogger(classname).debug(message);
//	}
//
//	/***
//	 * 用于输出 程序的调试信息 内容只会显示在控制台； 只有在log4j.properties中打开debug模式时才会显示。
//	 *
//	 * @param classname
//	 *            log发生的类名
//	 * @param e
//	 *            需要显示的内容
//	 */
//	public static void debug(Class<?> classname, Exception e) {
//		org.apache.log4j.Logger.getLogger(classname).debug(e);
//		org.apache.log4j.Logger.getLogger(classname).error(e.getMessage(), e);
//	}
//}
