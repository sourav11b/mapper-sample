package com.datastax.samples.javamapper.test.backup;

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
import java.util.stream.Collectors;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.AsyncResultSet;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

public class AsyncParallel2 {
	
	List<Row> list = Collections.synchronizedList(new ArrayList<Row>());
	DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss:SSS");


	public static void main(String[] args) throws Exception {
		
		AsyncParallel2 example = new AsyncParallel2();

		System.setProperty("datastax-java-driver.basic.request.page-size", "500");

		try (CqlSession session = CqlSession.builder().addContactPoint(new InetSocketAddress("54.193.183.91", 9042))
				.addContactPoint(new InetSocketAddress("54.153.62.226", 9042)).withLocalDatacenter("DC1")
				.withAuthCredentials("cassandra", "cassandra").build()) {


			SimpleStatement statement = SimpleStatement.newInstance(
					"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr where accnt_nbr=? AND  cmpny_id=? AND prdct_cde=? AND bckt_id=?");

			PreparedStatement prepared = session.prepare(statement);

			List<Long> langList = new ArrayList<Long>();

			long l = 20200101L;

			System.out.println(" current time : " + example.df.format(new Date(System.currentTimeMillis())) + " , Thread : "
					+ Thread.currentThread().getName());

			long totalTime = System.currentTimeMillis();


			while (l < 20200701L) {

				langList.add(l);
				l = l + 100L;

			}


			List<CompletableFuture<?>> pending = new ArrayList<>();

			List<CompletableFuture<AsyncResultSet>> completableFutures = langList.stream()
					.map(month -> session.executeAsync(prepared.bind("150000000000", "424", "DDA", month))
							.toCompletableFuture().whenComplete((resultSet, error) -> {
								if (error != null) {
									error.printStackTrace();
								}

							    try {
									CompletableFuture.allOf(pending.toArray(new CompletableFuture[0])).get();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ExecutionException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								example.printRows(resultSet);

								System.out.println("done fetching , current time : "
										+ example.df.format(new Date(System.currentTimeMillis())) + " , Thread : "
										+ Thread.currentThread().getName());
							}))
					.collect(Collectors.toList());

			CompletableFuture<Void> allFutures = CompletableFuture
					.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

			CompletableFuture<List<AsyncResultSet>> allCompletableFuture = allFutures.thenApply(future -> {
				return completableFutures.stream().map(completableFuture -> completableFuture.join())
						.collect(Collectors.toList());
			});

			CompletableFuture completableFuture = allCompletableFuture.thenApply(greets -> {
				return greets.stream().collect(Collectors.toList());
			});
			completableFuture.get();
			/*
			 * while( l < 20200701L) {
			 * session.executeAsync(prepared.bind("150000000000","424","DDA",20200101L)).
			 * whenComplete( (resultSet, error) -> { if (error != null) {
			 * error.printStackTrace(); } System.out.println("done fetching : " );
			 * System.out.println(" current time : "+df.format(new
			 * Date(System.currentTimeMillis())) +
			 * " , Thread : "+Thread.currentThread().getName());
			 * 
			 * 
			 * System.out.println(resultSet); });
			 * 
			 * 
			 * l=l+100L; }
			 */

			System.out.println(" list size : " + example.list.size());
			System.out.println(" current time : " + example.df.format(new Date(System.currentTimeMillis())) + " , Thread : "
					+ Thread.currentThread().getName());

			totalTime = System.currentTimeMillis() - totalTime;

			System.out.println("Total main thread Time : " + totalTime);

		} catch (Exception e) {
			System.err.println(" Exception : " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	void processRows(AsyncResultSet rs, Throwable error) {
		
		System.out.println("processRows current time : " + df.format(new Date(System.currentTimeMillis())) + " , Thread : "
				+ Thread.currentThread().getName());
		  if (error != null) {
		    // The query failed, process the error
		  } else {
		    CompletionStage<AsyncResultSet> nextPage = null;
		    if (rs.hasMorePages()) {
		      // fetch next page before consuming current page
		      nextPage = rs.fetchNextPage();
		    }
		    for (Row row : rs.currentPage()) {
		    	list.add(row);
		    }
		    if (nextPage != null) {
		      // only do this after consuming rows in current page, 
		      // to ensure they are processed in order
		      nextPage.whenComplete(this::processRows);
		    }
		  }
		}
	
	
	private CompletionStage<Void> printRows(AsyncResultSet resultSet) {
		
		System.out.println("processRows current time : " + df.format(new Date(System.currentTimeMillis())) + " , Thread : "
				+ Thread.currentThread().getName());
		  for (Row row : resultSet.currentPage()) {
		    	list.add(row);
		  }
		  
		  List<CompletableFuture<AsyncResultSet>> pending = new ArrayList<>();

		  if (resultSet.hasMorePages()) {
		    return resultSet.fetchNextPage().thenCompose(this::printRows);
		  } else {
		    return CompletableFuture.completedFuture(null);
		  }
		}
	
	private void test(AsyncResultSet resultSet) {
		
		System.out.println("processRows current time : " + df.format(new Date(System.currentTimeMillis())) + " , Thread : "
				+ Thread.currentThread().getName());
		  for (Row row : resultSet.currentPage()) {
		    	list.add(row);
		  }
		  
		  List<CompletableFuture<AsyncResultSet>> pending = new ArrayList<>();

		  if (resultSet.hasMorePages()) {
			  pending.add(resultSet.fetchNextPage().toCompletableFuture());
		  } 
		  
		 //.allOf(pending.toArray(new CompletableFuture[0])).get();

		}
	
	


}
