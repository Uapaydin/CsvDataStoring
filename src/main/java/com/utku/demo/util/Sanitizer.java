package com.utku.demo.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 4:06 PM
 */
public class Sanitizer {
    public static String cleanIt(String text) {
        return Jsoup.clean(text, Safelist.basic());
    }
}
