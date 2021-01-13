package com.datastax.samples.javamapper.entity;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.GetEntity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface DDAPostedResponseDAO {

	@Select
	PagingIterable<DDAPostedResponseEntity> asDDAPostedResponse(String accnt_nbr,String cmpny_id,String prdct_cde,Long bckt_id) ;

	@Select(customWhereClause = "solr_query = :solr_query")
	PagingIterable<DDAPostedResponseEntity> searchDDA(String solr_query) ;

	
}
