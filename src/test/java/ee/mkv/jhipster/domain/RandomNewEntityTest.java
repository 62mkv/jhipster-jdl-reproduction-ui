package ee.mkv.jhipster.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ee.mkv.jhipster.web.rest.TestUtil;

public class RandomNewEntityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RandomNewEntity.class);
        RandomNewEntity randomNewEntity1 = new RandomNewEntity();
        randomNewEntity1.setId(1L);
        RandomNewEntity randomNewEntity2 = new RandomNewEntity();
        randomNewEntity2.setId(randomNewEntity1.getId());
        assertThat(randomNewEntity1).isEqualTo(randomNewEntity2);
        randomNewEntity2.setId(2L);
        assertThat(randomNewEntity1).isNotEqualTo(randomNewEntity2);
        randomNewEntity1.setId(null);
        assertThat(randomNewEntity1).isNotEqualTo(randomNewEntity2);
    }
}
