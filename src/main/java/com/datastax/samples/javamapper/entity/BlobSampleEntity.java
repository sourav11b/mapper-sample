package com.datastax.samples.javamapper.entity;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.datastax.oss.driver.api.mapper.annotations.Computed;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.Transient;
import com.google.gson.Gson;


@Entity
@CqlName("blob_sample")
public class BlobSampleEntity {
	
	private Gson gson = new Gson();
	
	@PartitionKey(1) private String key;
	
	private ByteBuffer data;
	
	@Computed("blobastext(data)")
	private String data_1;
	
	@Transient private String computed_data;
	
	@Transient private Person computed_person;


	public Person getComputed_person() {	
		
		
		return gson.fromJson(data_1, Person.class);
	}

	public void setComputed_person(Person computed_person) {
		this.computed_person = computed_person;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ByteBuffer getData() {
		return data;
	}



	public void setData(ByteBuffer data) {
		this.data = data;
	}

	public String getData_1() {
		return data_1;
	}

	public void setData_1(String data_1) {
		this.data_1 = data_1;
	}

	public String getComputed_data() {
		
		computed_data = new String( data.array(), StandardCharsets.UTF_8 );
		return computed_data;
	}

	public void setComputed_data(String computed_data) {
		this.computed_data = computed_data;
	}

	@Override
	public String toString() {
		return "BlobSampleEntity [" + " key=" + key + ", data=" + data + ", data_1=" + data_1
				+ ", computed_data=" + getComputed_data() + ", computed_person=" + getComputed_person() + "]";
	}
	
	
	

}
