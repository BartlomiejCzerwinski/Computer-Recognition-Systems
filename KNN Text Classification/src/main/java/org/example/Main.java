package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
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
        System.out.println(articles.toString());
    }
}