package ee.mkv.jhipster.repository;

import ee.mkv.jhipster.domain.DepotCountryMapping;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DepotCountryMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepotCountryMappingRepository extends JpaRepository<DepotCountryMapping, Long> {
}
