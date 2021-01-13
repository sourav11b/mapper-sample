package com.datastax.samples.javamapper.entity;

import java.util.concurrent.CompletionStage;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.MapperBuilder;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import com.datastax.samples.javamapper.entity.async.AsyncDDAPostedResponseDAO;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseMapperBuilder;

@Mapper(builderName ="com.datastax.samples.javamapper.entity.DDAPostedResponseMapperBuilder")
public interface DDAPostedResponseMapper {
	
//	static MapperBuilder<DDAPostedResponseMapper> builder(CqlSession session) {
//	    return new DDAPostedResponseMapperBuilder(session);
//	  }
	
	@DaoFactory
	DDAPostedResponseDAO DDAPostedResponseDAO();
	@DaoFactory
	DDAPostedResponseDAO DDAPostedResponseDAO(@DaoKeyspace String keyspace);
	
	
	@DaoFactory
	CompletionStage<AsyncDDAPostedResponseDAO> asyncDDAPostedResponseDAO();

}
