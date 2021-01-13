package com.datastax.samples.javamapper.entity;

import java.lang.Long;
import java.time.Instant;
import java.time.LocalDate;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;


@Entity
@CqlName("dda_pstd_fincl_txn_bsns_by_accntnbr")
public class DDAPostedResponseEntity {
	
	@PartitionKey(1) private String accnt_nbr;
	@PartitionKey(2) private String cmpny_id;
	@PartitionKey(3) private String prdct_cde;
	@PartitionKey(4) private Long bckt_id;
	@ClusteringColumn(1) private LocalDate  pstd_dt;
	@ClusteringColumn(2) private String txn_seq;
    private Double accnt_bal;
    private String accnt_id;
    private String accnt_nbr_hash;
    private String ach_authrztn_cde;
    private Double avail_bal;
    private String bsns_cons_flg;
    private Double cash_back_amt;
    private Double chk_card_authrztn;
    private Double depst_hld;
    private String doc_id;
    private String enrchd_ctgry_guid;
    private String enrchd_ctgry_is_incm;
    private String enrchd_ctgry_nm;
    private String enrchd_ctgry_prnt_guid;
    private String  enrchd_desc;
    private String enrchd_is_bill_pay;
    private String enrchd_is_dirdep;
    private String enrchd_is_expns;
    private String enrchd_is_fee;
    private String enrchd_is_incm;
    private String enrchd_is_od_fee;
    private String enrchd_is_pyrl_advnc;
    private String  enrchd_merch_guid;
    private String enrchd_merch_loc_city;
    private String enrchd_merch_loc_ctry;
    private String enrchd_merch_loc_guid;
    private String enrchd_merch_loc_lat;
    private String enrchd_merch_loc_long;
    private String enrchd_merch_loc_merch_guid;
    private String enrchd_merch_loc_phn_nbr;
    private String enrchd_merch_loc_pstl_cde;
    private String  enrchd_merch_loc_st_addr;
    private String enrchd_merch_loc_ste;
    private String enrchd_merch_loc_store_nbr;
    private String enrchd_merch_logo_url;
    private String enrchd_merch_nm;
    private String enrchd_merch_website_url;
    private Double escrw_amt;
    private String extrnl_sub_prdct_cde;
    private Double  int_amt;
    private String intrnl_sub_prdct_cde;
    private Double invstmnt_sweep;
    private String last_4dgt_debit_card_accnt_nbr;
    private String load_ts;
    private Double loan_sweep;
    private String merch_catgry_cde;
    private Double next_bsns_day_crdts;
    private Double next_bsns_day_debits;
    private Double  orig_crncy_amt;
    private String orig_crncy_cde;
    private Double other_adjstmnt;
    private Instant  pnt_of_sale_ts;
    private Double prncpl_amt;
    private Instant  pstd_ts;
    private String rec_typ;
    private String ref_nbr;
    private String rev_cde;
    private String rib_addtnl_desc_fld;
    private Double rltd_txn_amt;
    private String rltd_txn_chk_nbr;
    private String rltd_txn_desc;
    private LocalDate rltd_txn_dt ;
    private String rtn ;
    private String seq_nbr;
    private String src_nm;
    private String strt_flg;
    private String sub_prdct_cde;
    private Double taxbl_pmt_amt;
    private Double tot_pmt_amt;
    private String trnsfr_dest_accnt_nbr;
    private String trnsfr_dest_cmpny_id;
    private String trnsfr_dest_prdct_cde;
    private Double txn_amt;
    private String txn_cde;
    private String txn_crncy_cde;
    private String txn_desc;
    private String txn_drctn;
    private String txn_hash_key;
    private String txn_id;
    private String txn_pstd_dt_mnfrm;
    private String txn_status;
    private String txn_trace_id;
    private Instant  txn_ts;
    private String txn_typ;
    private String txn_uid;
	

	public DDAPostedResponseEntity() {
	}


	public String getAccnt_nbr() {
		return accnt_nbr;
	}


	public void setAccnt_nbr(String accnt_nbr) {
		this.accnt_nbr = accnt_nbr;
	}


	public String getCmpny_id() {
		return cmpny_id;
	}


	public void setCmpny_id(String cmpny_id) {
		this.cmpny_id = cmpny_id;
	}


