package com.zyme.core.crawlers;

import java.util.Set;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import com.zyme.core.ApplicationConstants;
import com.zyme.core.ApplicationContextProvider;
import com.zyme.core.controller.CrawllerController;
import com.zyme.core.domain.ScrapeData;
import com.zyme.core.service.ScrapeDataService;
import com.zyme.core.service.SolrSearchService;
import com.zyme.core.utils.DocumentConverter;
import com.zyme.core.utils.SolrUtil;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

@Component
public class DomainCrawler extends WebCrawler{

	private ScrapeDataService scrapeDataService;

	@Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
        return href.contains(CrawllerController.domain);
    }
	
	@Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL===================" + url);
        System.out.println("================================");
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            savedata(text, html,url);
            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
        }
   }

	private void savedata(String text, String html, String url) {
		try {
			
			System.out.println("url at save to db:==========="+url);
			ScrapeData scrapeData = new ScrapeData();
			System.out.println("domain::"+(String)this.getMyController().getCustomData());
			System.out.println("domain::"+CrawllerController.domain);
			
			scrapeData.setDomain(CrawllerController.domain);
			scrapeData.setUrl(url);
			scrapeData.setContent(text);
			scrapeData.setCrawl_status(ApplicationConstants.CRAWLED);
			scrapeDataService = (ScrapeDataService) ApplicationContextProvider.getApplicationContext()
					.getBean("scrapeDataService");
			scrapeDataService.save(scrapeData);
			if (null!=scrapeData) {
				savetosolr(scrapeData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void savetosolr(ScrapeData scrapeData) {
		SolrSearchService solrService = new SolrSearchService();
		try {
			SolrDocument document = DocumentConverter.getSolrDocument(scrapeData);
			SolrInputDocument inputDocument = SolrUtil.toSolrInputDocument(document);
			solrService.saveDocument(inputDocument);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
