/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitn.webcrawler;

import java.util.*;

/**
 *
 */
public class CrawledDataSource {

    public static Map<String,String> crawledData =new HashMap<>();

    public CrawledDataSource() {
    }

    /**
     * @return the crawledData
     */
    public static Map<String,String> getCrawledData() {
        return crawledData;
    }
}
