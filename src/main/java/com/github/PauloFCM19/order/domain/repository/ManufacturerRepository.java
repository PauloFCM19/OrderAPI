package com.github.PauloFCM19.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.PauloFCM19.order.domain.model.Manufacturer;




@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
