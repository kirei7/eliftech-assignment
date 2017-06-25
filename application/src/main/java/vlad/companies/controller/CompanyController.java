package vlad.companies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vlad.companies.entity.Company;
import vlad.companies.exception.RawCompanyNotFoundException;
import vlad.companies.exception.CompanyNotFoundException;
import vlad.companies.service.CompanyService;

import java.util.Set;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Company> getMainCompanies() {
        return companyService.getMainCompanies();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company addMainCompany(@RequestParam Company company) {
        try {
            return companyService.save(company, null);
        } catch (RawCompanyNotFoundException e) {
            throw new CompanyNotFoundException(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteCompany(@RequestParam Company company) {
        companyService.delete(company);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Set<Company> getChildCompanies(@PathVariable String name) {
        return companyService.findByName(name).getChildCompanies();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public Company addChildCompany(@PathVariable String name, @RequestParam Company company) {
        try {
            return companyService.save(company, name);
        } catch (RawCompanyNotFoundException e) {
            throw new CompanyNotFoundException(e.getMessage());
        }
    }
}
