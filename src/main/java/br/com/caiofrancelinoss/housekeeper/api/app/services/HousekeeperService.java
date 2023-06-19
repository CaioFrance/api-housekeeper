package br.com.caiofrancelinoss.housekeeper.api.app.services;

import br.com.caiofrancelinoss.housekeeper.api.app.dto.CreateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.HousekeeperDetailDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.PartialUpdateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.UpdateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.Housekeeper;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import br.com.caiofrancelinoss.housekeeper.api.domain.repositories.HousekeeperRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HousekeeperService {
    private final HousekeeperRepository housekeeperRepository;

    public HousekeeperService(HousekeeperRepository housekeeperRepository) {
        this.housekeeperRepository = housekeeperRepository;
    }
    
    @Transactional
    public Housekeeper createAHousekeeper(CreateHousekeeperDto createHousekeeperDto) {
        var housekeeper = new Housekeeper(createHousekeeperDto);
        
        housekeeperRepository.saveAndFlush(housekeeper);
        
        return housekeeper;
    }
    
    public Housekeeper findAHousekeeperById(Long id) {
        return housekeeperRepository.getReferenceById(id);
    }
    
    public Page<HousekeeperDetailDto> getAllHousekeeperPerPage(Pageable pageable) {
        return housekeeperRepository.findByStatusNot(ProfileStatus.EXCLUDED, pageable).map(HousekeeperDetailDto::new);
    }
    
    @Transactional
    public void deleteAHousekeeperById(Long id) {
        var housekeeper = findAHousekeeperById(id);
        
        housekeeper.markAsDeleted();
    }
    
    @Transactional
    public Housekeeper partialUpdateHousekeeper(Long id, PartialUpdateHousekeeperDto partialUpdateHousekeeperDto) {
        var housekeeper = findAHousekeeperById(id);
        
        housekeeper.dataPartialUpdate(partialUpdateHousekeeperDto);
        
        return housekeeper;
    }
    
    @Transactional
    public Housekeeper updateHouseKeeper(Long id, UpdateHousekeeperDto updateHousekeeperDto) {
        var housekeeper = findAHousekeeperById(id);
        
        housekeeper.dataUpdate(updateHousekeeperDto);
        
        return housekeeper;
    }
}
