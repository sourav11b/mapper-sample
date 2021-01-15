## Prerequisites
- create the tables dda_pstd_fincl_txn_bsns_by_accntnbr_no_bucket and dda_pstd_fincl_txn_bsns_by_accntnbr
- load at least on partition in each with 10,000 rows (accnt_nbr = '150000000000' , cmpny_id = '424' and prdct_cde='DDA'  with/without monthly bucket). Use nosql bench artifacts usbank_cql_no_bucket.yaml and usbank_cql.yaml if needed


## Build 

- mvn clean install 

## Run

- mapped with page size x : java -jar target\mapper-sample-0.0.1-SNAPSHOT.jar x false false true ''
- unmapped with page size x : java -jar target\mapper-sample-0.0.1-SNAPSHOT.jar x false false false ''
- 10K rows from single partition : java -cp target\mapper-sample-0.0.1-SNAPSHOT.jar com.datastax.samples.javamapper.test.SyncParallel false
- 10K rows from 6 monthly partitons : java -cp target\mapper-sample-0.0.1-SNAPSHOT.jar com.datastax.samples.javamapper.test.SyncParallel true

### End
