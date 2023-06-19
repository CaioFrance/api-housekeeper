package br.com.caiofrancelinoss.housekeeper.api.app.controllers;

import br.com.caiofrancelinoss.housekeeper.api.app.dto.CreateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.HousekeeperDetailDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.PartialUpdateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.UpdateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.services.HousekeeperService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("housekeepers")
public class HousekeeperController {
    private final HousekeeperService housekeeperService;

    public HousekeeperController(HousekeeperService housekeeperService) {
        this.housekeeperService = housekeeperService;
    }

    @PostMapping("create")
    public ResponseEntity<HousekeeperDetailDto> createAHousekeeper(
        @RequestBody @Valid CreateHousekeeperDto createHousekeeperDto,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var housekeeper = housekeeperService.createAHousekeeper(createHousekeeperDto);
        
        var uri = uriComponentsBuilder.path("/show/{id}").buildAndExpand(housekeeper.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new HousekeeperDetailDto(housekeeper));
    }
    
    @GetMapping("show/{id}")
    public ResponseEntity<HousekeeperDetailDto> showAHousekeeper(@PathVariable Long id) {
        var housekeeper = housekeeperService.findAHousekeeperById(id);
        
        return ResponseEntity.ok(new HousekeeperDetailDto(housekeeper));
    }
    
    @GetMapping
    public ResponseEntity<Page<HousekeeperDetailDto>> showAllHousekeepers(
        @PageableDefault(sort = "name", direction = Sort.Direction.ASC)
        Pageable pageable
    ) {
        var housekeepers = housekeeperService.getAllHousekeeperPerPage(pageable);
        
        return ResponseEntity.ok(housekeepers);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAHousekeeperById(@PathVariable Long id) {
        housekeeperService.deleteAHousekeeperById(id);
        
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/update/{id}")
    public ResponseEntity<HousekeeperDetailDto> partialUpdateAHousekeeper(
        @PathVariable Long id,
        @RequestBody @Valid PartialUpdateHousekeeperDto partialUpdateHousekeeperDto
    ) {
        var housekeeper = housekeeperService.partialUpdateHousekeeper(id, partialUpdateHousekeeperDto);
        
        return ResponseEntity.ok(new HousekeeperDetailDto(housekeeper));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<HousekeeperDetailDto> updateAHousekeeper(
        @PathVariable Long id,
        @RequestBody @Valid UpdateHousekeeperDto updateHousekeeperDto
    ) {
        var housekeeper = housekeeperService.updateHouseKeeper(id, updateHousekeeperDto);
        
        return ResponseEntity.ok(new HousekeeperDetailDto(housekeeper));
    }
}
