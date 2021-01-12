package main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    @GetMapping
    public String goMain() {
        logger.debug("main debug >>> ");
        return "main";
    }
}
