package vlad.companies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vlad.companies.service.CompanyService;

@Controller
@RequestMapping("/home")
public class WebViewController {

    private CompanyService companyService;

    @Autowired
    public WebViewController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping
    public String homePage(Model model) {
        model.addAttribute(
                "company",
                companyService.newCompany()
                );
        return "index";
    }
}
