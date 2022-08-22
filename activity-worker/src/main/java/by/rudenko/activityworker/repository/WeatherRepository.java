package by.rudenko.activityworker.repository;

import by.rudenko.activityworker.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long>{
}