	public String getPrdct_cde() {
		return prdct_cde;
	}


	public void setPrdct_cde(String prdct_cde) {
		this.prdct_cde = prdct_cde;
	}


	public Long getBckt_id() {
		return bckt_id;
	}


	public void setBckt_id(Long bckt_id) {
		this.bckt_id = bckt_id;
	}


	public LocalDate getPstd_dt() {
		return pstd_dt;
	}


	public void setPstd_dt(LocalDate pstd_dt) {
		this.pstd_dt = pstd_dt;
	}


	public String getTxn_seq() {
		return txn_seq;
	}


	public void setTxn_seq(String txn_seq) {
		this.txn_seq = txn_seq;
	}


	public Double getAccnt_bal() {
		return accnt_bal;
	}


	public void setAccnt_bal(Double accnt_bal) {
		this.accnt_bal = accnt_bal;
	}


	public String getAccnt_id() {
		return accnt_id;
	}


	public void setAccnt_id(String accnt_id) {
		this.accnt_id = accnt_id;
	}


	public String getAccnt_nbr_hash() {
		return accnt_nbr_hash;
	}


	public void setAccnt_nbr_hash(String accnt_nbr_hash) {
		this.accnt_nbr_hash = accnt_nbr_hash;
	}


	public String getAch_authrztn_cde() {
		return ach_authrztn_cde;
	}


	public void setAch_authrztn_cde(String ach_authrztn_cde) {
		this.ach_authrztn_cde = ach_authrztn_cde;
	}


	public Double getAvail_bal() {
		return avail_bal;
	}


	public void setAvail_bal(Double avail_bal) {
		this.avail_bal = avail_bal;
	}


	public String getBsns_cons_flg() {
		return bsns_cons_flg;
	}


	public void setBsns_cons_flg(String bsns_cons_flg) {
		this.bsns_cons_flg = bsns_cons_flg;
	}


	public Double getCash_back_amt() {
		return cash_back_amt;
	}


	public void setCash_back_amt(Double cash_back_amt) {
		this.cash_back_amt = cash_back_amt;
	}


	public Double getChk_card_authrztn() {
		return chk_card_authrztn;
	}


	public void setChk_card_authrztn(Double chk_card_authrztn) {
		this.chk_card_authrztn = chk_card_authrztn;
	}


	public Double getDepst_hld() {
		return depst_hld;
	}


	public void setDepst_hld(Double depst_hld) {
		this.depst_hld = depst_hld;
	}


