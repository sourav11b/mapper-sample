package com.datastax.samples.javamapper.test;

import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.AsyncResultSet;
import com.datastax.oss.driver.api.core.cql.PagingState;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

public class SyncParallel {

	List<Row> list = Collections.synchronizedList(new ArrayList<Row>());
	DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss:SSS");

	public static void main(String[] args) throws Exception {
		
		
		
		SyncParallel example = new SyncParallel();
		
		List<Long> results = new ArrayList<Long>();
		for (int i = 0; i < 11 ; i++) {
			results.add(Long.valueOf(example.execute(false,true,false,0,"1000")));
			example.list.clear();
			Thread.sleep(2000);
		}
		
		//remove the first entry to exclude JVM class loading overheads from average

		results.remove(0);

		System.out.println(results);
		results.removeAll(Collections.singleton(0L));
		System.out.println("Average : " +results.stream().mapToDouble( l -> Double.valueOf(l)).average());
		
		
	};
	
	public long execute( boolean bucket,boolean parallel,boolean use_limit,int limit,String page_size){




		System.setProperty("datastax-java-driver.basic.request.page-size", page_size);

	//	boolean bucket = true;
		String bucket_category = "month";
	//	boolean parallel = true;
	//	boolean use_limit = false;

	//	int limit = 1000;

		try (CqlSession session = CqlSession.builder().addContactPoint(new InetSocketAddress("54.193.183.91", 9042))
				.addContactPoint(new InetSocketAddress("54.153.62.226", 9042)).withLocalDatacenter("DC1")
				.withAuthCredentials("cassandra", "cassandra").build()) {
			SimpleStatement statement;

			if (bucket) {

				if (use_limit) {
					statement = SimpleStatement.newInstance(
							"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=? AND bckt_id=? limit ?");
				} else {
					statement = SimpleStatement.newInstance(
							"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=? AND bckt_id=? ");
				}
			} else {
				if (use_limit) {
					statement = SimpleStatement.newInstance(
							"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr_no_bucket where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=? limit ?");
				} else {
					statement = SimpleStatement.newInstance(
							"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr_no_bucket where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=? ");
				}

			}

			PreparedStatement prepared = session.prepare(statement);

			List<Long> langList = new ArrayList<Long>();

			long l = 0L;
			if (bucket_category.equals("week")) {
				l = 2020010100L;

			} else {
				l = 20200101L;

			}

			//System.out.println(" current time : " + this.df.format(new Date(System.currentTimeMillis()))
			//		+ " , Thread : " + Thread.currentThread().getName());

			long totalTime = System.currentTimeMillis();

			if (bucket) {
				if (bucket_category.equals("week")) {

					while (l < 2020070100L) {

						l = l + 10000L;

						// add weeks intelligently

					}

				} else {
					while (l < 20200701L) {

						langList.add(l);
						l = l + 100L;

					}
				}
			} else {
				langList.add(0L);

			}

			if (parallel) {

				langList.parallelStream().forEach(month -> {

					ResultSet y = null;
					int page_count = 1;

					if (bucket) {
						if (use_limit) {
							y = session.execute(prepared.bind("150000000000", "424", "DDA", month, limit));
						} else {
							y = session.execute(prepared.bind("150000000000", "424", "DDA", month));

						}
					} else {
						if (use_limit) {
							y = session.execute(prepared.bind("150000000000", "424", "DDA", 6 * limit));
						} else {
							y = session.execute(prepared.bind("150000000000", "424", "DDA"));

						}
					}

					boolean flag = true;
					while (flag) {
						while (y.getAvailableWithoutFetching() > 0) {
							this.list.add(y.iterator().next());

						}
						if (!y.isFullyFetched()) {

							page_count++;
							PagingState pagingState = y.getExecutionInfo().getSafePagingState();
							if (bucket) {
								if (use_limit) {
									y = session.execute(prepared.bind("150000000000", "424", "DDA", month, limit)
											.setPagingState(pagingState));
								} else {
									y = session.execute(prepared.bind("150000000000", "424", "DDA", month)
											.setPagingState(pagingState));
								}
							} else {
								if (use_limit) {
									y = session.execute(prepared.bind("150000000000", "424", "DDA", 6 * limit)
											.setPagingState(pagingState));
								} else {
									y = session.execute(
											prepared.bind("150000000000", "424", "DDA").setPagingState(pagingState));
								}

							}
						} else {

							flag = false;
						}
					}
					//System.out.println(
					//		" total pages fetched " + page_count + "  , thread :" + Thread.currentThread().getName());

				});
			} else {
				langList.stream().forEach(month -> {

					ResultSet y = null;
					int page_count = 1;

					if (bucket) {
						y = session.execute(prepared.bind("150000000000", "424", "DDA", month));
					} else {
						y = session.execute(prepared.bind("150000000000", "424", "DDA"));

					}

					boolean flag = true;
					while (flag) {
						while (y.getAvailableWithoutFetching() > 0) {
							this.list.add(y.iterator().next());

						}
						if (!y.isFullyFetched()) {

							page_count++;
							PagingState pagingState = y.getExecutionInfo().getSafePagingState();
							if (bucket) {
								y = session.execute(
										prepared.bind("150000000000", "424", "DDA", month).setPagingState(pagingState));
							} else {
								y = session.execute(
										prepared.bind("150000000000", "424", "DDA").setPagingState(pagingState));

							}
						} else {

							flag = false;
						}
					}
					//System.out.println(
					//		" total pages fetched " + page_count + "  , thread :" + Thread.currentThread().getName());

				});
			}

			System.out
					.println(" list size : " + this.list.size() + "  , thread :" + Thread.currentThread().getName());
			//System.out.println(" current time : " + this.df.format(new Date(System.currentTimeMillis()))
				//	+ " , Thread : " + Thread.currentThread().getName());

			totalTime = System.currentTimeMillis() - totalTime;

			//System.out.println("Total main thread Time : " + totalTime);
			return totalTime;

		} catch (

		Exception e) {
//			System.out.println(" Exception current time : " + example.df.format(new Date(System.currentTimeMillis()))
//			+ " , Thread : " + Thread.currentThread().getName());
			System.err.println(" Exception : " + e.getMessage());
//			e.printStackTrace();
			return 0;
		}
		
		

	
	}

}
