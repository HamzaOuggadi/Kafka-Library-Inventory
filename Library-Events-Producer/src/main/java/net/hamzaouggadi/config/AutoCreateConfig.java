package net.hamzaouggadi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateConfig {

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name("Library_Events")
                .partitions(3)
                .replicas(3)
                .build();
    }
}