	public String getDoc_id() {
		return doc_id;
	}


	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}


	public String getEnrchd_ctgry_guid() {
		return enrchd_ctgry_guid;
	}


	public void setEnrchd_ctgry_guid(String enrchd_ctgry_guid) {
		this.enrchd_ctgry_guid = enrchd_ctgry_guid;
	}


	public String getEnrchd_ctgry_is_incm() {
		return enrchd_ctgry_is_incm;
	}


	public void setEnrchd_ctgry_is_incm(String enrchd_ctgry_is_incm) {
		this.enrchd_ctgry_is_incm = enrchd_ctgry_is_incm;
	}


	public String getEnrchd_ctgry_nm() {
		return enrchd_ctgry_nm;
	}


	public void setEnrchd_ctgry_nm(String enrchd_ctgry_nm) {
		this.enrchd_ctgry_nm = enrchd_ctgry_nm;
	}


	public String getEnrchd_ctgry_prnt_guid() {
		return enrchd_ctgry_prnt_guid;
	}


	public void setEnrchd_ctgry_prnt_guid(String enrchd_ctgry_prnt_guid) {
		this.enrchd_ctgry_prnt_guid = enrchd_ctgry_prnt_guid;
	}


	public String getEnrchd_desc() {
		return enrchd_desc;
	}


	public void setEnrchd_desc(String enrchd_desc) {
		this.enrchd_desc = enrchd_desc;
	}


	public String getEnrchd_is_bill_pay() {
		return enrchd_is_bill_pay;
	}


	public void setEnrchd_is_bill_pay(String enrchd_is_bill_pay) {
		this.enrchd_is_bill_pay = enrchd_is_bill_pay;
	}


	public String getEnrchd_is_dirdep() {
		return enrchd_is_dirdep;
	}


	public void setEnrchd_is_dirdep(String enrchd_is_dirdep) {
		this.enrchd_is_dirdep = enrchd_is_dirdep;
	}


	public String getEnrchd_is_expns() {
		return enrchd_is_expns;
	}


	public void setEnrchd_is_expns(String enrchd_is_expns) {
		this.enrchd_is_expns = enrchd_is_expns;
	}


	public String getEnrchd_is_fee() {
		return enrchd_is_fee;
	}


	public void setEnrchd_is_fee(String enrchd_is_fee) {
		this.enrchd_is_fee = enrchd_is_fee;
	}


	public String getEnrchd_is_incm() {
		return enrchd_is_incm;
	}


	public void setEnrchd_is_incm(String enrchd_is_incm) {
		this.enrchd_is_incm = enrchd_is_incm;
	}


	public String getEnrchd_is_od_fee() {
		return enrchd_is_od_fee;
	}


	public void setEnrchd_is_od_fee(String enrchd_is_od_fee) {
		this.enrchd_is_od_fee = enrchd_is_od_fee;
	}


	public String getEnrchd_is_pyrl_advnc() {
		return enrchd_is_pyrl_advnc;
	}


	public void setEnrchd_is_pyrl_advnc(String enrchd_is_pyrl_advnc) {
		this.enrchd_is_pyrl_advnc = enrchd_is_pyrl_advnc;
	}


	public String getEnrchd_merch_guid() {
		return enrchd_merch_guid;
	}


	public void setEnrchd_merch_guid(String enrchd_merch_guid) {
		this.enrchd_merch_guid = enrchd_merch_guid;
	}


	public String getEnrchd_merch_loc_city() {
		return enrchd_merch_loc_city;
	}


	public void setEnrchd_merch_loc_city(String enrchd_merch_loc_city) {
		this.enrchd_merch_loc_city = enrchd_merch_loc_city;
	}


	public String getEnrchd_merch_loc_ctry() {
		return enrchd_merch_loc_ctry;
	}


	public void setEnrchd_merch_loc_ctry(String enrchd_merch_loc_ctry) {
		this.enrchd_merch_loc_ctry = enrchd_merch_loc_ctry;
	}


	public String getEnrchd_merch_loc_guid() {
		return enrchd_merch_loc_guid;
	}


	public void setEnrchd_merch_loc_guid(String enrchd_merch_loc_guid) {
		this.enrchd_merch_loc_guid = enrchd_merch_loc_guid;
	}


	public String getEnrchd_merch_loc_lat() {
		return enrchd_merch_loc_lat;
	}


	public void setEnrchd_merch_loc_lat(String enrchd_merch_loc_lat) {
		this.enrchd_merch_loc_lat = enrchd_merch_loc_lat;
	}


	public String getEnrchd_merch_loc_long() {
		return enrchd_merch_loc_long;
	}


	public void setEnrchd_merch_loc_long(String enrchd_merch_loc_long) {
		this.enrchd_merch_loc_long = enrchd_merch_loc_long;
	}


	public String getEnrchd_merch_loc_merch_guid() {
		return enrchd_merch_loc_merch_guid;
	}


	public void setEnrchd_merch_loc_merch_guid(String enrchd_merch_loc_merch_guid) {
		this.enrchd_merch_loc_merch_guid = enrchd_merch_loc_merch_guid;
	}


	public String getEnrchd_merch_loc_phn_nbr() {
		return enrchd_merch_loc_phn_nbr;
	}


	public void setEnrchd_merch_loc_phn_nbr(String enrchd_merch_loc_phn_nbr) {
		this.enrchd_merch_loc_phn_nbr = enrchd_merch_loc_phn_nbr;
	}


	public String getEnrchd_merch_loc_pstl_cde() {
		return enrchd_merch_loc_pstl_cde;
	}


	public void setEnrchd_merch_loc_pstl_cde(String enrchd_merch_loc_pstl_cde) {
		this.enrchd_merch_loc_pstl_cde = enrchd_merch_loc_pstl_cde;
	}


	public String getEnrchd_merch_loc_st_addr() {
		return enrchd_merch_loc_st_addr;
	}


	public void setEnrchd_merch_loc_st_addr(String enrchd_merch_loc_st_addr) {
		this.enrchd_merch_loc_st_addr = enrchd_merch_loc_st_addr;
	}


	public String getEnrchd_merch_loc_ste() {
		return enrchd_merch_loc_ste;
	}


	public void setEnrchd_merch_loc_ste(String enrchd_merch_loc_ste) {
		this.enrchd_merch_loc_ste = enrchd_merch_loc_ste;
	}


	public String getEnrchd_merch_loc_store_nbr() {
		return enrchd_merch_loc_store_nbr;
	}


	public void setEnrchd_merch_loc_store_nbr(String enrchd_merch_loc_store_nbr) {
		this.enrchd_merch_loc_store_nbr = enrchd_merch_loc_store_nbr;
	}


	public String getEnrchd_merch_logo_url() {
		return enrchd_merch_logo_url;
	}


	public void setEnrchd_merch_logo_url(String enrchd_merch_logo_url) {
		this.enrchd_merch_logo_url = enrchd_merch_logo_url;
	}


	public String getEnrchd_merch_nm() {
		return enrchd_merch_nm;
	}


	public void setEnrchd_merch_nm(String enrchd_merch_nm) {
		this.enrchd_merch_nm = enrchd_merch_nm;
	}


	public String getEnrchd_merch_website_url() {
		return enrchd_merch_website_url;
	}


	public void setEnrchd_merch_website_url(String enrchd_merch_website_url) {
		this.enrchd_merch_website_url = enrchd_merch_website_url;
	}


	public Double getEscrw_amt() {
		return escrw_amt;
	}


	public void setEscrw_amt(Double escrw_amt) {
		this.escrw_amt = escrw_amt;
	}


	public String getExtrnl_sub_prdct_cde() {
		return extrnl_sub_prdct_cde;
	}


	public void setExtrnl_sub_prdct_cde(String extrnl_sub_prdct_cde) {
		this.extrnl_sub_prdct_cde = extrnl_sub_prdct_cde;
	}


	public Double getInt_amt() {
		return int_amt;
	}


	public void setInt_amt(Double int_amt) {
		this.int_amt = int_amt;
	}


	public String getIntrnl_sub_prdct_cde() {
		return intrnl_sub_prdct_cde;
	}


	public void setIntrnl_sub_prdct_cde(String intrnl_sub_prdct_cde) {
		this.intrnl_sub_prdct_cde = intrnl_sub_prdct_cde;
	}


	public Double getInvstmnt_sweep() {
		return invstmnt_sweep;
	}


	public void setInvstmnt_sweep(Double invstmnt_sweep) {
		this.invstmnt_sweep = invstmnt_sweep;
	}


	public String getLast_4dgt_debit_card_accnt_nbr() {
		return last_4dgt_debit_card_accnt_nbr;
	}


	public void setLast_4dgt_debit_card_accnt_nbr(String last_4dgt_debit_card_accnt_nbr) {
		this.last_4dgt_debit_card_accnt_nbr = last_4dgt_debit_card_accnt_nbr;
	}


	public String getLoad_ts() {
		return load_ts;
	}


	public void setLoad_ts(String load_ts) {
		this.load_ts = load_ts;
	}


	public Double getLoan_sweep() {
		return loan_sweep;
	}


	public void setLoan_sweep(Double loan_sweep) {
		this.loan_sweep = loan_sweep;
	}


	public String getMerch_catgry_cde() {
		return merch_catgry_cde;
	}


	public void setMerch_catgry_cde(String merch_catgry_cde) {
		this.merch_catgry_cde = merch_catgry_cde;
	}


	public Double getNext_bsns_day_crdts() {
		return next_bsns_day_crdts;
	}


	public void setNext_bsns_day_crdts(Double next_bsns_day_crdts) {
		this.next_bsns_day_crdts = next_bsns_day_crdts;
	}


	public Double getNext_bsns_day_debits() {
		return next_bsns_day_debits;
	}


	public void setNext_bsns_day_debits(Double next_bsns_day_debits) {
		this.next_bsns_day_debits = next_bsns_day_debits;
	}


	public Double getOrig_crncy_amt() {
		return orig_crncy_amt;
	}


	public void setOrig_crncy_amt(Double orig_crncy_amt) {
		this.orig_crncy_amt = orig_crncy_amt;
	}


	public String getOrig_crncy_cde() {
		return orig_crncy_cde;
	}


	public void setOrig_crncy_cde(String orig_crncy_cde) {
		this.orig_crncy_cde = orig_crncy_cde;
	}


	public Double getOther_adjstmnt() {
		return other_adjstmnt;
	}


	public void setOther_adjstmnt(Double other_adjstmnt) {
		this.other_adjstmnt = other_adjstmnt;
	}


	public Instant  getPnt_of_sale_ts() {
		return pnt_of_sale_ts;
	}


	public void setPnt_of_sale_ts(Instant  pnt_of_sale_ts) {
		this.pnt_of_sale_ts = pnt_of_sale_ts;
	}


	public Double getPrncpl_amt() {
		return prncpl_amt;
	}


	public void setPrncpl_amt(Double prncpl_amt) {
		this.prncpl_amt = prncpl_amt;
	}


	public Instant  getPstd_ts() {
		return pstd_ts;
	}


	public void setPstd_ts(Instant  pstd_ts) {
		this.pstd_ts = pstd_ts;
	}


	public String getRec_typ() {
		return rec_typ;
	}


	public void setRec_typ(String rec_typ) {
		this.rec_typ = rec_typ;
	}


	public String getRef_nbr() {
		return ref_nbr;
	}


	public void setRef_nbr(String ref_nbr) {
		this.ref_nbr = ref_nbr;
	}


	public String getRev_cde() {
		return rev_cde;
	}


	public void setRev_cde(String rev_cde) {
		this.rev_cde = rev_cde;
	}


	public String getRib_addtnl_desc_fld() {
		return rib_addtnl_desc_fld;
	}


	public void setRib_addtnl_desc_fld(String rib_addtnl_desc_fld) {
		this.rib_addtnl_desc_fld = rib_addtnl_desc_fld;
	}


	public Double getRltd_txn_amt() {
		return rltd_txn_amt;
	}


	public void setRltd_txn_amt(Double rltd_txn_amt) {
		this.rltd_txn_amt = rltd_txn_amt;
	}


	public String getRltd_txn_chk_nbr() {
		return rltd_txn_chk_nbr;
	}


	public void setRltd_txn_chk_nbr(String rltd_txn_chk_nbr) {
		this.rltd_txn_chk_nbr = rltd_txn_chk_nbr;
	}


	public String getRltd_txn_desc() {
		return rltd_txn_desc;
	}


	public void setRltd_txn_desc(String rltd_txn_desc) {
		this.rltd_txn_desc = rltd_txn_desc;
	}


	public LocalDate getRltd_txn_dt() {
		return rltd_txn_dt;
	}


	public void setRltd_txn_dt(LocalDate rltd_txn_dt) {
		this.rltd_txn_dt = rltd_txn_dt;
	}


	public String getRtn() {
		return rtn;
	}


	public void setRtn(String rtn) {
		this.rtn = rtn;
	}


	public String getSeq_nbr() {
		return seq_nbr;
	}


	public void setSeq_nbr(String seq_nbr) {
		this.seq_nbr = seq_nbr;
	}


	public String getSrc_nm() {
		return src_nm;
	}


	public void setSrc_nm(String src_nm) {
		this.src_nm = src_nm;
	}


	public String getStrt_flg() {
		return strt_flg;
	}


	public void setStrt_flg(String strt_flg) {
		this.strt_flg = strt_flg;
	}


	public String getSub_prdct_cde() {
		return sub_prdct_cde;
	}


	public void setSub_prdct_cde(String sub_prdct_cde) {
		this.sub_prdct_cde = sub_prdct_cde;
	}


	public Double getTaxbl_pmt_amt() {
		return taxbl_pmt_amt;
	}


	public void setTaxbl_pmt_amt(Double taxbl_pmt_amt) {
		this.taxbl_pmt_amt = taxbl_pmt_amt;
	}


	public String getTrnsfr_dest_accnt_nbr() {
		return trnsfr_dest_accnt_nbr;
	}


	public void setTrnsfr_dest_accnt_nbr(String trnsfr_dest_accnt_nbr) {
		this.trnsfr_dest_accnt_nbr = trnsfr_dest_accnt_nbr;
	}


	public String getTrnsfr_dest_cmpny_id() {
		return trnsfr_dest_cmpny_id;
	}


	public void setTrnsfr_dest_cmpny_id(String trnsfr_dest_cmpny_id) {
		this.trnsfr_dest_cmpny_id = trnsfr_dest_cmpny_id;
	}


	public String getTrnsfr_dest_prdct_cde() {
		return trnsfr_dest_prdct_cde;
	}


	public void setTrnsfr_dest_prdct_cde(String trnsfr_dest_prdct_cde) {
		this.trnsfr_dest_prdct_cde = trnsfr_dest_prdct_cde;
	}


	public String getTxn_cde() {
		return txn_cde;
	}


	public void setTxn_cde(String txn_cde) {
		this.txn_cde = txn_cde;
	}


	public String getTxn_crncy_cde() {
		return txn_crncy_cde;
	}


	public void setTxn_crncy_cde(String txn_crncy_cde) {
		this.txn_crncy_cde = txn_crncy_cde;
	}


	public String getTxn_desc() {
		return txn_desc;
	}


	public void setTxn_desc(String txn_desc) {
		this.txn_desc = txn_desc;
	}


	public String getTxn_drctn() {
		return txn_drctn;
	}


	public void setTxn_drctn(String txn_drctn) {
		this.txn_drctn = txn_drctn;
	}


	public String getTxn_hash_key() {
		return txn_hash_key;
	}


	public void setTxn_hash_key(String txn_hash_key) {
		this.txn_hash_key = txn_hash_key;
	}


	public String getTxn_id() {
		return txn_id;
	}


	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}


	public String getTxn_pstd_dt_mnfrm() {
		return txn_pstd_dt_mnfrm;
	}


	public void setTxn_pstd_dt_mnfrm(String txn_pstd_dt_mnfrm) {
		this.txn_pstd_dt_mnfrm = txn_pstd_dt_mnfrm;
	}


	public String getTxn_status() {
		return txn_status;
	}


	public void setTxn_status(String txn_status) {
		this.txn_status = txn_status;
	}


	public String getTxn_trace_id() {
		return txn_trace_id;
	}


	public void setTxn_trace_id(String txn_trace_id) {
		this.txn_trace_id = txn_trace_id;
	}


	public Instant  getTxn_ts() {
		return txn_ts;
	}


	public void setTxn_ts(Instant  txn_ts) {
		this.txn_ts = txn_ts;
	}


	public String getTxn_typ() {
		return txn_typ;
	}


	public void setTxn_typ(String txn_typ) {
		this.txn_typ = txn_typ;
	}


	public String getTxn_uid() {
		return txn_uid;
	}


	public void setTxn_uid(String txn_uid) {
		this.txn_uid = txn_uid;
	}


	@Override
	public String toString() {
		return "DDAPostedResponseMapping [accnt_nbr=" + accnt_nbr + ", cmpny_id=" + cmpny_id + ", prdct_cde="
				+ prdct_cde + ", bckt_id=" + bckt_id + ", pstd_dt=" + pstd_dt + ", txn_seq=" + txn_seq + ", accnt_bal="
				+ accnt_bal + ", accnt_id=" + accnt_id + ", accnt_nbr_hash=" + accnt_nbr_hash + ", ach_authrztn_cde="
				+ ach_authrztn_cde + ", avail_bal=" + avail_bal + ", bsns_cons_flg=" + bsns_cons_flg
				+ ", cash_back_amt=" + cash_back_amt + ", chk_card_authrztn=" + chk_card_authrztn + ", depst_hld="
				+ depst_hld + ", doc_id=" + doc_id + ", enrchd_ctgry_guid=" + enrchd_ctgry_guid
				+ ", enrchd_ctgry_is_incm=" + enrchd_ctgry_is_incm + ", enrchd_ctgry_nm=" + enrchd_ctgry_nm
				+ ", enrchd_ctgry_prnt_guid=" + enrchd_ctgry_prnt_guid + ", enrchd_desc=" + enrchd_desc
				+ ", enrchd_is_bill_pay=" + enrchd_is_bill_pay + ", enrchd_is_dirdep=" + enrchd_is_dirdep
				+ ", enrchd_is_expns=" + enrchd_is_expns + ", enrchd_is_fee=" + enrchd_is_fee + ", enrchd_is_incm="
				+ enrchd_is_incm + ", enrchd_is_od_fee=" + enrchd_is_od_fee + ", enrchd_is_pyrl_advnc="
				+ enrchd_is_pyrl_advnc + ", enrchd_merch_guid=" + enrchd_merch_guid + ", enrchd_merch_loc_city="
				+ enrchd_merch_loc_city + ", enrchd_merch_loc_ctry=" + enrchd_merch_loc_ctry
				+ ", enrchd_merch_loc_guid=" + enrchd_merch_loc_guid + ", enrchd_merch_loc_lat=" + enrchd_merch_loc_lat
				+ ", enrchd_merch_loc_long=" + enrchd_merch_loc_long + ", enrchd_merch_loc_merch_guid="
				+ enrchd_merch_loc_merch_guid + ", enrchd_merch_loc_phn_nbr=" + enrchd_merch_loc_phn_nbr
				+ ", enrchd_merch_loc_pstl_cde=" + enrchd_merch_loc_pstl_cde + ", enrchd_merch_loc_st_addr="
				+ enrchd_merch_loc_st_addr + ", enrchd_merch_loc_ste=" + enrchd_merch_loc_ste
				+ ", enrchd_merch_loc_store_nbr=" + enrchd_merch_loc_store_nbr + ", enrchd_merch_logo_url="
				+ enrchd_merch_logo_url + ", enrchd_merch_nm=" + enrchd_merch_nm + ", enrchd_merch_website_url="
				+ enrchd_merch_website_url + ", escrw_amt=" + escrw_amt + ", extrnl_sub_prdct_cde="
				+ extrnl_sub_prdct_cde + ", int_amt=" + int_amt + ", intrnl_sub_prdct_cde=" + intrnl_sub_prdct_cde
				+ ", invstmnt_sweep=" + invstmnt_sweep + ", last_4dgt_debit_card_accnt_nbr="
				+ last_4dgt_debit_card_accnt_nbr + ", load_ts=" + load_ts + ", loan_sweep=" + loan_sweep
				+ ", merch_catgry_cde=" + merch_catgry_cde + ", next_bsns_day_crdts=" + next_bsns_day_crdts
				+ ", next_bsns_day_debits=" + next_bsns_day_debits + ", orig_crncy_amt=" + orig_crncy_amt
				+ ", orig_crncy_cde=" + orig_crncy_cde + ", other_adjstmnt=" + other_adjstmnt + ", pnt_of_sale_ts="
				+ pnt_of_sale_ts + ", prncpl_amt=" + prncpl_amt + ", pstd_ts=" + pstd_ts + ", rec_typ=" + rec_typ
				+ ", ref_nbr=" + ref_nbr + ", rev_cde=" + rev_cde + ", rib_addtnl_desc_fld=" + rib_addtnl_desc_fld
				+ ", rltd_txn_amt=" + rltd_txn_amt + ", rltd_txn_chk_nbr=" + rltd_txn_chk_nbr + ", rltd_txn_desc="
				+ rltd_txn_desc + ", rltd_txn_dt=" + rltd_txn_dt + ", rtn=" + rtn + ", seq_nbr=" + seq_nbr + ", src_nm="
				+ src_nm + ", strt_flg=" + strt_flg + ", sub_prdct_cde=" + sub_prdct_cde + ", taxbl_pmt_amt="
				+ taxbl_pmt_amt + ", trnsfr_dest_accnt_nbr=" + trnsfr_dest_accnt_nbr + ", trnsfr_dest_cmpny_id="
				+ trnsfr_dest_cmpny_id + ", trnsfr_dest_prdct_cde=" + trnsfr_dest_prdct_cde + ", txn_cde=" + txn_cde
				+ ", txn_crncy_cde=" + txn_crncy_cde + ", txn_desc=" + txn_desc + ", txn_drctn=" + txn_drctn
				+ ", txn_hash_key=" + txn_hash_key + ", txn_id=" + txn_id + ", txn_pstd_dt_mnfrm=" + txn_pstd_dt_mnfrm
				+ ", txn_status=" + txn_status + ", txn_trace_id=" + txn_trace_id + ", txn_ts=" + txn_ts + ", txn_typ="
				+ txn_typ + ", txn_uid=" + txn_uid + "]";
	}
	
	
	
	

}
