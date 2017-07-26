package com.sukrit.crawler;

import org.apache.commons.lang3.StringUtils;

public final class CrawlerUtils {

	public static boolean isContentInProfileURL(String URL , String content)
	{
		boolean containerContainsContent = StringUtils.containsIgnoreCase(URL, content);
		return containerContainsContent;
	}
	
	private CrawlerUtils()
	{
		
	}
}
