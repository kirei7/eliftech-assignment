package vlad.companies.controller;

import org.springframework.web.bind.annotation.*;
import vlad.companies.Company;
import vlad.companies.exception.CompanyNotFoundException;

import java.util.Set;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyServiceFacade facade;

    @RequestMapping(method = RequestMethod.GET)
    public Set<Company> getMainCompanies() {
        return facade.getMainCompanies();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company addMainCompany(@RequestParam Company company) {
        return facade.save(company, null);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteCompany(@RequestParam Company company) {
        facade.delete(company);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Set<Company> getChildCompanies(@PathVariable String name) {
        return facade.findByName(name).getChildCompanies();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public Company addChildCompany(@PathVariable String name, @RequestParam Company company) {
        Company parentCompany = facade.findByName(name);
        if (parentCompany == null) throw new CompanyNotFoundException(name);
        return facade.save(company, parentCompany);
    }
}
