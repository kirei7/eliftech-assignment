package vlad.companies.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CompanyEntity implements Company<CompanyEntity> {

    @Id
    private String name;

    @Column(nullable = false)
    private BigDecimal estimatedEarnings;

    @OneToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private Set<CompanyEntity> childCompanies = new HashSet<>();

    private String parentName;

    /*Business methods*/
    @Override
    public BigDecimal getTotalEarnings() {
        BigDecimal totalEarnings = estimatedEarnings;
        if (childCompanies != null)
            for (CompanyEntity child : childCompanies)
                totalEarnings = totalEarnings.add(child.getTotalEarnings());
        return totalEarnings;
    }
    @Override
    public boolean addChild(Company child) {
        return childCompanies.add((CompanyEntity) child);
    }
    /*Business methods end*/

    /*Entity-specific methods*/
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getEstimatedEarnings() {
        return estimatedEarnings;
    }

    @Override
    public void setEstimatedEarnings(BigDecimal estimatedEarnings) {
        this.estimatedEarnings = estimatedEarnings;
    }

    @Override
    public Set<CompanyEntity> getChildCompanies() {
        return childCompanies;
    }

    @Override
    public void setChildCompanies(Set<CompanyEntity> childCompanies) {
        this.childCompanies = childCompanies;
    }

    @Override
    public String getParentName() {
        return parentName;
    }

    @Override
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CompanyEntity))
            return false;

        CompanyEntity that = (CompanyEntity) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "name='" + name + '\'' +
                ", estimatedEarnings=" + estimatedEarnings +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
