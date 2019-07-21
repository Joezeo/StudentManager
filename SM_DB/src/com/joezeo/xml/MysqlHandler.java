package com.joezeo.xml;

import com.joezeo.bean.Configuration;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 处理xml 将配置信息存入Configuration对象中
 */
public class MysqlHandler extends DefaultHandler {
    /**
     * 数据库配置信息对象
     */
    private Configuration conf = null;

    private String tag = null;

    public Configuration getConfigure() {
        return conf;
    }

    @Override
    public void startDocument() throws SAXException {
        conf = new Configuration();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tag = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);

        if (tag != null) {
            if (tag.equals("driver")) {
                conf.setDriver(content);
            } else if (tag.equals("url")) {
                conf.setUrl(content);
            } else if (tag.equals("user")) {
                conf.setUser(content);
            } else if (tag.equals("pwd")) {
                conf.setPwd(content);
            } else if (tag.equals("usingDB")) {
                conf.setUsingDB(content);
            } else if (tag.equals("queryClass")) {
                conf.setQueryClass(content);
            } else if (tag.equals("poPackage")){
                conf.setPoPackage(content);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tag = null;
    }
}
