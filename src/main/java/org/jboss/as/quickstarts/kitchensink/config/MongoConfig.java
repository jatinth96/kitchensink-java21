package org.jboss.as.quickstarts.kitchensink.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "kitchensink";
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017"); // Customize if needed
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), getDatabaseName());

        // Create indexes for "member" collection
        IndexOperations indexOps = mongoTemplate.indexOps("members");

        indexOps.ensureIndex(new Index().on("email", org.springframework.data.domain.Sort.Direction.ASC).unique());
        indexOps.ensureIndex(new Index().on("phoneNumber", org.springframework.data.domain.Sort.Direction.ASC).unique());
        indexOps.ensureIndex(new Index().on("name", org.springframework.data.domain.Sort.Direction.ASC));

        return mongoTemplate;
    }
}
