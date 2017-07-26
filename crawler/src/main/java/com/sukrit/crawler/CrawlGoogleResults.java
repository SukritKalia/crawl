package com.sukrit.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlGoogleResults {
public  Map<String,String> fetchProfileURLS(Review review) throws UnsupportedEncodingException, IOException
{
	String google = CrawlerContsants.GOOGLE_URL;
	String whiteSpace = " ";
	String reviewerFirstname = review.getReviewerFirstname();
	String reviewerLastName = review.getReviewerLastname();
	String reviewerJobTitle = review.getReviewerJobTitle();
	String reviewerCompanyName= review.getReviewerCompanyName();
	ProfessionalPortal webPortalName = review.getProfessionalPortal();
	String search= reviewerFirstname+whiteSpace+reviewerLastName+whiteSpace+reviewerJobTitle+whiteSpace+reviewerCompanyName+whiteSpace+webPortalName.portalName();
	String searchWithoutJobTitle = reviewerFirstname+whiteSpace+reviewerLastName+whiteSpace+reviewerCompanyName+whiteSpace+webPortalName.portalName();
	String charset = CrawlerContsants.CHAR_SET;
	String userAgent = CrawlerContsants.USER_AGENT; // Change this to your company's name and bot homepage!

	Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
	
	 Map<String,String> userProfileURLMap = new HashMap<String,String>();
	 List<String> profileURLlist = new ArrayList<String>();
	 String url = null;
	for (Element link : links) {
	    String title = link.text();
	    url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
	    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

	    if (!url.startsWith("http")) {
	        continue; // Ads/news/etc.
	    }
	   // System.out.println("Title: " + title);
	   // System.out.println("URL: " + url);
	    if(!StringUtils.isEmpty(url))
	    {
	    	profileURLlist.add(url);
	    }
	}
	
	String actualURL = profileURLlist.get(0);
	//Document document = Jsoup.connect(actualURL).get();     
	// System.out.println(document);
	// String docu = document.toString();
	// boolean docuContains = docu.contains("at Department of Veterans Affairs");
	 //System.out.println(docuContains);
	
	////search without jobtitle---> this code is to be optimised
	
	if(!CrawlerUtils.isContentInProfileURL(actualURL, reviewerFirstname) || !CrawlerUtils.isContentInProfileURL(actualURL, reviewerLastName) || !CrawlerUtils.isContentInProfileURL(actualURL, webPortalName.portalName()))
	{
		Elements links1 = Jsoup.connect(google + URLEncoder.encode(searchWithoutJobTitle, charset)).userAgent(userAgent).get().select(".g>.r>a");
		 
		 Map<String,String> userProfileURLMap1 = new HashMap<String,String>();
		 List<String> profileURLlist1 = new ArrayList<String>();
		 String url1 = null;
		for (Element link : links1) {
		    String title1 = link.text();
		    url1 = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
		    url1 = URLDecoder.decode(url1.substring(url1.indexOf('=') + 1, url1.indexOf('&')), "UTF-8");

		    if (!url1.startsWith("http")) {
		        continue; // Ads/news/etc.
		    }
		   // System.out.println("Title: " + title);
		   // System.out.println("URL: " + url);
		    if(!StringUtils.isEmpty(url1))
		    {
		    	profileURLlist1.add(url1);
		    }
		}
		String actualURL1 = profileURLlist1.get(0);
		if(!CrawlerUtils.isContentInProfileURL(actualURL1, reviewerFirstname) || !CrawlerUtils.isContentInProfileURL(actualURL1, reviewerLastName))
	    {
	    	userProfileURLMap1.put("flag","Please verify this URL manually as well!!");
	     }
	    if(StringUtils.isEmpty(reviewerCompanyName))
	    {
	    	userProfileURLMap1.put("flag","Please verify this URL manually as well!!");
	     }
	    userProfileURLMap1.put("actualURL",actualURL1);
	return userProfileURLMap1;
	}
	
	
	
	if(!CrawlerUtils.isContentInProfileURL(actualURL, reviewerFirstname) || !CrawlerUtils.isContentInProfileURL(actualURL, reviewerLastName))
    {
    	userProfileURLMap.put("flag","Please verify this URL manually as well!!");
     }
    if(StringUtils.isEmpty(reviewerCompanyName))
    {
    	userProfileURLMap.put("flag","Please verify this URL manually as well!!");
     }
    userProfileURLMap.put("actualURL",actualURL);
return userProfileURLMap;
	
	
	 
}
}
