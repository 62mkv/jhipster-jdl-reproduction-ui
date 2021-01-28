package ee.mkv.jhipster.web.rest;

import ee.mkv.jhipster.ReproductionUiApp;
import ee.mkv.jhipster.domain.BusinessUnitConfig;
import ee.mkv.jhipster.repository.BusinessUnitConfigRepository;

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
 * Integration tests for the {@link BusinessUnitConfigResource} REST controller.
 */
@SpringBootTest(classes = ReproductionUiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BusinessUnitConfigResourceIT {

    private static final String DEFAULT_UNIT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_LABEL_QUEUE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_LABEL_QUEUE = "BBBBBBBBBB";

    @Autowired
    private BusinessUnitConfigRepository businessUnitConfigRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBusinessUnitConfigMockMvc;

    private BusinessUnitConfig businessUnitConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusinessUnitConfig createEntity(EntityManager em) {
        BusinessUnitConfig businessUnitConfig = new BusinessUnitConfig()
            .unitName(DEFAULT_UNIT_NAME)
            .defaultLabelQueue(DEFAULT_DEFAULT_LABEL_QUEUE);
        return businessUnitConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusinessUnitConfig createUpdatedEntity(EntityManager em) {
        BusinessUnitConfig businessUnitConfig = new BusinessUnitConfig()
            .unitName(UPDATED_UNIT_NAME)
            .defaultLabelQueue(UPDATED_DEFAULT_LABEL_QUEUE);
        return businessUnitConfig;
    }

    @BeforeEach
    public void initTest() {
        businessUnitConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusinessUnitConfig() throws Exception {
        int databaseSizeBeforeCreate = businessUnitConfigRepository.findAll().size();
        // Create the BusinessUnitConfig
        restBusinessUnitConfigMockMvc.perform(post("/api/business-unit-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitConfig)))
            .andExpect(status().isCreated());

        // Validate the BusinessUnitConfig in the database
        List<BusinessUnitConfig> businessUnitConfigList = businessUnitConfigRepository.findAll();
        assertThat(businessUnitConfigList).hasSize(databaseSizeBeforeCreate + 1);
        BusinessUnitConfig testBusinessUnitConfig = businessUnitConfigList.get(businessUnitConfigList.size() - 1);
        assertThat(testBusinessUnitConfig.getUnitName()).isEqualTo(DEFAULT_UNIT_NAME);
        assertThat(testBusinessUnitConfig.getDefaultLabelQueue()).isEqualTo(DEFAULT_DEFAULT_LABEL_QUEUE);
    }

    @Test
    @Transactional
    public void createBusinessUnitConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessUnitConfigRepository.findAll().size();

        // Create the BusinessUnitConfig with an existing ID
        businessUnitConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessUnitConfigMockMvc.perform(post("/api/business-unit-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitConfig)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessUnitConfig in the database
        List<BusinessUnitConfig> businessUnitConfigList = businessUnitConfigRepository.findAll();
        assertThat(businessUnitConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUnitNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = businessUnitConfigRepository.findAll().size();
        // set the field null
        businessUnitConfig.setUnitName(null);

        // Create the BusinessUnitConfig, which fails.


        restBusinessUnitConfigMockMvc.perform(post("/api/business-unit-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitConfig)))
            .andExpect(status().isBadRequest());

        List<BusinessUnitConfig> businessUnitConfigList = businessUnitConfigRepository.findAll();
        assertThat(businessUnitConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefaultLabelQueueIsRequired() throws Exception {
        int databaseSizeBeforeTest = businessUnitConfigRepository.findAll().size();
        // set the field null
        businessUnitConfig.setDefaultLabelQueue(null);

        // Create the BusinessUnitConfig, which fails.


        restBusinessUnitConfigMockMvc.perform(post("/api/business-unit-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitConfig)))
            .andExpect(status().isBadRequest());

        List<BusinessUnitConfig> businessUnitConfigList = businessUnitConfigRepository.findAll();
        assertThat(businessUnitConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBusinessUnitConfigs() throws Exception {
        // Initialize the database
        businessUnitConfigRepository.saveAndFlush(businessUnitConfig);

        // Get all the businessUnitConfigList
        restBusinessUnitConfigMockMvc.perform(get("/api/business-unit-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessUnitConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitName").value(hasItem(DEFAULT_UNIT_NAME)))
            .andExpect(jsonPath("$.[*].defaultLabelQueue").value(hasItem(DEFAULT_DEFAULT_LABEL_QUEUE)));
    }
    
    @Test
    @Transactional
    public void getBusinessUnitConfig() throws Exception {
        // Initialize the database
        businessUnitConfigRepository.saveAndFlush(businessUnitConfig);

        // Get the businessUnitConfig
        restBusinessUnitConfigMockMvc.perform(get("/api/business-unit-configs/{id}", businessUnitConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(businessUnitConfig.getId().intValue()))
            .andExpect(jsonPath("$.unitName").value(DEFAULT_UNIT_NAME))
            .andExpect(jsonPath("$.defaultLabelQueue").value(DEFAULT_DEFAULT_LABEL_QUEUE));
    }
    @Test
    @Transactional
    public void getNonExistingBusinessUnitConfig() throws Exception {
        // Get the businessUnitConfig
        restBusinessUnitConfigMockMvc.perform(get("/api/business-unit-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessUnitConfig() throws Exception {
        // Initialize the database
        businessUnitConfigRepository.saveAndFlush(businessUnitConfig);

        int databaseSizeBeforeUpdate = businessUnitConfigRepository.findAll().size();

        // Update the businessUnitConfig
        BusinessUnitConfig updatedBusinessUnitConfig = businessUnitConfigRepository.findById(businessUnitConfig.getId()).get();
        // Disconnect from session so that the updates on updatedBusinessUnitConfig are not directly saved in db
        em.detach(updatedBusinessUnitConfig);
        updatedBusinessUnitConfig
            .unitName(UPDATED_UNIT_NAME)
            .defaultLabelQueue(UPDATED_DEFAULT_LABEL_QUEUE);

        restBusinessUnitConfigMockMvc.perform(put("/api/business-unit-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBusinessUnitConfig)))
            .andExpect(status().isOk());

        // Validate the BusinessUnitConfig in the database
        List<BusinessUnitConfig> businessUnitConfigList = businessUnitConfigRepository.findAll();
        assertThat(businessUnitConfigList).hasSize(databaseSizeBeforeUpdate);
        BusinessUnitConfig testBusinessUnitConfig = businessUnitConfigList.get(businessUnitConfigList.size() - 1);
        assertThat(testBusinessUnitConfig.getUnitName()).isEqualTo(UPDATED_UNIT_NAME);
        assertThat(testBusinessUnitConfig.getDefaultLabelQueue()).isEqualTo(UPDATED_DEFAULT_LABEL_QUEUE);
    }

    @Test
    @Transactional
    public void updateNonExistingBusinessUnitConfig() throws Exception {
        int databaseSizeBeforeUpdate = businessUnitConfigRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusinessUnitConfigMockMvc.perform(put("/api/business-unit-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitConfig)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessUnitConfig in the database
        List<BusinessUnitConfig> businessUnitConfigList = businessUnitConfigRepository.findAll();
        assertThat(businessUnitConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusinessUnitConfig() throws Exception {
        // Initialize the database
        businessUnitConfigRepository.saveAndFlush(businessUnitConfig);

        int databaseSizeBeforeDelete = businessUnitConfigRepository.findAll().size();

        // Delete the businessUnitConfig
        restBusinessUnitConfigMockMvc.perform(delete("/api/business-unit-configs/{id}", businessUnitConfig.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BusinessUnitConfig> businessUnitConfigList = businessUnitConfigRepository.findAll();
        assertThat(businessUnitConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
