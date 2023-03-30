package nusiss.paf.day28airbnb.Config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import nusiss.paf.day28airbnb.Repository.NumberDoubleToDoubleConverter;

@Configuration
public class MongoConfig {
    
    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory, MappingMongoConverter mappingMongoConverter) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory, mappingMongoConverter);
        return mongoTemplate;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory mongoDatabaseFactory, MongoCustomConversions mongoCustomConversions) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        mappingMongoConverter.setCustomConversions(mongoCustomConversions);
        mappingMongoConverter.afterPropertiesSet();
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingMongoConverter;
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new NumberDoubleToDoubleConverter()));
    }
}
