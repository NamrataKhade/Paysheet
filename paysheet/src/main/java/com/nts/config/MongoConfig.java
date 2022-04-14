/*
 * package com.nts.config;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.PropertySource; import
 * org.springframework.data.mongodb.core.MongoTemplate;
 * 
 * import com.mongodb.client.MongoClient; import
 * com.mongodb.client.MongoClients;
 * 
 * @Configuration
 * 
 * @PropertySource(value = { "classpath:application.yml" }) public class
 * MongoConfig {
 * 
 * @Value("${spring.data.mongodb.client}") private String MongoDbClient;
 * 
 * @Value("${spring.data.mongodb.database}") private String databaseName;
 * 
 * public MongoClient mongoClient() { return MongoClients.create(MongoDbClient);
 * }
 * 
 * @Bean public MongoTemplate mongoTemplate() { return new
 * MongoTemplate(mongoClient(), databaseName); }
 * 
 * }
 */