package com.datastax.samples.javamapper.test.backup;

import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

public class ResultSetPaging {

	public ResultSetPaging() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("datastax-java-driver.basic.request.page-size", "200");

		try (CqlSession session = CqlSession.builder().addContactPoint(new InetSocketAddress("54.193.183.91", 9042))
				.addContactPoint(new InetSocketAddress("54.153.62.226", 9042)).withLocalDatacenter("DC1")
				.withAuthCredentials("cassandra", "cassandra").build()) {

			SimpleStatement statement = SimpleStatement.newInstance(
					"select * from transaction_datastore.dda_pstd_fincl_txn_bsns_by_accntnbr where solr_query = '{\"q\" : \"txn_amt : 100\", \"paging\" : \"driver\" }'");
			ResultSet res = session.execute(statement);

			System.out.println(res.getAvailableWithoutFetching());

		}

	}

}
