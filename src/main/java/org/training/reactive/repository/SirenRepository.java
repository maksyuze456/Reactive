package org.training.reactive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training.reactive.model.Siren;

@Repository
public interface SirenRepository extends JpaRepository<Siren, Long> {
}
