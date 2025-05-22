package com.logan.cricketProject.repository;

import com.logan.cricketProject.entity.TeamDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<TeamDAO, String> {
}
