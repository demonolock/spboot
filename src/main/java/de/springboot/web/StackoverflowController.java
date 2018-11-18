package de.springboot.web;

import de.springboot.model.StackoverflowWebsite;
import de.springboot.service.StackoverflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stackoverflow")
public class StackoverflowController {

    @Autowired
    private StackoverflowService stackoverflowService; //dependency injection

    @RequestMapping
   // @ResponseBody //default - JSON not need if use @RestController
    public List<StackoverflowWebsite> getListOfProviders() {
        return stackoverflowService.findAll();
    }

}
