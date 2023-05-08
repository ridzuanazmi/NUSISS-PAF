package nusiss.paf.revisionday26workshop.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration  
public class AppConfig {
    
    @Value("${mongo.url}")
    private String mongoUrl;

    @Bean
    public MongoTemplate createMongoTemplate() {
        // Create a MongoClient
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, "boardgames"); // REMEMBER TO CHANGE THE DATABASE NAME
    }

    @Bean
    public WebMvcConfigurer configureCors() {
        return new EnableCors("/api/*", "*");
    }
}
