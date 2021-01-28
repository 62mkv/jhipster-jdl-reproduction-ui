package ee.mkv.jhipster.web.rest;

import ee.mkv.jhipster.domain.BusinessUnitConfig;
import ee.mkv.jhipster.repository.BusinessUnitConfigRepository;
import ee.mkv.jhipster.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ee.mkv.jhipster.domain.BusinessUnitConfig}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BusinessUnitConfigResource {

    private final Logger log = LoggerFactory.getLogger(BusinessUnitConfigResource.class);

    private static final String ENTITY_NAME = "businessUnitConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BusinessUnitConfigRepository businessUnitConfigRepository;

    public BusinessUnitConfigResource(BusinessUnitConfigRepository businessUnitConfigRepository) {
        this.businessUnitConfigRepository = businessUnitConfigRepository;
    }

    /**
     * {@code POST  /business-unit-configs} : Create a new businessUnitConfig.
     *
     * @param businessUnitConfig the businessUnitConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new businessUnitConfig, or with status {@code 400 (Bad Request)} if the businessUnitConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/business-unit-configs")
    public ResponseEntity<BusinessUnitConfig> createBusinessUnitConfig(@Valid @RequestBody BusinessUnitConfig businessUnitConfig) throws URISyntaxException {
        log.debug("REST request to save BusinessUnitConfig : {}", businessUnitConfig);
        if (businessUnitConfig.getId() != null) {
            throw new BadRequestAlertException("A new businessUnitConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessUnitConfig result = businessUnitConfigRepository.save(businessUnitConfig);
        return ResponseEntity.created(new URI("/api/business-unit-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /business-unit-configs} : Updates an existing businessUnitConfig.
     *
     * @param businessUnitConfig the businessUnitConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated businessUnitConfig,
     * or with status {@code 400 (Bad Request)} if the businessUnitConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the businessUnitConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/business-unit-configs")
    public ResponseEntity<BusinessUnitConfig> updateBusinessUnitConfig(@Valid @RequestBody BusinessUnitConfig businessUnitConfig) throws URISyntaxException {
        log.debug("REST request to update BusinessUnitConfig : {}", businessUnitConfig);
        if (businessUnitConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BusinessUnitConfig result = businessUnitConfigRepository.save(businessUnitConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, businessUnitConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /business-unit-configs} : get all the businessUnitConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of businessUnitConfigs in body.
     */
    @GetMapping("/business-unit-configs")
    public ResponseEntity<List<BusinessUnitConfig>> getAllBusinessUnitConfigs(Pageable pageable) {
        log.debug("REST request to get a page of BusinessUnitConfigs");
        Page<BusinessUnitConfig> page = businessUnitConfigRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /business-unit-configs/:id} : get the "id" businessUnitConfig.
     *
     * @param id the id of the businessUnitConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the businessUnitConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/business-unit-configs/{id}")
    public ResponseEntity<BusinessUnitConfig> getBusinessUnitConfig(@PathVariable Long id) {
        log.debug("REST request to get BusinessUnitConfig : {}", id);
        Optional<BusinessUnitConfig> businessUnitConfig = businessUnitConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(businessUnitConfig);
    }

    /**
     * {@code DELETE  /business-unit-configs/:id} : delete the "id" businessUnitConfig.
     *
     * @param id the id of the businessUnitConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/business-unit-configs/{id}")
    public ResponseEntity<Void> deleteBusinessUnitConfig(@PathVariable Long id) {
        log.debug("REST request to delete BusinessUnitConfig : {}", id);
        businessUnitConfigRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
