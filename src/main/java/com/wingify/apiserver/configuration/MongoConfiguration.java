package com.wingify.apiserver.configuration;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoConfiguration {

	@Value(value = "${spring.data.mongodb.host}")
	private String host;

	@Value(value = "${spring.data.mongodb.port}")
	private Integer port;

	@Value(value = "${spring.data.mongodb.database}")
	private String db;

	@Bean
	public MongoClient client() throws UnknownHostException {
		List<ServerAddress> seeds = new ArrayList<>();
		String arr[] = host.split(",");
		for (String ip : arr)
			seeds.add(new ServerAddress(ip, port));
		MongoClient client = new MongoClient(seeds, new MongoClientOptions.Builder().socketTimeout(15000)
				.connectTimeout(1000).maxWaitTime(1000).writeConcern(WriteConcern.JOURNALED).build());
		return client;
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(client(), db);
		return simpleMongoDbFactory;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	@Bean
	public MongoDatabase getMongoDB() throws UnknownHostException {
		MongoDatabase database = client().getDatabase(db);
		return database;
	}
}
