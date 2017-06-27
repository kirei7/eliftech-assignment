package vlad.companies.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vlad.companies.entity.Company;
import vlad.companies.entity.CompanyEntity;
import vlad.companies.exception.RawCompanyNotFoundException;
import vlad.companies.exception.CompanyNotFoundException;
import vlad.companies.service.CompanyService;

import java.util.Set;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Company> getMainCompanies() {
        Set<Company> companies = companyService.getMainCompanies();
        logger.debug(companies.toString());
        return companies;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company addCompany(@ModelAttribute CompanyEntity company) {
        logger.debug("SAVING: " + company.toString());
        return saveCompany(company);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Company editCompany(@ModelAttribute CompanyEntity company) {
        logger.debug("EDITING: " + company.toString());
        company.setChildCompanies(companyService.findByName(company.getName()).getChildCompanies());
        return saveCompany(company);
    }
    private Company saveCompany(Company company) {
        try {
            return companyService.save(company, company.getParentName());
        } catch (RawCompanyNotFoundException e) {
            throw new CompanyNotFoundException(e.getMessage());
        }
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public void deleteCompany(@RequestParam("name") String name) {
        companyService.delete(companyService.findByName(name));
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Set<Company> getChildCompanies(@PathVariable String name) {
        return companyService.findByName(name).getChildCompanies();
    }
}
