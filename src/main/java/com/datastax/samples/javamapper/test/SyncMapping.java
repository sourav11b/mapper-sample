package com.datastax.samples.javamapper.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.xml.transform.Result;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.protocol.internal.util.Bytes;
import com.datastax.samples.javamapper.entity.DDAPostedResponseDAO;
import com.datastax.samples.javamapper.entity.DDAPostedResponseEntity;
import com.datastax.samples.javamapper.entity.DDAPostedResponseMapper;
import com.datastax.samples.javamapper.entity.DDAPostedResponseMapperBuilder;

public class SyncMapping {

	public SyncMapping() {
		// TODO Auto-generated constructor stub
	}

	List<Row> list =  Collections.synchronizedList(new ArrayList<Row>());
	DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss:SSS");

	public static void main(String[] args) throws Exception {

		SyncMapping example = new SyncMapping();

		if (args.length !=5 ) {
			throw new Exception(" requires following parameters : pageSize useSplit  useSearch useMapper search_string");
			
			
		}

		System.setProperty("datastax-java-driver.basic.request.page-size", args[0]);

		// TODO Auto-generated method stub
		boolean useSplit = Boolean.parseBoolean(args[1]);
		boolean useSearch = Boolean.parseBoolean(args[2]);
		boolean useMapper = Boolean.parseBoolean(args[3]);
//		long totalTime = System.currentTimeMillis();
//		try (CqlSession session = CqlSession.builder().addContactPoint(new InetSocketAddress("54.193.183.91", 9042))
//				.addContactPoint(new InetSocketAddress(":wq"
//						+ "", 9042)).withLocalDatacenter("DC1")
//				.withAuthCredentials("cassandra", "cassandra").build()) {
//			
//			SimpleStatement statement = SimpleStatement.newInstance(
//					"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=? AND bckt_id=?");
//
//			PreparedStatement prepared = session.prepare(statement);
//
//			
//
//			// 'account_number' AND cmpny_id='424' AND prdct_cde='DDA' AND bckt_id=20200101
//			ResultSet res = session.execute(prepared.bind("150000000000", "424", "DDA", 20200101L));
//
//		
//			System.out.println("res.getAvailableWithoutFetching() : " + res.getAvailableWithoutFetching());
//
//		
//		}
//		totalTime = System.currentTimeMillis() - totalTime;
//
//		System.out.println("Total DB Time during intial run: " + totalTime);

		
		System.out.println("useSplit : "+useSplit+", useSearch : "+useSearch+", useMapper : "+useMapper+", enrchd_desc : "+args[4]);

		ArrayList<Long> results = new ArrayList<Long>();
		for (int i = 0; i < 11; i++) {
			results.add(Long.valueOf(example.execute(String.valueOf(150000000000L+i),useSplit, useSearch, useMapper,args[4])));
			example.list.clear();
			Thread.sleep(2000);
		}
		
		//remove the first entry to exclude JVM class loading overheads from average
		results.remove(0);
		System.out.println(results);
		results.removeAll(Collections.singleton(0L));
		System.out.println("Average : " + results.stream().mapToDouble(l -> Double.valueOf(l)).average());

	}

	public long execute(String account_number,boolean useSplit, boolean useSearch, boolean useMapper, String search_string ) {
		
		System.out.println("account_number: "+account_number+", useSplit : "+useSplit+", useSearch : "+useSearch+", useMapper : "+useMapper+", search_string : "+search_string);
		
		long totalTime = 0L;


		try (CqlSession session = CqlSession.builder()
				//.addContactPoint(new InetSocketAddress("54.193.183.91", 9042))
				//.addContactPoint(new InetSocketAddress(":wq"
				//		+ "", 9042)).withLocalDatacenter("DC1")
				//.withAuthCredentials("cassandra", "cassandra")
				.build()) {
			System.out.println("datastax-java-driver.basic.request.page-size --- "
					+ System.getProperty("datastax-java-driver.basic.request.page-size"));

			List<DDAPostedResponseEntity> ddas = new ArrayList<DDAPostedResponseEntity>();

			if (useMapper) {

				DDAPostedResponseMapper inventoryMapper = new DDAPostedResponseMapperBuilder(session).build();

				DDAPostedResponseDAO dao = inventoryMapper.DDAPostedResponseDAO("transaction_datastore");

				PagingIterable<DDAPostedResponseEntity> result;

				totalTime = System.currentTimeMillis();

				if (useSearch) {

					//String searchString = String.format("{ \"q\" : \" enrchd_desc : %s \", \"paging\" : \"driver\"}",enrchd_desc);

					System.out.println("searchString :"+search_string);
					result = dao.searchDDA(search_string);
				} else {

					result = dao.asDDAPostedResponse(account_number, "424", "DDA", 20200101L);
				}

				System.out.println("result.isFullyFetched() : " + result.isFullyFetched());
				System.out.println("result.getAvailableWithoutFetching() : " + result.getAvailableWithoutFetching());

				System.out.println("start : " + Instant.now());
				if (useSplit) {

					Consumer<DDAPostedResponseEntity> consumer = new Consumer<DDAPostedResponseEntity>() {

						@Override
						public void accept(DDAPostedResponseEntity t) {

							ddas.add(t);
						}
					};

					Spliterator<DDAPostedResponseEntity> split = result.spliterator();
					split.trySplit();
					split.tryAdvance(consumer);

					;

				} else {

					while (result.getAvailableWithoutFetching() > 0) {

						ddas.add(result.iterator().next());

					}

				}

				totalTime = System.currentTimeMillis() - totalTime;

				System.out.println("Total DB Time : " + totalTime);

				System.out.println("ddas.size() : " + ddas.size());

				System.out.println("end : " + Instant.now());
			} else {

				SimpleStatement statement = SimpleStatement.newInstance(
						"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=? AND bckt_id=?");

				PreparedStatement prepared = session.prepare(statement);

				totalTime = System.currentTimeMillis();

				// 'account_number' AND cmpny_id='424' AND prdct_cde='DDA' AND bckt_id=20200101
				ResultSet res = session.execute(prepared.bind(account_number, "424", "DDA", 20200101L));

				totalTime = System.currentTimeMillis() - totalTime;

				System.out.println("Total DB Time : " + totalTime);

				System.out.println("res.getAvailableWithoutFetching() : " + res.getAvailableWithoutFetching());

//				int count =0;
//				
//				while (res.getAvailableWithoutFetching() > 0) {
//
//					count ++;
//
//				}
//				

			}

		} catch (Exception e) {
			System.err.println(" Exception : " + e.getMessage());
		}
		return totalTime;

	}

}
