package com.uken.platform.leaderboard;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class LeaderboardService {

	private static final String LEADERBOARD_NAME = "leaderboard";

	@Autowired
	private StringRedisTemplate redisTemplate;

	public void setScore(String user, double score) {
		redisTemplate.opsForZSet().add(LEADERBOARD_NAME, user, score);
	}

	public double getScore(String user) {
		return Optional.ofNullable(redisTemplate.opsForZSet().score(LEADERBOARD_NAME, user)).orElse(0.0);
	}

	public void incrementScore(String user, double delta) {
		redisTemplate.opsForZSet().incrementScore(LEADERBOARD_NAME, user, delta);
	}

	public void decrementScore(String user, double delta) {
		incrementScore(user, -delta);
	}

	public long getRank(String user) {
		return Optional.ofNullable(redisTemplate.opsForZSet().reverseRank(LEADERBOARD_NAME, user)).orElse(0L);
	}

	public Set<String> getUsersByRankRange(long bottom, long top) {
		return Optional.ofNullable(redisTemplate.opsForZSet().reverseRange(LEADERBOARD_NAME, bottom, top))
				.orElse(Collections.emptySet());
	}
}
