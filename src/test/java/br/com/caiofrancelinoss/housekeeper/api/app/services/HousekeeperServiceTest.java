package br.com.caiofrancelinoss.housekeeper.api.app.services;

import br.com.caiofrancelinoss.housekeeper.api.ApiHousekeeperApplication;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.AddressDataDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.CreateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.ErrorsDataValidation;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.Gender;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import br.com.caiofrancelinoss.housekeeper.api.domain.services.HousekeeperService;
import jakarta.persistence.EntityNotFoundException;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

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
            22,
            "",
            new BigDecimal("0.50"),
            addressDto,
            Gender.FEMALE
        );

        var housekeeperCreated = service.createAHousekeeper(createHousekeeperDto);

        assertEquals(createHousekeeperDto.name(), housekeeperCreated.getName());
        assertEquals(1L, housekeeperCreated.getId());
        assertEquals(ProfileStatus.AVAILABLE, housekeeperCreated.getStatus());
    }

    @Test
    void createNewHousekeeperFailure() {
        var createHousekeeperDto = new CreateHousekeeperDto(
            "Ana",
            "(92)99999-9999",
            "",
            "ana@email",
            30,
            "",
            new BigDecimal("0.50"),
            null,
            Gender.FEMALE
        );

        var createdResponseFail = restTemplate
            .postForEntity("/housekeepers/create", createHousekeeperDto, ErrorsDataValidation[].class);

        var responseBody = createdResponseFail.getBody();

        var expectBody = new ErrorsDataValidation[]{
            new ErrorsDataValidation("address", "não deve ser nulo")
        };

        assertEquals(HttpStatus.BAD_REQUEST, createdResponseFail.getStatusCode());
        assertNotNull(responseBody);
        assertArrayEquals(expectBody, Arrays.stream(responseBody).toArray());
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
            22,
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

    @Test
    void showHousekeeperByIdFailure() {
        var searchId = 1L;            

        assertThrows(EntityNotFoundException.class, () -> {
            service.findAHousekeeperById(searchId);
        });
    }
}