package com.uken.platform.leaderboard;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaderboardController {

	@Autowired
	private LeaderboardService leaderboardService;

	@RequestMapping(method = RequestMethod.POST, path = "/score/set")
	public void setScore(@RequestBody(required = true) Map<String, String> body) {
		leaderboardService.setScore(body.get("user"), Double.parseDouble(body.get("score")));
	}

	@RequestMapping("/score/get/{name}")
	public double getScore(@PathVariable String name) {
		return leaderboardService.getScore(name);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/score/increment")
	public void incrementScore(@RequestBody Map<String, String> body) {
		leaderboardService.incrementScore(body.get("user"), Double.parseDouble(body.get("delta")));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/score/decrement")
	public void decrementScore(@RequestBody Map<String, String> body) {
		leaderboardService.decrementScore(body.get("user"), Double.parseDouble(body.get("delta")));
	}

	@RequestMapping("/rank/get/{name}")
	public double getRank(@PathVariable String name) {
		return leaderboardService.getRank(name);
	}

	@RequestMapping("rank/get")
	public Set<String> getUsersByRankRange(@RequestParam("bottom") String bottom, @RequestParam("top") String top) {
		return leaderboardService.getUsersByRankRange(Long.parseLong(bottom), Long.parseLong(top));
	}

}
