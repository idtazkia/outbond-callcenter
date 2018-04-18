package id.ac.tazkia.callcenter.outbound.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data @Entity
public class CallSession {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_prospect")
    private Prospect prospect;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull
    private LocalDateTime callStartTime = LocalDateTime.now();

    @NotNull
    private LocalDateTime callEndTime = LocalDateTime.now();


    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_call_result")
    private CallResult callResult;

    private String remarks;

    @OneToOne(mappedBy = "callSession")
    private FollowupCall followupCall;
}
