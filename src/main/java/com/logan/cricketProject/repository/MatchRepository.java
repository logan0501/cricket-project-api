package com.logan.cricketProject.repository;

import com.logan.cricketProject.entity.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match,String> {
}
