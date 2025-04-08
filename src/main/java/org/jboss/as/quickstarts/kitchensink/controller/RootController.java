package org.jboss.as.quickstarts.kitchensink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String home() {
        return "index"; // Maps to src/main/resources/templates/index.html
    }
}
