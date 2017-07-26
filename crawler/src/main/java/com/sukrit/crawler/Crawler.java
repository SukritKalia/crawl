package com.sukrit.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
public static void main(String[] args) throws UnsupportedEncodingException, IOException {
	CrawlGoogleResults craw = new CrawlGoogleResults();
	Review review = new Review();
	review.setReviewerFirstname("Ian");
	review.setReviewerLastname("Parnell");
	review.setReviewerJobTitle("Solution Architect");
	//review.setReviewerEmail("ctizzano@bn.com");
	review.setReviewerCompanyName("Queensland Urban Utilities");
	review.setProfessionalPortal(ProfessionalPortal.LINKEDIN.LINKEDIN);
	Map<String , String> profileURLmap=craw.fetchProfileURLS(review);
	for (Map.Entry<String, String> entry : profileURLmap.entrySet()) {
		System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	}

	
}
}
