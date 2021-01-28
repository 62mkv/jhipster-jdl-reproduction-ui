package ee.mkv.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BusinessUnitConfig.
 */
@Entity
@Table(name = "business_unit_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BusinessUnitConfig implements Serializable {

    // some arbitrary changes
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 10, max = 32)
    @Column(name = "unit_name", length = 32, nullable = false, unique = true)
    private String unitName;

    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "default_label_queue", length = 16, nullable = false)
    private String defaultLabelQueue;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public BusinessUnitConfig unitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDefaultLabelQueue() {
        return defaultLabelQueue;
    }

    public BusinessUnitConfig defaultLabelQueue(String defaultLabelQueue) {
        this.defaultLabelQueue = defaultLabelQueue;
        return this;
    }

    public void setDefaultLabelQueue(String defaultLabelQueue) {
        this.defaultLabelQueue = defaultLabelQueue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusinessUnitConfig)) {
            return false;
        }
        return id != null && id.equals(((BusinessUnitConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusinessUnitConfig{" +
            "id=" + getId() +
            ", unitName='" + getUnitName() + "'" +
            ", defaultLabelQueue='" + getDefaultLabelQueue() + "'" +
            "}";
    }
}
