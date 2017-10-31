package com.zyme.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyme.core.domain.ScrapeData;
import com.zyme.core.service.ScrapeDataService;

@CrossOrigin(origins = "*")
@RestController
public class ScrapeDataController {

	@Autowired(required=true)
	private ScrapeDataService scrapeDataService;
	
	@RequestMapping("/getAll")
	public 	List<ScrapeData> getAllScrapeData() {
		return scrapeDataService.getAll();
	}

	@RequestMapping("/getById")
    public ScrapeData readById(@RequestParam(value="id", required=true) int id) {
		return scrapeDataService.getById(id);
	}
	
    @RequestMapping("/getByDomain")
    public List<ScrapeData> readByDomain(@RequestParam(value="domain", required=true) String domain) {
    	System.out.println("domain::"+domain);
		return scrapeDataService.getByDomain(domain);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ScrapeData addData(@RequestBody ScrapeData data) {
		if(null != data){
			data = scrapeDataService.save(data);
		}
//		CrawllerController crawllerController = new CrawllerController();
//		List<ScrapeData> list = new ArrayList<ScrapeData>();
//		list.add(data);
//		crawllerController.crawlData(list);
		return data;
    }

    @RequestMapping(value="/addList", method = RequestMethod.POST)
    public List<ScrapeData> addDataList(@RequestBody List<ScrapeData> dataList) {
    	System.out.println("at add List::");
    	for(ScrapeData data : dataList){
    		System.out.println(data.toString());
    	}
		if(null != dataList && !dataList.isEmpty()){
			dataList = scrapeDataService.saveList(dataList);
		}
//		CrawllerController crawllerController = new CrawllerController();
//		crawllerController.crawlData(dataList);
		return dataList;
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ScrapeData updateData(@RequestBody ScrapeData data) {
		if(null != data){
			data = scrapeDataService.save(data);
		}
		return data;
    }

}
