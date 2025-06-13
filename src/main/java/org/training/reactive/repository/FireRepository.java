package org.training.reactive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training.reactive.model.Fire;
import org.training.reactive.model.Status;

import java.util.List;

@Repository
public interface FireRepository extends JpaRepository<Fire, Long> {
    List<Fire> findAllByStatus(Status status);
}
