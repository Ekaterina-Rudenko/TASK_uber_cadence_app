package by.rudenko.activityworker.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Weather")
@Table(name = "weather")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "record_date")
    private LocalDateTime recordDate;

}
