package org.example;

public class ReutersArticle {
    private final String rawString;

    public ReutersArticle(String rawString) {
        this.rawString = rawString;
    }

    /**
     * Retrieve tag value.
     * @param tag The xml-ish tag to locate.
     * @return the text delimited by <tag and </tag>. Including any properties that may be in the opening tag.
     */
    public String getTag(String tag){
        String startTag = "<" + tag; //leave out the closing ">".
        String endTag = "</" + tag + ">";
        int startPos = rawString.indexOf(startTag);
        int endPos = rawString.indexOf(endTag);
        if ((startPos == -1) || (endPos == -1)){
            return "";
        } else {
            startPos += startTag.length()+1;
            return rawString.substring(startPos, endPos);
        }
    }
}