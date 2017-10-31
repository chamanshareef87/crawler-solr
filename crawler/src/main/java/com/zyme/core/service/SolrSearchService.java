package com.zyme.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.zyme.core.utils.DocumentConverter;
import com.zyme.core.utils.SolrUtil;
import com.zyme.core.vo.SearchResult;

@Service
public class SolrSearchService {

	private SolrUtil solrutil = new SolrUtil();
	public List<SearchResult> getResults(String domain, String terms) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		System.out.println("get results at service");
		
		HttpSolrClient server = SolrUtil.getSolrServer();
		SolrQuery query = solrutil.getSolrQuery(domain, terms);
		SolrDocumentList docsList = solrutil.getSolrDocs(server, query);
		results = getResultFromSolr(docsList, terms);
		return results;
	}

	public List<SearchResult> getResultsByDomain(String domain, String terms) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		System.out.println("get results at service");
		
		HttpSolrClient server = SolrUtil.getSolrServer();
		SolrQuery query = solrutil.getDomainSolrQuery(domain, terms);
		System.out.println("Query:::"+query.toQueryString());
		SolrDocumentList docsList = solrutil.getSolrDocs(server, query);
		results = getResultFromSolr(docsList, terms);
		
		return results;
		
	}

	private List<SearchResult> getResultFromSolr(SolrDocumentList docsList, String terms) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		for(SolrDocument doc : docsList){
			SearchResult result = DocumentConverter.getResultFromDocument(doc, terms);
			results.add(result);
		}
		return results;
	}

	public void saveDocument(SolrInputDocument doc){
		try {
			HttpSolrClient server = SolrUtil.getSolrServer();
			System.out.println("serve::"+server);
			System.out.println("Document::"+doc);
			server.add(doc);
			server.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
