package com.ameren.outage.outageloadsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class OutageLoadSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutageLoadSimulatorApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
	    jedisConFactory.setHostName("localhost");
	    jedisConFactory.setPort(6379);
	    return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
}
