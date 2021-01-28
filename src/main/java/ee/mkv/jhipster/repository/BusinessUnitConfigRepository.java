package ee.mkv.jhipster.repository;

import ee.mkv.jhipster.domain.BusinessUnitConfig;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BusinessUnitConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessUnitConfigRepository extends JpaRepository<BusinessUnitConfig, Long> {
}
