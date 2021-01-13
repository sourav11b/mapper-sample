package com.datastax.samples.javamapper.test;

import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import java.util.HashMap;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.AsyncResultSet;
import com.datastax.oss.driver.api.core.cql.PagingState;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

public class SyncParallelMoreBuckets {

	List<Row> list = Collections.synchronizedList(new ArrayList<Row>());
	DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss:SSS");

	public static void main(String[] args) throws Exception {

		SyncParallelMoreBuckets example = new SyncParallelMoreBuckets();

		if (args.length != 5) {
			throw new Exception(" requires following parameters : pageSize bucket  use_limit limit total_bukcet");

		}

		System.setProperty("datastax-java-driver.basic.request.page-size", args[0]);

		// TODO Auto-generated method stub
		boolean bucket = Boolean.parseBoolean(args[1]);
		boolean use_limit = Boolean.parseBoolean(args[2]);
		int limit = Integer.parseInt(args[3]);
		int total_bukcet = Integer.parseInt(args[4]);

		List<Long> results = new ArrayList<Long>();
		for (int i = 0; i < 11; i++) {
			results.add(Long.valueOf(
					example.execute(String.valueOf(150000000000L + i), bucket, use_limit, limit, total_bukcet)));
			example.list.clear();
			Thread.sleep(2000);
		}
		//remove the first entry to exclude JVM class loading overheads from average

		results.remove(0);

		System.out.println(results);
		results.removeAll(Collections.singleton(0L));
		results.remove(0);
		System.out.println("Average : " + results.stream().mapToDouble(l -> Double.valueOf(l)).average());

	}

	public long execute(String account_number, boolean bucket, boolean use_limit, int limit, int total_bukcet) {

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
				statement = SimpleStatement.newInstance(
						"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr_no_bucket where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=?");

			}

			PreparedStatement prepared = session.prepare(statement);

			List<String> keyList = new ArrayList<String>();

			Map<String, Long> keyMap = new HashMap<String, Long>();

			long l = 20200101L;

			// System.out.println(" current time : " + this.df.format(new
			// Date(System.currentTimeMillis()))
			// + " , Thread : " + Thread.currentThread().getName());

			if (bucket) {

				while (l < 20200701L) {

//					keyMap.put("150000000000", l);
//					keyMap.put("150000000001", l);
					keyList.add("150000000000" + "--" + Long.toString(l));
					if (total_bukcet > 6)
						keyList.add("150000000001" + "--" + Long.toString(l));
					if (total_bukcet > 12)
						keyList.add("150000000002" + "--" + Long.toString(l));
					if (total_bukcet > 18)
						keyList.add("150000000003" + "--" + Long.toString(l));

					l = l + 100L;

				}

			} else {
				keyList.add(account_number+ "--" + Long.toString(0L));
			}

			long totalTime = System.currentTimeMillis();

			

				keyList.parallelStream().forEach(entry -> {

					Long b = Long.valueOf(entry.split("--")[1]);
					String a = entry.split("--")[0];

					// System.out.println(entry + "->"+"a: "+a+",b : "+b);

					ResultSet y = null;
					int page_count = 1;

					if (bucket) {
						if (use_limit) {
							y = session.execute(prepared.bind(a, "424", "DDA", b, limit));
						} else {
							y = session.execute(prepared.bind(a, "424", "DDA", b));

						}
					} else {
						y = session.execute(prepared.bind(account_number, "424", "DDA"));

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
									y = session.execute(
											prepared.bind(a, "424", "DDA", b, limit).setPagingState(pagingState));
								} else {
									y = session.execute(prepared.bind(a, "424", "DDA", b).setPagingState(pagingState));
								}
							} else {
								y = session.execute(
										prepared.bind(account_number, "424", "DDA").setPagingState(pagingState));

							}

						} else {

							flag = false;
						}
					}
					// System.out.println(
					// " total pages fetched " + page_count + " , thread :" +
					// Thread.currentThread().getName());

//				System.out.println(" current time : " + this.df.format(new Date(System.currentTimeMillis()))
//						+ " , Thread : " + Thread.currentThread().getName());

				});

			

			System.out.println(" list size : " + this.list.size() + "  , thread :" + Thread.currentThread().getName());
			totalTime = System.currentTimeMillis() - totalTime;
			// System.out.println("Total main thread Time : " + totalTime);

			return totalTime;

		} catch (Exception e) {
//			System.out.println(" Exception current time : " + example.df.format(new Date(System.currentTimeMillis()))
//			+ " , Thread : " + Thread.currentThread().getName());
			System.err.println(" Exception : " + e.getMessage());
			e.printStackTrace();
			return 0L;
		}
	}

}
