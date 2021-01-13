package com.datastax.samples.javamapper.test.backup;
//package com.datastax.samples.javamapper.test;
//
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.time.Instant;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.Spliterator;
//
//import javax.xml.transform.Result;
//
//import com.datastax.oss.driver.api.core.CqlSession;
//import com.datastax.oss.driver.api.core.PagingIterable;
//import com.datastax.oss.driver.api.core.cql.ResultSet;
//import com.datastax.oss.driver.api.core.cql.Row;
//import com.datastax.oss.driver.api.core.cql.SimpleStatement;
//import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
//import com.datastax.oss.protocol.internal.util.Bytes;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseDAO;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseEntity;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseMapper;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseMapperBuilder;
//import com.sun.tools.javac.util.List;
//
//public class MappingTest {
//
//	public MappingTest() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public static void main(String[] args) {
//		/*
//		
//		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-dd");
//
//		// TODO Auto-generated method stub
//
//		System.setProperty("datastax-java-driver.basic.request.page-size", "200");
//		
//		try (CqlSession session = CqlSession.builder().addContactPoint(new InetSocketAddress("54.193.183.91", 9042))
//				.addContactPoint(new InetSocketAddress("54.153.62.226", 9042)).withLocalDatacenter("DC1")
//				.withAuthCredentials("cassandra", "cassandra")
//				.build()) {
//			System.out.println("datastax-java-driver.basic.request.page-size --- " +System.getProperty("datastax-java-driver.basic.request.page-size"));
//			
//			DDAPostedResponseMapper inventoryMapper = new DDAPostedResponseMapperBuilder(session).build();
//
//			DDAPostedResponseDAO dao = inventoryMapper.DDAPostedResponseDAO("transaction_datastore");
//			
//			//one with mapping - one without mapping ,cassandra partition - use case 
//			//map 5000 at a time 
//			//solr 300 per page
//			//
//			
//			//solr paging/sorting - 1 and 3
//			
//			//
//			
//
//			SimpleStatement statement = SimpleStatement.newInstance(
//					"select count(*) from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr where solr_query = '{\"q\" : \"txn_amt : 100\" }'");
//			ResultSet res = session.execute(statement);
//			System.out.println(res.one().getLong("count"));
////			PagingIterable<DDAPostedResponseEntity> result = dao.asDDAPostedResponse("150560779961", "424", "DDA",
////					20200101L);
//
//			// ,\"paging\":\"driver\"
//			
//			String searchString = "{ \"q\" : \" txn_amt: 100 \", \"paging\" : \"driver\"}";
//			
//			PagingIterable<DDAPostedResponseEntity> result = dao.searchDDA(searchString);
//
//			int count = 0;
//			
//			System.out.println("result.isFullyFetched() : " + result.isFullyFetched());
//			
//			System.out.println(Instant.now());
//
//			ArrayList<DDAPostedResponseEntity> resultsList = new ArrayList<DDAPostedResponseEntity>();
//			// PRIMARY KEY ((accnt_nbr, cmpny_id, prdct_cde, bckt_id), pstd_dt, txn_seq)
//
//			while (result.getAvailableWithoutFetching()>0) {
//
//				for (DDAPostedResponseEntity ddaPostedResponseEntity : result) {
////					System.out.println(ddaPostedResponseEntity.getAccnt_nbr()
////							+" : " + ddaPostedResponseEntity.getCmpny_id() + " ,"
////							+" : " + ddaPostedResponseEntity.getPrdct_cde() + " ,"
////							+" : " + ddaPostedResponseEntity.getBckt_id()+ " ,"
////							+" : " + ddaPostedResponseEntity.getPstd_dt() + " ,"
////							+" : " + ddaPostedResponseEntity.getTxn_seq() + " ,"
////							);
//					//System.out.println(ddaPostedResponseEntity);
//					//resultsList.add(ddaPostedResponseEntity);
//					count++;
//				}
//				System.out.println("count : " + count);
//				System.out.println(Instant.now());
////				if (count > 30)
////					break;
////				ByteBuffer pagingStateAsBytes = result.getExecutionInfo().getPagingState();
////				Bytes.toHexString(pagingStateAsBytes);
////
////				statement.setPagingState(pagingStateAsBytes);
////				result = dao.searchDDA(searchString);
//			}
//
//		
//
//			System.out.println("count : " + count);
//
//		} catch (Exception e) {
//			System.err.println(" Exception : " + e.getMessage());
//		}
//		*/
//
//	}
//
//}
