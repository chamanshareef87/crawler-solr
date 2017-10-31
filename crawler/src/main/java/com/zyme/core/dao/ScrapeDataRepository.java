package com.zyme.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zyme.core.domain.ScrapeData;

public interface ScrapeDataRepository extends CrudRepository<ScrapeData,Integer> {
	
	@Query(value="SELECT * FROM domain_pages WHERE domain = :domain",nativeQuery = true)
	List<ScrapeData> findByDomain(@Param("domain") String domain);

	@Query(value="SELECT * FROM domain_pages WHERE url = :url limit 1",nativeQuery = true)
	ScrapeData findByUrl(@Param("url") String url);

	@Query(value="SELECT * FROM domain_pages WHERE RECORD_STATUS=1 AND crawl_status = 'crawle'",nativeQuery = true)
	List<ScrapeData> getAllUrls();

}
