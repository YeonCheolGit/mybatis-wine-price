package main.controller;

import lombok.extern.log4j.Log4j2;
import main.paging.PageMaker;
import main.paging.SearchCriteria;
import main.service.interceptor.Auth;
import main.service.wine.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Log4j2 // log field 생성 lombok 애노테이션
@RequestMapping(value = "/wine")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    /**
     * @Auth "ROLE_USER" 권한 접속 허용
     * 전체 와인 리스트
     */
    @Auth(role = Auth.Role.ROLE_USER)
    @GetMapping(value = "/searchBarAndPagination")
    public String searchBarAndPagination(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                                         Model model) {
        log.debug("==================== searchBar ====================");

        wineService.wineSearchCount(searchCriteria.getKeyword()); // 검색 마다 조회수 카운트 +1
        wineService.realtimeWineSearchCount(); // 실시간 상위 검색 와인 목록 3개 가지고 옴

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(searchCriteria);
        pageMaker.setTotalCount(wineService.countWines(searchCriteria));

        model.addAttribute("allWineList", wineService.listPaging(searchCriteria));
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("realtimeWineSearchCount", wineService.realtimeWineSearchCount());

        return "wine/allWineList";
    }

    /*
     * 정렬 버튼 클릭 시 동작
     * 정렬 후 페이지를 넘겨도 유지됨
     */
    @GetMapping(value = "/orderByPrice")
    public String orderByPrice(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                            Model model) {
        log.debug("==================== prices ====================");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(searchCriteria);
        pageMaker.setTotalCount(wineService.countWines(searchCriteria));

        model.addAttribute("allWineList", wineService.orderByPrice(searchCriteria));
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("orderByPrice", true);

        return "wine/allWineList";
    }

    /*
     * 검색창 자동완성(autocomplete) 동작
     */
    @GetMapping(value = "/autocomplete")
    @ResponseBody
    public List<String> autocomplete(HttpServletRequest request) {
        log.debug("==================== search ====================");
        return wineService.search(request.getParameter("term"));
    }
}
