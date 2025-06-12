package org.training.reactive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training.reactive.model.Fire;

@Repository
public interface FireRepository extends JpaRepository<Fire, Long> {
}
