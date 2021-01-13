package com.datastax.samples.javamapper.async.test;

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
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

public class AsyncParallel {

	List<Row> list = Collections.synchronizedList(new ArrayList<Row>());
	DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss:SSS");

	public static void main(String[] args) throws Exception {

		AsyncParallel example = new AsyncParallel();

		System.setProperty("datastax-java-driver.basic.request.page-size", "500");

		try (CqlSession session = CqlSession.builder().addContactPoint(new InetSocketAddress("54.193.183.91", 9042))
				.addContactPoint(new InetSocketAddress("54.153.62.226", 9042)).withLocalDatacenter("DC1")
				.withAuthCredentials("cassandra", "cassandra").build()) {

			SimpleStatement statement = SimpleStatement.newInstance(
					"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=? AND bckt_id=?");

			PreparedStatement prepared = session.prepare(statement);

			List<Long> langList = new ArrayList<Long>();

			long l = 20200101L;

			System.out.println(" current time : " + example.df.format(new Date(System.currentTimeMillis()))
					+ " , Thread : " + Thread.currentThread().getName());

			long totalTime = System.currentTimeMillis();

			while (l < 20200701L) {

				langList.add(l);
				l = l + 100L;

			}

			langList.parallelStream().forEach(month -> {
				CompletionStage<AsyncResultSet> y = session
						.executeAsync(prepared.bind("150000000000", "424", "DDA", month));
				CompletionStage<List<Row>> z = y.thenCompose(rs -> example.processAsyncResultSet(rs, example.list));
				try {
					z.toCompletableFuture().get();
				} catch (InterruptedException | ExecutionException e) {
					System.out.println(
							" Exception current time : " + example.df.format(new Date(System.currentTimeMillis()))
									+ " , Thread : " + Thread.currentThread().getName());
					System.err.println(" Exception : " + e.getMessage());
					e.printStackTrace();
				}
			}

			);

			System.out.println(" list size : " + example.list.size());
			System.out.println(" current time : " + example.df.format(new Date(System.currentTimeMillis()))
					+ " , Thread : " + Thread.currentThread().getName());

			totalTime = System.currentTimeMillis() - totalTime;

			System.out.println("Total main thread Time : " + totalTime);

		} catch (Exception e) {
			System.out.println(" Exception current time : " + example.df.format(new Date(System.currentTimeMillis()))
					+ " , Thread : " + Thread.currentThread().getName());
			System.err.println(" Exception : " + e.getMessage());
			e.printStackTrace();
		}

	}

	private CompletionStage<List<Row>> processAsyncResultSet(AsyncResultSet resultSet, List<Row> list) {

		//System.out.println(resultSet.remaining());

		for (Row row : resultSet.currentPage()) {
			list.add(row);
		}
		if (resultSet.hasMorePages()) {
			//System.out.println(resultSet.remaining());

			return resultSet.fetchNextPage().thenCompose(rs -> processAsyncResultSet(rs, list));
		} else {

			System.out.println("done fetching , current time : " + this.df.format(new Date(System.currentTimeMillis()))
					+ " , Thread : " + Thread.currentThread().getName());
			return CompletableFuture.completedFuture(list);
		}

	}

}
