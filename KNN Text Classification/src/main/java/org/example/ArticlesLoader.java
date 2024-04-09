package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticlesLoader {
    public ArticlesLoader() {
    }

    public List<Article> loadArticles() {
        ArrayList<Article> articles = new ArrayList<>();
        File file = new File("reuters21578");
        ReutersParser reutersParser = new ReutersParser(file);
        StopWordsFilter stopWordsFilter = new StopWordsFilter();
        for(ReutersArticle a : reutersParser) {
            String title = a.getTag("TITLE");
            String places = a.getTag("PLACES");
            Pattern pattern = Pattern.compile("<D>(west-germany|usa|france|uk|canada|japan)</D>");
            int count = places.split("<D>").length - 1;
            Matcher matcher = pattern.matcher(places);
            while (matcher.find() && count == 1) {
                String country = matcher.group(1);
                String body = a.getTag("BODY");
                body = stopWordsFilter.filter(body);
                articles.add(new Article(country, title, body));
                break;
            }
        }
        return articles;
    }
}
