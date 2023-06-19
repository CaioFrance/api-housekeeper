package br.com.caiofrancelinoss.housekeeper.api.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.caiofrancelinoss.housekeeper.api.app.dto.CreateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.PartialUpdateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.app.dto.UpdateHousekeeperDto;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.Gender;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "housekeeper")
@Table(name = "housekeepers")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Housekeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cellphone;
    private String telephone;
    private String email;
    private int age;
    private String experience;
    private BigDecimal pricePerHour;

    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Embedded
    private Address address;
    
    @ManyToMany
    @JoinTable(
        name = "housekeepers_skills",
        joinColumns = @JoinColumn(name = "housekeeper_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();
    
    @CreationTimestamp
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    public Housekeeper(CreateHousekeeperDto createHousekeeperDto) {
        this.name = createHousekeeperDto.name();
        this.cellphone = createHousekeeperDto.cellphone();
        this.telephone = createHousekeeperDto.telephone();
        this.email = createHousekeeperDto.email();
        this.age = createHousekeeperDto.age();
        this.experience = createHousekeeperDto.experience();
        this.pricePerHour = createHousekeeperDto.pricePerHour();
        this.status = ProfileStatus.AVAILABLE;
        this.gender = createHousekeeperDto.gender();
        this.address = new Address(createHousekeeperDto.address());
    }
    
    public void markAsDeleted() {
        this.status = ProfileStatus.EXCLUDED;
        this.deletedAt = LocalDateTime.now();
    }

    public void dataPartialUpdate(PartialUpdateHousekeeperDto partialUpdateHousekeeperDto) {
        if (partialUpdateHousekeeperDto.email() != null && !partialUpdateHousekeeperDto.email().equals("")) {
            this.email = partialUpdateHousekeeperDto.email();
        }
        
        if (partialUpdateHousekeeperDto.cellphone() != null && !partialUpdateHousekeeperDto.cellphone().equals("")) {
            this.cellphone = partialUpdateHousekeeperDto.cellphone();
        }
        
        if (partialUpdateHousekeeperDto.telephone() != null) {
            this.telephone = partialUpdateHousekeeperDto.telephone();
        }
        
        if (partialUpdateHousekeeperDto.address() != null) {
            this.address.dataUpdate(partialUpdateHousekeeperDto.address());
        }
        
        if (partialUpdateHousekeeperDto.status() != null) {
            this.status = partialUpdateHousekeeperDto.status();
        }
    }
    
    public void dataUpdate(UpdateHousekeeperDto updateHousekeeperDto) {
        this.cellphone = updateHousekeeperDto.cellphone();
        this.telephone = updateHousekeeperDto.telephone();
        this.email = updateHousekeeperDto.email();
        this.experience = updateHousekeeperDto.experience();
        this.status = updateHousekeeperDto.status();
        this.pricePerHour = updateHousekeeperDto.pricePerHour();
        this.address.dataUpdate(updateHousekeeperDto.address());
    }
}
