package com.mysql.generator;

import java.io.UnsupportedEncodingException;

import com.api.common.utils.HttpUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 用途：TODO
 * 
 * @author bbdtek
 */
public class ParserAirport {
    private static Logger log = LoggerFactory.getLogger(ParserAirport.class);
    /**
     * @param args
     * @throws UnsupportedEncodingException
     * @throws XPatherException
     */
    public static void main(String[] args) throws Exception,
            XPatherException {
        String url = "http://zh.wikipedia.org/wiki/%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E6%9C%BA%E5%9C%BA%E5%88%97%E8%A1%A8";
        String contents = HttpUtils.get(url);
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(contents);
        String xpath = "//div[@class='mw-content-ltr']//table[@class='wikitable + sortable']//tbody//tr[@align='right']";
        Object[] objarr = null;
        objarr = tn.evaluateXPath(xpath);
        if (objarr != null && objarr.length > 0) {
            for (Object obj : objarr) {
                TagNode tntr = (TagNode) obj;
                String xptr = "//td[@align='left']//a";
                Object[] objarrtr = null;
                objarrtr = tntr.evaluateXPath(xptr);
                if (objarrtr != null && objarrtr.length > 0) {
                    for (Object obja : objarrtr) {
                        TagNode tna = (TagNode) obja;
                        String str = tna.getText().toString();
                        log.info(str);
                    }
                }
            }
        }
    }
}