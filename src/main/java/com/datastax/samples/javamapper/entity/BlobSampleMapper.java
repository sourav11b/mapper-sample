package com.datastax.samples.javamapper.entity;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper(builderName ="com.datastax.samples.javamapper.entity.BlobSampleMapperBuilder")
public interface BlobSampleMapper {
	

	
	@DaoFactory
	BlobSampleDAO blobSampleDAO(@DaoKeyspace String keyspace);

}
