package com.spring.controller;

import java.util.ArrayList;

public class Test {
	
	//����һ����̬���ַ�������
	public static String static_string = "";

	public static void main(String[] args) {

		System.out.println( 00000100&00000101);
		/*
		 * �𰸣�
		 * 5&4 = 4
		 * 
		 * �����̣��Ȱ�5��4ת���ɶ����ƣ��������������ƣ�
		 * Ȼ��Ѷ�����ת����10���ƣ��������յĽ����
		 * 
		 * 1������ת����2���ƣ�5ת���ɶ�������00000101,4ת���ɶ�������00000100 
		 * 2��Ȼ��λ�������"ͬ1��1����0��0,"����˼���ǰ�λ���бȽϣ�����ͼ��
		 * �������2������1�����1��
		 * �������2λ��һ����0�����0��
		 * 
		 * 00000101 ������5��
		 * 00000100 ������4��
		 * 00000100 ������ǽ����Ȼ��ת����10���ƣ�����4��
		 */
		//�������
		System.out.println( 5&4); // = 4
		System.out.println( 4&5); // = 4
		
		System.out.println( 5|4); // = 4
		System.out.println( 4|5);
		
		System.out.println(72993/100 + 1);
		
		//���ý����ַ��ķ���
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
	 * дһ������������2���ַ���˳��
	 * 1�����ǿ������м������
	 * 2�����ǿ�������ֵ��Ӽ���
	 * 3�����ǿ�������λ�����
	 */
	
	public static void exchangeTwoChar(String x,String y){
		//����һ����ʱ����temp
		String temp;
		temp = x;
		x = y;
		y =temp;
		
		System.out.println("x = " + x);
		System.out.println("y = " + y);

	}
	
	/*
	 * ���⣺��η�������ַ���CHINA
	 * 1����StringBuffer
	 */
	public static StringBuffer exchangeString_1(String str){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length();i++){
			sb.append(str.charAt(str.length() - (i+1)));
		}
		return sb;
	}
	/*
	 * ���⣺��η�������ַ���CHINA
	 * 2����StringBuffer��reverse()����
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
	 * ���⣺��η�������ַ���CHINA
	 * 3��������arraylist
	 */
	public static String exchangeString_3(String str){
		//����һ��ArrayList
		ArrayList<Character> al = new ArrayList<Character>();
		//���ַ���str�ָ��char�Ժ󣬴���ArrayList
		for(int i=0;i<str.length();i++){
			al.add(i, str.charAt(str.length()-(i+1)));
		}
		//���ַ���ArrayList��ȡ����Ȼ��ƴ�ӳ��ַ���
		for(int j=0;j<al.size();j++){
			//�����õ��˵ݹ���ã�
			//static_string�Ƕ���ľ�̬��ȫ���ַ�������
			//public static String static_string = "";
			static_string = static_string.concat(al.get(j).toString());
		}
		return static_string;
	}

}