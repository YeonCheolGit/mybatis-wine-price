package main.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PageMaker {

    private int totalCount; //전체 게시글 갯수
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 3; //하단의 페이지 번호의 갯수
    private Criteria criteria;

    public void setCriteria(Criteria cri) {
        this.criteria = cri;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public Criteria getCri() {
        return criteria;
    }

    private void calcData() {
        /*
        // endPage - 한 목록에서 현재 보이는 끝 번호
        // if getPage = 3, displayPageNum = 3 then return 3
        // ceil은 크거나 같은 숫자 중 작은 숫자를 Integer로 반환
         */
        endPage = (int) (Math.ceil(criteria.getPage() / (double)displayPageNum) * displayPageNum);

        /*
        // 시작 목록 번호
        // if endPage = 3, displayPageNum = 3 then return 1
         */
        startPage = (endPage - displayPageNum) + 1;

        /*
        // if totalCount = 11, getPerPageNum = 10 then return 1
         */
        int tempEndPage = (int) (Math.ceil(totalCount / (double)criteria.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        //이전과 다음 버튼
        prev = startPage != 1;
        next = endPage * criteria.getPerPageNum() < totalCount; //총 게시물이 더 많다는 것은, 뒤에 더 보여줄게 있다는 의미
    }

    public String makeQuery(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .build();

        return uriComponents.toUriString();
    }

    public String makeSearch(int page) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
                .queryParam("orderByPrice", ((SearchCriteria) criteria).getSearchType())
                .queryParam("keyword", encoding(((SearchCriteria)criteria).getKeyword()))
                .build();

        return uriComponents.toUriString();
    }

    private String encoding(String keyword) {
        if (keyword == null || keyword.trim().length() == 0) {
            return "";
        }

        return URLEncoder.encode(keyword, StandardCharsets.UTF_8);
    }
}
