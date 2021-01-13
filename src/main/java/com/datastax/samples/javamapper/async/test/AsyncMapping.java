package com.datastax.samples.javamapper.async.test;
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
//import java.util.concurrent.CompletionStage;
//import java.util.function.Consumer;
//
//import javax.xml.transform.Result;
//
//import com.datastax.oss.driver.api.core.CqlSession;
//import com.datastax.oss.driver.api.core.MappedAsyncPagingIterable;
//import com.datastax.oss.driver.api.core.PagingIterable;
//import com.datastax.oss.driver.api.core.cql.PreparedStatement;
//import com.datastax.oss.driver.api.core.cql.ResultSet;
//import com.datastax.oss.driver.api.core.cql.Row;
//import com.datastax.oss.driver.api.core.cql.SimpleStatement;
//import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
//import com.datastax.oss.protocol.internal.util.Bytes;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseDAO;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseEntity;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseMapper;
//import com.datastax.samples.javamapper.entity.DDAPostedResponseMapperBuilder;
//import com.datastax.samples.javamapper.entity.async.AsyncDDAPostedResponseDAO;
//import com.sun.tools.javac.util.List;
//
//public class AsyncMapping {
//
//	public AsyncMapping() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public static void main(String[] args) throws Exception {
//
//		if (args.length != 4) {
//			throw new Exception(" requires following parameters : pageSize useSplit  useSearch useMapper");
//		}
//
//		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-dd");
//
//		// TODO Auto-generated method stub
//		boolean useSplit = Boolean.getBoolean(args[1]);
//		boolean useSearch = Boolean.getBoolean(args[2]);
//		boolean useMapper = Boolean.getBoolean(args[3]);
//
//		System.setProperty("datastax-java-driver.basic.request.page-size", args[0]);
//
//		try {
//			
//			CompletionStage<CqlSession> sessionFuture = CqlSession.builder().addContactPoint(new InetSocketAddress("54.193.183.91", 9042))
//					.addContactPoint(new InetSocketAddress("54.153.62.226", 9042)).withLocalDatacenter("DC1")
//					.withAuthCredentials("cassandra", "cassandra").withKeyspace("transaction_datastore").buildAsync();
//			System.out.println("datastax-java-driver.basic.request.page-size --- "
//					+ System.getProperty("datastax-java-driver.basic.request.page-size"));
//			
//			
//			
//			ArrayList<DDAPostedResponseEntity> ddas = new ArrayList<DDAPostedResponseEntity>();
//			
//			CompletionStage<DDAPostedResponseMapper> mapperFuture =
//				    sessionFuture.thenApply(s -> new DDAPostedResponseMapperBuilder(s).build());
//			
//			CompletionStage<AsyncDDAPostedResponseDAO> daoFuture =
//				    mapperFuture.thenCompose(DDAPostedResponseMapper::asyncDDAPostedResponseDAO);
//
//
//
//			
//
//		} catch (Exception e) {
//			System.err.println(" Exception : " + e.getMessage());
//		}
//
//	}
//
//}
