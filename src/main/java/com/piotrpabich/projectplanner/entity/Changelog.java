package com.piotrpabich.projectplanner.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piotrpabich.projectplanner.enums.Action;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Changelog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Date timestamp;

    private String changes;

    @Enumerated(EnumType.STRING)
    private Action action;

//    @ManyToOne
//    private User user;

    @JsonProperty("additional_information")
    private String additionalInformation;


}
