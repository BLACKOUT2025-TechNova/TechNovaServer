package com.techNova.techNovaApplication.parking.model;

import com.techNova.techNovaApplication.parking.dto.ScoreAndDetailDto;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Evaluation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "score_detail", joinColumns = @JoinColumn(name = "evaluation_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "score_and_detail")
    private Map<String, ScoreAndDetailDto> evaluations;

    public Evaluation(Map<String, ScoreAndDetailDto> evaluations) {
        this.evaluations = evaluations;
    }
}
