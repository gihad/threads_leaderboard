package com.uken.platform.leaderboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@PropertySource("application.properties")
public class Application {

	@Value("${redis.hostname}")
	private String redisHostName;

	@Value("${redis.port}")
	private int redisPort;

	@Bean
	public LeaderboardService leaderboardservice() {
		return new LeaderboardService();
	}

	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

		jedisConnectionFactory.setHostName(redisHostName);
		jedisConnectionFactory.setPort(redisPort);
		jedisConnectionFactory.setUsePool(true);

		return jedisConnectionFactory;
	}

	@Bean
	public StringRedisTemplate getStringRedisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate(getJedisConnectionFactory());
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
