package ee.mkv.jhipster.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ee.mkv.jhipster.web.rest.TestUtil;

public class DepotCountryMappingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepotCountryMapping.class);
        DepotCountryMapping depotCountryMapping1 = new DepotCountryMapping();
        depotCountryMapping1.setId(1L);
        DepotCountryMapping depotCountryMapping2 = new DepotCountryMapping();
        depotCountryMapping2.setId(depotCountryMapping1.getId());
        assertThat(depotCountryMapping1).isEqualTo(depotCountryMapping2);
        depotCountryMapping2.setId(2L);
        assertThat(depotCountryMapping1).isNotEqualTo(depotCountryMapping2);
        depotCountryMapping1.setId(null);
        assertThat(depotCountryMapping1).isNotEqualTo(depotCountryMapping2);
    }
}
