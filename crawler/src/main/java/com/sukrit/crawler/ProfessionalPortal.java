package com.sukrit.crawler;

public enum ProfessionalPortal {
	LINKEDIN(CrawlerContsants.WEB_PORTAL_LINKEDIN),
    ZOOMINFO(CrawlerContsants.WEB_PORTAL_ZOOMINFO);
	
	private String portalName;

	ProfessionalPortal(String portalName) {
        this.portalName = portalName;
    }

    public String portalName() {
        return portalName;
    }
}
