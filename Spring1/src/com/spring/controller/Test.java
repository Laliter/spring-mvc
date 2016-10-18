package com.spring.controller;

import java.util.ArrayList;

public class Test {
	
	//定义一个静态的字符串变量
	public static String static_string = "";

	public static void main(String[] args) {

		System.out.println( 00000100&00000101);
		/*
		 * 答案：
		 * 5&4 = 4
		 * 
		 * 解答过程：先把5和4转化成二进制，算出结果（二进制）
		 * 然后把二进制转化成10进制，就是最终的结果。
		 * 
		 * 1，首先转换成2进制，5转化成二进制是00000101,4转化成二进制是00000100 
		 * 2，然后按位与操作，"同1则1，遇0则0,"，意思就是按位进行比较，如下图，
		 * 如果上下2个都是1，则记1；
		 * 如果上下2位有一个是0，则记0；
		 * 
		 * 00000101 （这是5）
		 * 00000100 （这是4）
		 * 00000100 （这就是结果，然后转化成10进制，就是4）
		 */
		//测试输出
		System.out.println( 5&4); // = 4
		System.out.println( 4&5); // = 4
		
		System.out.println( 5|4); // = 4
		System.out.println( 4|5);
		
		System.out.println(72993/100 + 1);
		
		//调用交换字符的方法
		String x = "jobs";
		String y = "Amiercan";
		
		exchangeTwoChar(x,y);

		String stringTest = "ABC";
		
		exchangeString_1(stringTest);
		exchangeString_2(stringTest);
		exchangeString_3(stringTest);
		System.out.println("exchangeString_1(stringTest):" + exchangeString_1(stringTest));
		System.out.println("exchangeString_2(stringTest):" + exchangeString_2(stringTest));
		System.out.println("exchangeString_3(stringTest):" + exchangeString_3(stringTest));
		

	}
	
	/*
	 * 写一个方法，交换2个字符的顺序
	 * 1，我们可以用中间变量；
	 * 2，我们可以用数值相加减；
	 * 3，我们可以用移位运算符
	 */
	
	public static void exchangeTwoChar(String x,String y){
		//定义一个临时变量temp
		String temp;
		temp = x;
		x = y;
		y =temp;
		
		System.out.println("x = " + x);
		System.out.println("y = " + y);

	}
	
	/*
	 * 问题：如何反向输出字符串CHINA
	 * 1，用StringBuffer
	 */
	public static StringBuffer exchangeString_1(String str){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length();i++){
			sb.append(str.charAt(str.length() - (i+1)));
		}
		return sb;
	}
	/*
	 * 问题：如何反向输出字符串CHINA
	 * 2，用StringBuffer的reverse()方法
	 */
	public static String exchangeString_2(String str){
		StringBuffer sb = new StringBuffer(str);
		StringBuffer result = sb.reverse();
		//for(int i=0;i<str.length();i++){
		//	sb.append(str.charAt(str.length() - (i+1)));
		//}
		return result.toString();
	}
	
	/*
	 * 问题：如何反向输出字符串CHINA
	 * 3，用数组arraylist
	 */
	public static String exchangeString_3(String str){
		//定义一个ArrayList
		ArrayList<Character> al = new ArrayList<Character>();
		//把字符串str分割成char以后，存入ArrayList
		for(int i=0;i<str.length();i++){
			al.add(i, str.charAt(str.length()-(i+1)));
		}
		//将字符从ArrayList中取出，然后拼接成字符串
		for(int j=0;j<al.size();j++){
			//这里用到了递归调用，
			//static_string是定义的静态的全局字符串变量
			//public static String static_string = "";
			static_string = static_string.concat(al.get(j).toString());
		}
		return static_string;
	}

}