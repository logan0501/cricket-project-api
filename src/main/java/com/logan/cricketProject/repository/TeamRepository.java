package com.logan.cricketProject.repository;

import com.logan.cricketProject.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
}
