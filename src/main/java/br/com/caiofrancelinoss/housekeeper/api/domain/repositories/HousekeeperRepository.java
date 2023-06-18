package br.com.caiofrancelinoss.housekeeper.api.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.Housekeeper;

public interface HousekeeperRepository extends JpaRepository<Housekeeper, Long> {    
}
