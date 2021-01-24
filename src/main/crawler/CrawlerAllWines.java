package main.crawler;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CrawlerAllWines {
    public static void main(String[] args) throws IOException {
        crawler();
    }

   
    public static void crawler() throws IOException {
        SqlSession sqlSession = null;
        int number = 0;

        while (number <= 7) {
            ++number;
            Document doc1 = Jsoup
                    .connect("http://www.ssg.com/search.ssg?target=all&query=와인&ctgId=6000099422&ctgLv=3&ctgLast=Y&parentCtgId=6000099420&count=100&page=" + number).get();
            Elements title1 = doc1.select("a.clickable > em.tx_ko");

            int count = 0;

            for (Element element : title1) {
                count++;
                String win = element.text();
                sqlSession.insert("mapper.wine.addWines" + win);
                System.out.println(count + " >> " + win + " >> " + count);
            }
            System.out.println(number + "페이지 끝");
            System.out.println("======================= \n");
        }
    }



}
