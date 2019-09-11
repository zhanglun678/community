package com.zl.test;



public class SplitAccessToken {

	public static void main(String[] args) {
		String str = "access_token=53a0df7a179f08ea59086b846da23b7ef6b96fd6&scope=user&token_type=bearer";
		System.out.println(str.split("&")[0].split("=")[1]);
	}
}
