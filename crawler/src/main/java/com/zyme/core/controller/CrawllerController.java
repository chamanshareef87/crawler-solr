package com.zyme.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyme.core.crawlers.DomainCrawler;
import com.zyme.core.crawlers.GeneralCrawler;
import com.zyme.core.domain.ScrapeData;
import com.zyme.core.service.ScrapeDataService;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@CrossOrigin(origins = "*")
@RestController
public class CrawllerController {

	@Autowired(required=true)
	private ScrapeDataService scrapeDataService;

    public static String domain="";

    @RequestMapping("/crawlAll")
    public String crawling(@RequestParam(value="site", required=false, defaultValue="https://www.google.co.in/") String site) {
    	System.out.println("crawlAll");
    	try {
			List<ScrapeData> datalist = scrapeDataService.getAllScrapeDate();
			crawlData(datalist);
    	} catch (Exception e) {
			System.out.println("Exception:::::");
			e.printStackTrace();
		}
    	return "crawl successful.";
    }

    @RequestMapping("/crawldomain")
    public String crawlDomain(@RequestParam(value="domain", required=true) String domain) {
    	System.out.println("crawling domain");
    	try {
    		this.domain=domain;
			crawlByDomain(domain);
    	} catch (Exception e) {
			System.out.println("Exception:::::");
			e.printStackTrace();
		}
    	return "crawl successful.";
    }

	public void crawlData(List<com.zyme.core.domain.ScrapeData> datalist ){
		try {
			System.out.println("at crawling method....");
			String crawlStorageFolder = "../../../src/main/resources/data";
			int numberOfCrawlers = 1;
			CrawlConfig config = new CrawlConfig();
			config.setCrawlStorageFolder(crawlStorageFolder);
			config.setMaxDepthOfCrawling(0);
			config.setMaxPagesToFetch(10);
			config.setPolitenessDelay(500);
			
			PageFetcher pageFetcher = new PageFetcher(config);
			RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
			RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
			CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
			for(ScrapeData sdata : datalist){
				controller.addSeed(sdata.getUrl());
			}
			controller.start(GeneralCrawler.class, numberOfCrawlers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	   private void crawlByDomain(String domain) {
		   try {
				System.out.println("crawling domian::"+domain);
				String crawlStorageFolder = "../../src/main/resources/data";
				
				int numberOfCrawlers = 1;
				CrawlConfig config = new CrawlConfig();
				config.setCrawlStorageFolder(crawlStorageFolder);
//				config.setMaxDepthOfCrawling(1);
//				config.setMaxPagesToFetch(10);
				config.setPolitenessDelay(500);
				
				PageFetcher pageFetcher = new PageFetcher(config);
				RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
				RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
				CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
				controller.addSeed(domain);
				controller.start(DomainCrawler.class, numberOfCrawlers);
				controller.setCustomData(domain);

		   } catch (Exception e) {
				e.printStackTrace();
			}
		}

}
