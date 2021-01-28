package ee.mkv.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DepotCountryMapping.
 */
@Entity
@Table(name = "depot_country_mapping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepotCountryMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "depot_name", length = 10, nullable = false, unique = true)
    private String depotName;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "country_code", length = 2, nullable = false)
    private String countryCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepotName() {
        return depotName;
    }

    public DepotCountryMapping depotName(String depotName) {
        this.depotName = depotName;
        return this;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public DepotCountryMapping countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepotCountryMapping)) {
            return false;
        }
        return id != null && id.equals(((DepotCountryMapping) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepotCountryMapping{" +
            "id=" + getId() +
            ", depotName='" + getDepotName() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            "}";
    }
}
