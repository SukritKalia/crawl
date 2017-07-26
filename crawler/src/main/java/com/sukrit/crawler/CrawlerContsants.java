package com.sukrit.crawler;

public final class CrawlerContsants {

	 public static final String WEB_PORTAL_LINKEDIN = "Linkedin";
	 public static final String WEB_PORTAL_ZOOMINFO = "zoominfo";
	 public static final String GOOGLE_URL= "http://www.google.com/search?q=";
	 public static final String CHAR_SET = "UTF-8";
	 public static final String USER_AGENT = "Chrome";
	 
	 private CrawlerContsants(){
		    //this prevents even the native class from 
		    //calling this ctor as well :
		    throw new AssertionError();
		  }
}
