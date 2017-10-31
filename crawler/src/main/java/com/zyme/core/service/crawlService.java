package com.zyme.core.service;

import org.springframework.stereotype.Service;

@Service
public class crawlService {

/*	@Autowired
	private ScrapeDataRepository scrapeDataRepository;
	
	
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
			controller.start(MyCrawler.class, numberOfCrawlers);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

}
