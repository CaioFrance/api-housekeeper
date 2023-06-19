package br.com.caiofrancelinoss.housekeeper.api.app.services;

import br.com.caiofrancelinoss.housekeeper.api.app.dto.AddressDataDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.CreateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.Address;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.Housekeeper;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.Gender;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import br.com.caiofrancelinoss.housekeeper.api.domain.repositories.HousekeeperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;

class HousekeeperServiceTest {

    @Mock
    private HousekeeperRepository housekeeperRepository;
    
    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSaveAHousekeeperWhenPassedValidParams() {
        var addressDto =new AddressDataDto(
            "street", 
            "neighborhood", 
            "zipcode", 
            "city",
            "state",
            "", 
            "number");
        
        var createHousekeeperDto = new CreateHousekeeperDto(
            "Test ",
            "(92)99999-9999",
            "",
            "test@email",
            randomNumber(20, 50),
            "",
            new BigDecimal("0.50"),
            addressDto,
            Gender.FEMALE
        );
        var housekeeper = new Housekeeper(createHousekeeperDto);
        
        Mockito.when(housekeeperRepository.saveAndFlush(housekeeper))
            .thenReturn(housekeeperFake(1L, createHousekeeperDto));
        
        var newHousekeeper = housekeeperRepository.saveAndFlush(housekeeper);
        
        Mockito.verify(housekeeperRepository).saveAndFlush(housekeeper);
        
        assertNotNull(newHousekeeper);
        assertEquals(1L, newHousekeeper.getId());
        assertEquals(Housekeeper.class, newHousekeeper.getClass());
    }
    
    @Test
    void shouldReturnAHousekeeperWhenPassedId() {
        var searchId = 1L;
        
        Mockito.when(housekeeperRepository.getReferenceById(searchId))
            .thenReturn(housekeeperFake(searchId, ProfileStatus.AVAILABLE, LocalDateTime.now(), LocalDateTime.now(), null));
        
        var housekeeper = housekeeperRepository.getReferenceById(searchId);
        
        Mockito.verify(housekeeperRepository).getReferenceById(searchId);
        
        assertNotNull(housekeeper);
        assertEquals(searchId, housekeeper.getId());
        assertEquals(ProfileStatus.AVAILABLE, housekeeper.getStatus());
    }
    
    private Housekeeper housekeeperFake(long id, ProfileStatus profileStatus, LocalDateTime createdAt,
                                        LocalDateTime updatedAt, LocalDateTime deletedAt) {
        var randomAge = randomNumber(18, 60);
        
        var address = new Address(
            "street", 
            "neighborhood",
            "zipcode",
            "city",
            "state",
            "complement",
            "number"
        );
        
        return new Housekeeper(
            id,
            "Test",
            "(99)9999-9999",
            "",
            "test@email",
            randomAge,
            "",
            new BigDecimal("0.50"),
            profileStatus,
            Gender.MALE,
            address,
            new HashSet<>(),
            createdAt,
            updatedAt,
            deletedAt
        );
    }
    
    private Housekeeper housekeeperFake(long id, CreateHousekeeperDto createHousekeeperDto) {
        return new Housekeeper(
            id,
            createHousekeeperDto.name(),
            createHousekeeperDto.cellphone(),
            createHousekeeperDto.telephone(),
            createHousekeeperDto.email(),
            createHousekeeperDto.age(),
            createHousekeeperDto.experience(),
            createHousekeeperDto.pricePerHour(),
            ProfileStatus.AVAILABLE,
            createHousekeeperDto.gender(),
            new Address(createHousekeeperDto.address()),
            new HashSet<>(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            null
        );
    }
    
    private int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1));
    }
}