package ee.mkv.jhipster.web.rest;

import ee.mkv.jhipster.ReproductionUiApp;
import ee.mkv.jhipster.domain.RandomNewEntity;
import ee.mkv.jhipster.repository.RandomNewEntityRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RandomNewEntityResource} REST controller.
 */
@SpringBootTest(classes = ReproductionUiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RandomNewEntityResourceIT {

    private static final String DEFAULT_ENTITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOME_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_SOME_VALUE = "BBBBBBBBBB";

    @Autowired
    private RandomNewEntityRepository randomNewEntityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRandomNewEntityMockMvc;

    private RandomNewEntity randomNewEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RandomNewEntity createEntity(EntityManager em) {
        RandomNewEntity randomNewEntity = new RandomNewEntity()
            .entityName(DEFAULT_ENTITY_NAME)
            .someValue(DEFAULT_SOME_VALUE);
        return randomNewEntity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RandomNewEntity createUpdatedEntity(EntityManager em) {
        RandomNewEntity randomNewEntity = new RandomNewEntity()
            .entityName(UPDATED_ENTITY_NAME)
            .someValue(UPDATED_SOME_VALUE);
        return randomNewEntity;
    }

    @BeforeEach
    public void initTest() {
        randomNewEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createRandomNewEntity() throws Exception {
        int databaseSizeBeforeCreate = randomNewEntityRepository.findAll().size();
        // Create the RandomNewEntity
        restRandomNewEntityMockMvc.perform(post("/api/random-new-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(randomNewEntity)))
            .andExpect(status().isCreated());

        // Validate the RandomNewEntity in the database
        List<RandomNewEntity> randomNewEntityList = randomNewEntityRepository.findAll();
        assertThat(randomNewEntityList).hasSize(databaseSizeBeforeCreate + 1);
        RandomNewEntity testRandomNewEntity = randomNewEntityList.get(randomNewEntityList.size() - 1);
        assertThat(testRandomNewEntity.getEntityName()).isEqualTo(DEFAULT_ENTITY_NAME);
        assertThat(testRandomNewEntity.getSomeValue()).isEqualTo(DEFAULT_SOME_VALUE);
    }

    @Test
    @Transactional
    public void createRandomNewEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = randomNewEntityRepository.findAll().size();

        // Create the RandomNewEntity with an existing ID
        randomNewEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRandomNewEntityMockMvc.perform(post("/api/random-new-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(randomNewEntity)))
            .andExpect(status().isBadRequest());

        // Validate the RandomNewEntity in the database
        List<RandomNewEntity> randomNewEntityList = randomNewEntityRepository.findAll();
        assertThat(randomNewEntityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEntityNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = randomNewEntityRepository.findAll().size();
        // set the field null
        randomNewEntity.setEntityName(null);

        // Create the RandomNewEntity, which fails.


        restRandomNewEntityMockMvc.perform(post("/api/random-new-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(randomNewEntity)))
            .andExpect(status().isBadRequest());

        List<RandomNewEntity> randomNewEntityList = randomNewEntityRepository.findAll();
        assertThat(randomNewEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSomeValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = randomNewEntityRepository.findAll().size();
        // set the field null
        randomNewEntity.setSomeValue(null);

        // Create the RandomNewEntity, which fails.


        restRandomNewEntityMockMvc.perform(post("/api/random-new-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(randomNewEntity)))
            .andExpect(status().isBadRequest());

        List<RandomNewEntity> randomNewEntityList = randomNewEntityRepository.findAll();
        assertThat(randomNewEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRandomNewEntities() throws Exception {
        // Initialize the database
        randomNewEntityRepository.saveAndFlush(randomNewEntity);

        // Get all the randomNewEntityList
        restRandomNewEntityMockMvc.perform(get("/api/random-new-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(randomNewEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].entityName").value(hasItem(DEFAULT_ENTITY_NAME)))
            .andExpect(jsonPath("$.[*].someValue").value(hasItem(DEFAULT_SOME_VALUE)));
    }
    
    @Test
    @Transactional
    public void getRandomNewEntity() throws Exception {
        // Initialize the database
        randomNewEntityRepository.saveAndFlush(randomNewEntity);

        // Get the randomNewEntity
        restRandomNewEntityMockMvc.perform(get("/api/random-new-entities/{id}", randomNewEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(randomNewEntity.getId().intValue()))
            .andExpect(jsonPath("$.entityName").value(DEFAULT_ENTITY_NAME))
            .andExpect(jsonPath("$.someValue").value(DEFAULT_SOME_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingRandomNewEntity() throws Exception {
        // Get the randomNewEntity
        restRandomNewEntityMockMvc.perform(get("/api/random-new-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRandomNewEntity() throws Exception {
        // Initialize the database
        randomNewEntityRepository.saveAndFlush(randomNewEntity);

        int databaseSizeBeforeUpdate = randomNewEntityRepository.findAll().size();

        // Update the randomNewEntity
        RandomNewEntity updatedRandomNewEntity = randomNewEntityRepository.findById(randomNewEntity.getId()).get();
        // Disconnect from session so that the updates on updatedRandomNewEntity are not directly saved in db
        em.detach(updatedRandomNewEntity);
        updatedRandomNewEntity
            .entityName(UPDATED_ENTITY_NAME)
            .someValue(UPDATED_SOME_VALUE);

        restRandomNewEntityMockMvc.perform(put("/api/random-new-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRandomNewEntity)))
            .andExpect(status().isOk());

        // Validate the RandomNewEntity in the database
        List<RandomNewEntity> randomNewEntityList = randomNewEntityRepository.findAll();
        assertThat(randomNewEntityList).hasSize(databaseSizeBeforeUpdate);
        RandomNewEntity testRandomNewEntity = randomNewEntityList.get(randomNewEntityList.size() - 1);
        assertThat(testRandomNewEntity.getEntityName()).isEqualTo(UPDATED_ENTITY_NAME);
        assertThat(testRandomNewEntity.getSomeValue()).isEqualTo(UPDATED_SOME_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingRandomNewEntity() throws Exception {
        int databaseSizeBeforeUpdate = randomNewEntityRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRandomNewEntityMockMvc.perform(put("/api/random-new-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(randomNewEntity)))
            .andExpect(status().isBadRequest());

        // Validate the RandomNewEntity in the database
        List<RandomNewEntity> randomNewEntityList = randomNewEntityRepository.findAll();
        assertThat(randomNewEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRandomNewEntity() throws Exception {
        // Initialize the database
        randomNewEntityRepository.saveAndFlush(randomNewEntity);

        int databaseSizeBeforeDelete = randomNewEntityRepository.findAll().size();

        // Delete the randomNewEntity
        restRandomNewEntityMockMvc.perform(delete("/api/random-new-entities/{id}", randomNewEntity.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RandomNewEntity> randomNewEntityList = randomNewEntityRepository.findAll();
        assertThat(randomNewEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
