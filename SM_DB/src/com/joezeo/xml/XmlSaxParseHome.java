package com.joezeo.xml;

import com.joezeo.bean.Configuration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * xml解析控制中心
 */
public class XmlSaxParseHome {
    /**
     * 数据库配置信息对象 从XmlSaxParseHandler中获取
     */
    private static Configuration conf = null;

    //私有化构造器
    private XmlSaxParseHome(){

    }

    static {
        try {
            //获取解析器工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();

            //获取解析器
            SAXParser parser = factory.newSAXParser();

            //创建handler类
            MysqlHandler handler = new MysqlHandler();

            //从xml文件获取输入流
            //在xml文件里 不能使用&符号 要用 &amp; 来代替
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/joezeo/xml/db_info.xml");

            //解析
            parser.parse(is, handler);

            //获取Configuration
            conf = handler.getConfigure();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //对DBManager类提供获取Configuration方法
    public static Configuration getConfiguration(){
        return conf;
    }
}
