package com.logan.cricketProject.controller.matchController;

import com.logan.cricketProject.entity.Match;
import com.logan.cricketProject.service.matchService.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MatchController {
    private final MatchServiceImpl matchService;

    @Autowired
    public MatchController(MatchServiceImpl matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/match")
    public Match createMatch(@RequestBody Match match){
       return matchService.createMatch(match);
    }
}
