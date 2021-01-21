package com.datastax.samples.javamapper.entity;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface BlobSampleDAO {

	@Select
	PagingIterable<BlobSampleEntity> asBlobSample(String key);
	
}
