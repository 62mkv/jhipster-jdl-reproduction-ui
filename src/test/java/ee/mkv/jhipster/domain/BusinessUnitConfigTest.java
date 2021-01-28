package ee.mkv.jhipster.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ee.mkv.jhipster.web.rest.TestUtil;

public class BusinessUnitConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessUnitConfig.class);
        BusinessUnitConfig businessUnitConfig1 = new BusinessUnitConfig();
        businessUnitConfig1.setId(1L);
        BusinessUnitConfig businessUnitConfig2 = new BusinessUnitConfig();
        businessUnitConfig2.setId(businessUnitConfig1.getId());
        assertThat(businessUnitConfig1).isEqualTo(businessUnitConfig2);
        businessUnitConfig2.setId(2L);
        assertThat(businessUnitConfig1).isNotEqualTo(businessUnitConfig2);
        businessUnitConfig1.setId(null);
        assertThat(businessUnitConfig1).isNotEqualTo(businessUnitConfig2);
    }
}
