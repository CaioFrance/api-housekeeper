package br.com.caiofrancelinoss.housekeeper.api.app.services;

import br.com.caiofrancelinoss.housekeeper.api.ApiHousekeeperApplication;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.AddressDataDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.CreateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.HousekeeperDetailDto;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.Gender;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import br.com.caiofrancelinoss.housekeeper.api.domain.services.HousekeeperService;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    classes = ApiHousekeeperApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@Transactional
class HousekeeperServiceTest {
    @Autowired
    private HousekeeperService service;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @BeforeEach
    public void beforeEach(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }
    
    @Test
    void createNewHousekeeperSuccess() {
        var addressDto =new AddressDataDto(
            "street", 
            "neighborhood", 
            "zipcode", 
            "city",
            "state",
            "", 
            "number");
        var createHousekeeperDto = new CreateHousekeeperDto(
            "Ana",
            "(92)99999-9999",
            "",
            "ana@email",
            randomNumber(20, 50),
            "",
            new BigDecimal("0.50"),
            addressDto,
            Gender.FEMALE
        );
        
        var createdResponse = restTemplate
            .postForEntity("/housekeepers/create", createHousekeeperDto, HousekeeperDetailDto.class);
        
        var responseBody = createdResponse.getBody();
        
        assertEquals(HttpStatus.CREATED, createdResponse.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(createHousekeeperDto.name(), responseBody.name());
        assertEquals(1L, responseBody.id());
        assertEquals(ProfileStatus.AVAILABLE, responseBody.status());
        assertNotNull(responseBody.address());
    }
    
    @Test
    void showHousekeeperById() {
        var addressDto =new AddressDataDto(
            "street",
            "neighborhood",
            "zipcode",
            "city",
            "state",
            "",
            "number");
        var createHousekeeperDto = new CreateHousekeeperDto(
            "Ana",
            "(92)99999-9999",
            "",
            "ana@email",
            randomNumber(18, 60),
            "",
            new BigDecimal("0.50"),
            addressDto,
            Gender.FEMALE
        );
        var searchId = 1L;
        
        service.createAHousekeeper(createHousekeeperDto);
        
        var housekeeper = service.findAHousekeeperById(searchId);
        
        assertEquals("Ana", housekeeper.getName());
        assertEquals(searchId, housekeeper.getId());
        assertNotNull(housekeeper.getAddress());
        assertNotNull(housekeeper.getCreatedAt());
        assertNotNull(housekeeper.getUpdatedAt());
    }
    
    private int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1));
    }
}