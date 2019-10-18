package glescroel.escalade.controller;

import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Controller
public class SiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    SiteService siteService;

    @GetMapping(value = "/site", params = {"id"})
    public String viewSitePage(Model model, @NotNull(message = "id must be not null") @RequestParam("id") String id) {
        LOGGER.debug(">>>>> Dans SiteController");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));

        model.addAttribute("site", site);

        return "site";
    }
}
