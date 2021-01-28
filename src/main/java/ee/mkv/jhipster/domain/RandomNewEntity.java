package ee.mkv.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A RandomNewEntity.
 */
@Entity
@Table(name = "random_new_entity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RandomNewEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 10, max = 32)
    @Column(name = "entity_name", length = 32, nullable = false, unique = true)
    private String entityName;

    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "some_value", length = 16, nullable = false)
    private String someValue;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public RandomNewEntity entityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getSomeValue() {
        return someValue;
    }

    public RandomNewEntity someValue(String someValue) {
        this.someValue = someValue;
        return this;
    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RandomNewEntity)) {
            return false;
        }
        return id != null && id.equals(((RandomNewEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RandomNewEntity{" +
            "id=" + getId() +
            ", entityName='" + getEntityName() + "'" +
            ", someValue='" + getSomeValue() + "'" +
            "}";
    }
}
