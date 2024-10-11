package com.irojas.demojwt.sportRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Player;
import com.irojas.demojwt.sport.Model.PlayerLeague;

public interface PlayerLeagueRepository extends JpaRepository<PlayerLeague, Long>{
	
	public Optional<PlayerLeague> findByPlayerAndLeague(Player player, League league);
	
	public List<PlayerLeague> findByPlayerId(Long playerId);
	
}
