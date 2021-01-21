package com.datastax.samples.javamapper.blob.test;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.samples.javamapper.entity.BlobSampleDAO;
import com.datastax.samples.javamapper.entity.BlobSampleEntity;
import com.datastax.samples.javamapper.entity.BlobSampleMapper;
import com.datastax.samples.javamapper.entity.BlobSampleMapperBuilder;


public class TestBlobMapping {

	public static void main(String[] args) {
		try (CqlSession session = CqlSession.builder()
				.build()) {
			
			BlobSampleMapper blobSampleMapper = new BlobSampleMapperBuilder(session).build();

			BlobSampleDAO dao = blobSampleMapper.blobSampleDAO("transaction_datastore");

			PagingIterable<BlobSampleEntity> result;
			
			result = dao.asBlobSample("1");
			
			
			System.out.println("---- "+result.one());
		
			
		}
	}

}
