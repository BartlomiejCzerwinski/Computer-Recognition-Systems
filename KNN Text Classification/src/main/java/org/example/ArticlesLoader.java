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
        for(ReutersArticle a : reutersParser) {
            String title = a.getTag("TITLE");
            String places = a.getTag("PLACES");
            Pattern pattern = Pattern.compile("<D>(west-germany|usa|france|uk|canada|japan)</D>");
            Matcher matcher = pattern.matcher(places);
            while (matcher.find()) {
                String country = matcher.group(1);
                String body = a.getTag("BODY");
                articles.add(new Article(country, title, body));
                break;
            }
        }
        System.out.println(articles.get(0).getTitle().toString());
        return articles;
    }
}
