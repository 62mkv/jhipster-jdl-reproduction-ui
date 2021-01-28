package ee.mkv.jhipster.web.rest;

import ee.mkv.jhipster.domain.DepotCountryMapping;
import ee.mkv.jhipster.repository.DepotCountryMappingRepository;
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
 * REST controller for managing {@link ee.mkv.jhipster.domain.DepotCountryMapping}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DepotCountryMappingResource {

    private final Logger log = LoggerFactory.getLogger(DepotCountryMappingResource.class);

    private static final String ENTITY_NAME = "depotCountryMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepotCountryMappingRepository depotCountryMappingRepository;

    public DepotCountryMappingResource(DepotCountryMappingRepository depotCountryMappingRepository) {
        this.depotCountryMappingRepository = depotCountryMappingRepository;
    }

    /**
     * {@code POST  /depot-country-mappings} : Create a new depotCountryMapping.
     *
     * @param depotCountryMapping the depotCountryMapping to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depotCountryMapping, or with status {@code 400 (Bad Request)} if the depotCountryMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/depot-country-mappings")
    public ResponseEntity<DepotCountryMapping> createDepotCountryMapping(@Valid @RequestBody DepotCountryMapping depotCountryMapping) throws URISyntaxException {
        log.debug("REST request to save DepotCountryMapping : {}", depotCountryMapping);
        if (depotCountryMapping.getId() != null) {
            throw new BadRequestAlertException("A new depotCountryMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepotCountryMapping result = depotCountryMappingRepository.save(depotCountryMapping);
        return ResponseEntity.created(new URI("/api/depot-country-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /depot-country-mappings} : Updates an existing depotCountryMapping.
     *
     * @param depotCountryMapping the depotCountryMapping to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depotCountryMapping,
     * or with status {@code 400 (Bad Request)} if the depotCountryMapping is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depotCountryMapping couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/depot-country-mappings")
    public ResponseEntity<DepotCountryMapping> updateDepotCountryMapping(@Valid @RequestBody DepotCountryMapping depotCountryMapping) throws URISyntaxException {
        log.debug("REST request to update DepotCountryMapping : {}", depotCountryMapping);
        if (depotCountryMapping.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepotCountryMapping result = depotCountryMappingRepository.save(depotCountryMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, depotCountryMapping.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /depot-country-mappings} : get all the depotCountryMappings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of depotCountryMappings in body.
     */
    @GetMapping("/depot-country-mappings")
    public ResponseEntity<List<DepotCountryMapping>> getAllDepotCountryMappings(Pageable pageable) {
        log.debug("REST request to get a page of DepotCountryMappings");
        Page<DepotCountryMapping> page = depotCountryMappingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /depot-country-mappings/:id} : get the "id" depotCountryMapping.
     *
     * @param id the id of the depotCountryMapping to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the depotCountryMapping, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/depot-country-mappings/{id}")
    public ResponseEntity<DepotCountryMapping> getDepotCountryMapping(@PathVariable Long id) {
        log.debug("REST request to get DepotCountryMapping : {}", id);
        Optional<DepotCountryMapping> depotCountryMapping = depotCountryMappingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(depotCountryMapping);
    }

    /**
     * {@code DELETE  /depot-country-mappings/:id} : delete the "id" depotCountryMapping.
     *
     * @param id the id of the depotCountryMapping to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/depot-country-mappings/{id}")
    public ResponseEntity<Void> deleteDepotCountryMapping(@PathVariable Long id) {
        log.debug("REST request to delete DepotCountryMapping : {}", id);
        depotCountryMappingRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
