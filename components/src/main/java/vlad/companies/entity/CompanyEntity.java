package vlad.companies.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class CompanyEntity implements Company<CompanyEntity> {

    @Id
    private String name;

    @Column(nullable = false)
    private BigDecimal estimatedEarnings;

    @OneToMany
    private Set<CompanyEntity> childCompanies;

    @ManyToOne
    private CompanyEntity parent;

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
    public Company getParent() {
        return parent;
    }

    @Override
    public void setParent(CompanyEntity parent) {
        this.parent = parent;
    }
}
