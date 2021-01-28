package ee.mkv.jhipster.repository;

import ee.mkv.jhipster.domain.RandomNewEntity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RandomNewEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RandomNewEntityRepository extends JpaRepository<RandomNewEntity, Long> {
}
