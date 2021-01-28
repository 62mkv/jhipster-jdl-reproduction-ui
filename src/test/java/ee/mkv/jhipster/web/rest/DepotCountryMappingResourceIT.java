package ee.mkv.jhipster.web.rest;

import ee.mkv.jhipster.ReproductionUiApp;
import ee.mkv.jhipster.domain.DepotCountryMapping;
import ee.mkv.jhipster.repository.DepotCountryMappingRepository;

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
 * Integration tests for the {@link DepotCountryMappingResource} REST controller.
 */
@SpringBootTest(classes = ReproductionUiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DepotCountryMappingResourceIT {

    private static final String DEFAULT_DEPOT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPOT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AA";
    private static final String UPDATED_COUNTRY_CODE = "BB";

    @Autowired
    private DepotCountryMappingRepository depotCountryMappingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepotCountryMappingMockMvc;

    private DepotCountryMapping depotCountryMapping;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepotCountryMapping createEntity(EntityManager em) {
        DepotCountryMapping depotCountryMapping = new DepotCountryMapping()
            .depotName(DEFAULT_DEPOT_NAME)
            .countryCode(DEFAULT_COUNTRY_CODE);
        return depotCountryMapping;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepotCountryMapping createUpdatedEntity(EntityManager em) {
        DepotCountryMapping depotCountryMapping = new DepotCountryMapping()
            .depotName(UPDATED_DEPOT_NAME)
            .countryCode(UPDATED_COUNTRY_CODE);
        return depotCountryMapping;
    }

    @BeforeEach
    public void initTest() {
        depotCountryMapping = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepotCountryMapping() throws Exception {
        int databaseSizeBeforeCreate = depotCountryMappingRepository.findAll().size();
        // Create the DepotCountryMapping
        restDepotCountryMappingMockMvc.perform(post("/api/depot-country-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depotCountryMapping)))
            .andExpect(status().isCreated());

        // Validate the DepotCountryMapping in the database
        List<DepotCountryMapping> depotCountryMappingList = depotCountryMappingRepository.findAll();
        assertThat(depotCountryMappingList).hasSize(databaseSizeBeforeCreate + 1);
        DepotCountryMapping testDepotCountryMapping = depotCountryMappingList.get(depotCountryMappingList.size() - 1);
        assertThat(testDepotCountryMapping.getDepotName()).isEqualTo(DEFAULT_DEPOT_NAME);
        assertThat(testDepotCountryMapping.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void createDepotCountryMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = depotCountryMappingRepository.findAll().size();

        // Create the DepotCountryMapping with an existing ID
        depotCountryMapping.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepotCountryMappingMockMvc.perform(post("/api/depot-country-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depotCountryMapping)))
            .andExpect(status().isBadRequest());

        // Validate the DepotCountryMapping in the database
        List<DepotCountryMapping> depotCountryMappingList = depotCountryMappingRepository.findAll();
        assertThat(depotCountryMappingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDepotNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = depotCountryMappingRepository.findAll().size();
        // set the field null
        depotCountryMapping.setDepotName(null);

        // Create the DepotCountryMapping, which fails.


        restDepotCountryMappingMockMvc.perform(post("/api/depot-country-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depotCountryMapping)))
            .andExpect(status().isBadRequest());

        List<DepotCountryMapping> depotCountryMappingList = depotCountryMappingRepository.findAll();
        assertThat(depotCountryMappingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = depotCountryMappingRepository.findAll().size();
        // set the field null
        depotCountryMapping.setCountryCode(null);

        // Create the DepotCountryMapping, which fails.


        restDepotCountryMappingMockMvc.perform(post("/api/depot-country-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depotCountryMapping)))
            .andExpect(status().isBadRequest());

        List<DepotCountryMapping> depotCountryMappingList = depotCountryMappingRepository.findAll();
        assertThat(depotCountryMappingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDepotCountryMappings() throws Exception {
        // Initialize the database
        depotCountryMappingRepository.saveAndFlush(depotCountryMapping);

        // Get all the depotCountryMappingList
        restDepotCountryMappingMockMvc.perform(get("/api/depot-country-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depotCountryMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].depotName").value(hasItem(DEFAULT_DEPOT_NAME)))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)));
    }
    
    @Test
    @Transactional
    public void getDepotCountryMapping() throws Exception {
        // Initialize the database
        depotCountryMappingRepository.saveAndFlush(depotCountryMapping);

        // Get the depotCountryMapping
        restDepotCountryMappingMockMvc.perform(get("/api/depot-country-mappings/{id}", depotCountryMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(depotCountryMapping.getId().intValue()))
            .andExpect(jsonPath("$.depotName").value(DEFAULT_DEPOT_NAME))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingDepotCountryMapping() throws Exception {
        // Get the depotCountryMapping
        restDepotCountryMappingMockMvc.perform(get("/api/depot-country-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepotCountryMapping() throws Exception {
        // Initialize the database
        depotCountryMappingRepository.saveAndFlush(depotCountryMapping);

        int databaseSizeBeforeUpdate = depotCountryMappingRepository.findAll().size();

        // Update the depotCountryMapping
        DepotCountryMapping updatedDepotCountryMapping = depotCountryMappingRepository.findById(depotCountryMapping.getId()).get();
        // Disconnect from session so that the updates on updatedDepotCountryMapping are not directly saved in db
        em.detach(updatedDepotCountryMapping);
        updatedDepotCountryMapping
            .depotName(UPDATED_DEPOT_NAME)
            .countryCode(UPDATED_COUNTRY_CODE);

        restDepotCountryMappingMockMvc.perform(put("/api/depot-country-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDepotCountryMapping)))
            .andExpect(status().isOk());

        // Validate the DepotCountryMapping in the database
        List<DepotCountryMapping> depotCountryMappingList = depotCountryMappingRepository.findAll();
        assertThat(depotCountryMappingList).hasSize(databaseSizeBeforeUpdate);
        DepotCountryMapping testDepotCountryMapping = depotCountryMappingList.get(depotCountryMappingList.size() - 1);
        assertThat(testDepotCountryMapping.getDepotName()).isEqualTo(UPDATED_DEPOT_NAME);
        assertThat(testDepotCountryMapping.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingDepotCountryMapping() throws Exception {
        int databaseSizeBeforeUpdate = depotCountryMappingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepotCountryMappingMockMvc.perform(put("/api/depot-country-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depotCountryMapping)))
            .andExpect(status().isBadRequest());

        // Validate the DepotCountryMapping in the database
        List<DepotCountryMapping> depotCountryMappingList = depotCountryMappingRepository.findAll();
        assertThat(depotCountryMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepotCountryMapping() throws Exception {
        // Initialize the database
        depotCountryMappingRepository.saveAndFlush(depotCountryMapping);

        int databaseSizeBeforeDelete = depotCountryMappingRepository.findAll().size();

        // Delete the depotCountryMapping
        restDepotCountryMappingMockMvc.perform(delete("/api/depot-country-mappings/{id}", depotCountryMapping.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DepotCountryMapping> depotCountryMappingList = depotCountryMappingRepository.findAll();
        assertThat(depotCountryMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
