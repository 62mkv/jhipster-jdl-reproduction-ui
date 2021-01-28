package ee.mkv.jhipster.web.rest;

import ee.mkv.jhipster.domain.RandomNewEntity;
import ee.mkv.jhipster.repository.RandomNewEntityRepository;
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
 * REST controller for managing {@link ee.mkv.jhipster.domain.RandomNewEntity}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RandomNewEntityResource {

    private final Logger log = LoggerFactory.getLogger(RandomNewEntityResource.class);

    private static final String ENTITY_NAME = "randomNewEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RandomNewEntityRepository randomNewEntityRepository;

    public RandomNewEntityResource(RandomNewEntityRepository randomNewEntityRepository) {
        this.randomNewEntityRepository = randomNewEntityRepository;
    }

    /**
     * {@code POST  /random-new-entities} : Create a new randomNewEntity.
     *
     * @param randomNewEntity the randomNewEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new randomNewEntity, or with status {@code 400 (Bad Request)} if the randomNewEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/random-new-entities")
    public ResponseEntity<RandomNewEntity> createRandomNewEntity(@Valid @RequestBody RandomNewEntity randomNewEntity) throws URISyntaxException {
        log.debug("REST request to save RandomNewEntity : {}", randomNewEntity);
        if (randomNewEntity.getId() != null) {
            throw new BadRequestAlertException("A new randomNewEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RandomNewEntity result = randomNewEntityRepository.save(randomNewEntity);
        return ResponseEntity.created(new URI("/api/random-new-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /random-new-entities} : Updates an existing randomNewEntity.
     *
     * @param randomNewEntity the randomNewEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated randomNewEntity,
     * or with status {@code 400 (Bad Request)} if the randomNewEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the randomNewEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/random-new-entities")
    public ResponseEntity<RandomNewEntity> updateRandomNewEntity(@Valid @RequestBody RandomNewEntity randomNewEntity) throws URISyntaxException {
        log.debug("REST request to update RandomNewEntity : {}", randomNewEntity);
        if (randomNewEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RandomNewEntity result = randomNewEntityRepository.save(randomNewEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, randomNewEntity.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /random-new-entities} : get all the randomNewEntities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of randomNewEntities in body.
     */
    @GetMapping("/random-new-entities")
    public ResponseEntity<List<RandomNewEntity>> getAllRandomNewEntities(Pageable pageable) {
        log.debug("REST request to get a page of RandomNewEntities");
        Page<RandomNewEntity> page = randomNewEntityRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /random-new-entities/:id} : get the "id" randomNewEntity.
     *
     * @param id the id of the randomNewEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the randomNewEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/random-new-entities/{id}")
    public ResponseEntity<RandomNewEntity> getRandomNewEntity(@PathVariable Long id) {
        log.debug("REST request to get RandomNewEntity : {}", id);
        Optional<RandomNewEntity> randomNewEntity = randomNewEntityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(randomNewEntity);
    }

    /**
     * {@code DELETE  /random-new-entities/:id} : delete the "id" randomNewEntity.
     *
     * @param id the id of the randomNewEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/random-new-entities/{id}")
    public ResponseEntity<Void> deleteRandomNewEntity(@PathVariable Long id) {
        log.debug("REST request to delete RandomNewEntity : {}", id);
        randomNewEntityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
