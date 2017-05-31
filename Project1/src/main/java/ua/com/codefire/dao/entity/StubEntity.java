package ua.com.codefire.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gleb on 31.05.17.
 */
@Entity
@Table(name = "stub")
public class StubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stub_value")
    private String stubValue;

    @Column(name = "first_value")
    private Integer firstValue;

    @Column(name = "second_value")
    private Integer secondValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStubValue() {
        return stubValue;
    }

    public void setStubValue(String stubValue) {
        this.stubValue = stubValue;
    }

    public Integer getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Integer firstValue) {
        this.firstValue = firstValue;
    }

    public Integer getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Integer secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StubEntity that = (StubEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (stubValue != null ? !stubValue.equals(that.stubValue) : that.stubValue != null) return false;
        if (firstValue != null ? !firstValue.equals(that.firstValue) : that.firstValue != null) return false;
        return secondValue != null ? secondValue.equals(that.secondValue) : that.secondValue == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (stubValue != null ? stubValue.hashCode() : 0);
        result = 31 * result + (firstValue != null ? firstValue.hashCode() : 0);
        result = 31 * result + (secondValue != null ? secondValue.hashCode() : 0);
        return result;
    }
}
