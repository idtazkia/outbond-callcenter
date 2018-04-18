package id.ac.tazkia.callcenter.outbound.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data @Entity
public class FollowupCall {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    private LocalDateTime callTime;

    @OneToOne @JoinColumn(name = "id_call_session")
    private CallSession callSession;

    @ManyToOne @JoinColumn(name = "id_prospect")
    private Prospect prospect;


}
