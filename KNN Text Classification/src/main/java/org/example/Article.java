package org.example;

import org.apache.maven.surefire.shade.booter.org.apache.commons.lang3.builder.ToStringBuilder;

public class Article {
    private String title;
    private String country;
    private String body;

    public Article(String country, String title, String body) {
        this.title = title;
        this.country = country;
        this.body = body;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("country", country)
                .append("body", body)
                .toString();
    }

}
