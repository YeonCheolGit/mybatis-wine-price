package main.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
@RequestMapping(value = "/main")
public class MainController {
    /*
     * main 초기 화면
     */
    @GetMapping("")
    public String goMain() {
        log.info("==================== main debug ==================== ");
        return "main";
    }

    /*
     * 예기치 못 한 에러가 발생 했을 때
     */
    @GetMapping(value = "/errorPage")
    public String errorPage() {
        log.info("==================== main debug ==================== ");
        return "commons/errorPage";
    }

    /*
     * React 프로젝트 채팅방
     */
    @GetMapping("kafkaPort")
    public ModelAndView kafkaPort() {
        log.info("==================== kafkaPort ==================== ");
        return new ModelAndView("redirect:" +  "http://localhost:3000");
    }
}
