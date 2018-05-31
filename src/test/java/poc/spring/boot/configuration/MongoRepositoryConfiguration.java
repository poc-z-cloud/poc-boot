package poc.spring.boot.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"poc.spring.boot.domain.model"})
@EnableMongoRepositories(basePackages = "poc.spring.boot.domain.repository")
public class MongoRepositoryConfiguration {
}