package com.gabrielspassos.service;

import com.gabrielspassos.domain.SoccerTeam;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TeamService {

    public List<SoccerTeam> findAll() {
        return SoccerTeam.listAll();
    }

    public Optional<SoccerTeam> findById(Long id) {
        return Optional.ofNullable(SoccerTeam.findById(id));
    }

    @Transactional
    public SoccerTeam save(SoccerTeam soccerTeam) {
        soccerTeam.persist();
        return soccerTeam;
    }

    @Transactional
    public boolean delete(Long id) {
        return SoccerTeam.deleteById(id);
    }

}
