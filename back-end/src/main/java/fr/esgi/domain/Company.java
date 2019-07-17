package fr.esgi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A company.
 */
@Entity
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String socialReason;

    private String email;

    public Company() {
    	// Empty constructor needed for Hibernate.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) &&
                Objects.equals(name, company.name) &&
                Objects.equals(address, company.address) &&
                Objects.equals(socialReason, company.socialReason) &&
                Objects.equals(email, company.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, socialReason, email);
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (address != null) {
			builder.append("address=");
			builder.append(address);
			builder.append(", ");
		}
		if (socialReason != null) {
			builder.append("socialReason=");
			builder.append(socialReason);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
		}
		builder.append("]");
		return builder.toString();
	}
}
