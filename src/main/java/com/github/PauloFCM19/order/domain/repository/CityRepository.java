package com.github.PauloFCM19.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.PauloFCM19.order.domain.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
