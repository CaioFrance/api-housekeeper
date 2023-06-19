package br.com.caiofrancelinoss.housekeeper.api.domain.repositories;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.Housekeeper;

public interface HousekeeperRepository extends JpaRepository<Housekeeper, Long> {
    Page<Housekeeper> findByStatusNot(ProfileStatus status, Pageable pageable);
}
