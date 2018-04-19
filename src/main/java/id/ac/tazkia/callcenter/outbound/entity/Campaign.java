package id.ac.tazkia.callcenter.outbound.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data @Entity
public class Campaign {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull @NotEmpty @Column(unique = true)
    private String name;
    private String description;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "campaign_prospect",
            joinColumns = @JoinColumn(name = "id_campaign"),
            inverseJoinColumns = @JoinColumn(name = "id_prospect")
    )
    private Set<Prospect> prospects = new HashSet<>();
}
