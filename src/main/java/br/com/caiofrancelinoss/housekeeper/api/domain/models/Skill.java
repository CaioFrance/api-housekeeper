package br.com.caiofrancelinoss.housekeeper.api.domain.models;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.SkillLevel;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.SkillStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "skill")
@Table(name = "skills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    
    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;
    
    @Enumerated(EnumType.STRING)
    private SkillStatus status;
    
    @ManyToMany(mappedBy = "skills")
    private Set<Housekeeper> housekeepers = new HashSet<>();

    @CreationTimestamp
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;
}
