package com.zyme.core.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyme.core.dao.ScrapeDataRepository;
import com.zyme.core.domain.ScrapeData;

@Service
public class ScrapeDataService {

	@Autowired
	private ScrapeDataRepository scrapeDataRepository;
	
	
	public List<ScrapeData> getAllScrapeDate(){
//		List<ScrapeData> scrapeDataList = new ArrayList<>();
//		scrapeDataRepository.findAll().forEach(scrapeDataList::add);
//		return scrapeDataList;
		return scrapeDataRepository.getAllUrls();
	}
	public List<ScrapeData> getAll(){
		List<ScrapeData> scrapeDataList = new ArrayList<>();
		scrapeDataRepository.findAll().forEach(scrapeDataList::add);
		return scrapeDataList;
	}

	public ScrapeData getById(int id) {
		return scrapeDataRepository.findOne(id);
	}

	public List<ScrapeData> getByDomain(String domain) {
//		List<ScrapeData> scrapeDataList = new ArrayList<>();
		return scrapeDataRepository.findByDomain(domain);
//		return scrapeDataList;
	}


	public List<ScrapeData> saveList(List<ScrapeData> sdList) {
		for(ScrapeData sd : sdList){
			save(sd);
		}
		return sdList;
	}

	public ScrapeData save(ScrapeData sd) {
		return scrapeDataRepository.save(sd);
	}

/*	public List<ScrapeData> getUncrawled() {

		Session session = this.sessionFactory.openSession();
		List<ScrapeData> scrapeList = session.createQuery("from ScrapeData where content is null or content="+"''").list();
		session.close();
		for(ScrapeData s : scrapeList){
			s.setContent("");
		}
		return scrapeList;
	} */

	public ScrapeData getByurl(String url) {
		return scrapeDataRepository.findByUrl(url);
	}
	
}
