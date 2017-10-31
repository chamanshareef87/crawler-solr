package com.zyme.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyme.core.service.SolrSearchService;
import com.zyme.core.vo.SearchResult;

@CrossOrigin(origins = "*")
@RestController
public class SolrController {
	
	@Autowired
	private SolrSearchService solrSearchService;
	
	@RequestMapping("/getResults")
	public List<SearchResult> getSearchResult(
			@RequestParam(value = "terms") String terms
			) {	
		String domain = "";
		System.out.println("get results at controller"+domain+terms);
//		SolrSearchService solrSearchService = new SolrSearchService();
		List<SearchResult> results = solrSearchService.getResults(domain, terms );
		
		return results;
	}
	
	@RequestMapping("/getResultsByDomain")
	public List<SearchResult> getResultsByDomain(
			@RequestParam(value = "domain") String domain,
			@RequestParam(value = "terms") String terms
			) {	
		System.out.println("get results at controller"+domain+terms);
//		SolrSearchService solrSearchService = new SolrSearchService();
		List<SearchResult> results = solrSearchService.getResultsByDomain(domain, terms );
		
		return results;
	}
	
}
