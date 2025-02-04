package com.github.PauloFCM19.order.domain.repository;

import com.github.PauloFCM19.order.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
