package vlad.companies.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/*generic type is needed to operate types inside derivatives
note that this design is to allow each derivative to be a standalone
persistent entity, such as JPA entity or no-sql document*/
public interface Company<T extends Company> extends Serializable{
    /*Business methods*/
    BigDecimal getTotalEarnings();

    boolean addChild(Company child);

    /*Business methods end*/
    /*Entity-specific methods*/
    String getName();

    void setName(String name);

    BigDecimal getEstimatedEarnings();

    void setEstimatedEarnings(BigDecimal estimatedEarnings);

    Set<T> getChildCompanies();

    void setChildCompanies(Set<T> childCompanies);

    String getParentName();

    void setParentName(String parent);
}
