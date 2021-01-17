package main.paging;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchCriteria extends Criteria {

    private String searchType;
    private String keyword;

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "searchType='" + searchType + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
