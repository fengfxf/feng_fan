package com.eureka.test.ctrl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		String r1 = "(?<name>^.+)-(?<version>v.+$)";
		  String s1 = "shop-va";
		  Pattern p = Pattern.compile(r1);
		  Matcher m = p.matcher(s1);
		  System.out.println(m.find() +":"+m.group("name")+"/"+m.group("version"));
	}

}
