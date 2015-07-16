package com.unitn.webcrawler;

import scala.Serializable;

/**
 * Page stores info of crawled web pages
 */
public class Page implements Serializable{

    private final String url;

    private final String html;

    public Page(Page page) {
        this(page.url, page.html);
    }

    public Page(String url, String html) {
        this.url = url;
        this.html = html;
    }

    public static Page mergePages(Page page1, Page page2) {
        if (page1 != null && !page1.isEmpty()) {
            return new Page(page1);
        }
        if (page2 != null && !page2.isEmpty()) {
            return new Page(page2);
        }
        throw new IllegalArgumentException("Both pages are empty!");
    }

    private boolean isEmpty() {
        return url.isEmpty() || html.isEmpty();
    }

    public String getUrl() {
        return url;
    }

    public String getHtml() {
        return html;
    }
}
