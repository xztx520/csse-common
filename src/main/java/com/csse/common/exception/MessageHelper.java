package com.csse.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


/**
 * 消息资源文件辅助工具类
 * <p/>
 * 读取classpath下面的所有message_*.xml文件中的错误消息。
 *
 *
 * @author shihaizhou
 */
public class MessageHelper {
    private static final Logger log = LogManager.getLogger(MessageHelper.class);
    private static MessageHelper instance = new MessageHelper();

    private Properties sysProps = null;

    private MessageHelper() {
        this.init();
    }

    public static MessageHelper getInstance() {
        return instance;
    }

    /**
     * 初始化，将资源文件中的消息加载到内存中
     */
    private void init() {
        sysProps = new Properties();

        //加载classes目录下的message*.xml模式的文件
        File messagePath = new File(getClass().getResource("/").getPath() + "/");
        System.out.println(getClass().getResource("/").getPath());
        System.out.println(messagePath.length());

        String[] filenamelist = messagePath.list(new FilenameFilter() {
            Pattern p = compile("message.*.xml");

            @Override
            public boolean accept(File path, String name) {
                Matcher m = p.matcher(name);
                return m.matches();
            }
        });

        if (filenamelist != null) {
            for (String filename : filenamelist) {
                System.out.println(filename);
                File propsFile = new File(messagePath.getPath() + "/" + filename);
                // 加载错误消息到内存中
                try {
                    loadMessage(new FileInputStream(propsFile));
                } catch (FileNotFoundException e) {
                    throw new ProgramException(e);
                } catch (Exception e) {
                    throw new ProgramException(MessageFormat.format("消息文件{0}格式不正确。", propsFile.getName()), e);
                }
            }
        }

        //加载 commons-util.jar包中的 message_common.xml文件
        ClassPathResource rs = new ClassPathResource("message_common.xml");
        try {
            loadMessage(rs.getInputStream());
        } catch (IOException e) {
            throw new ProgramException(e);
        } catch (Exception e) {
            throw new ProgramException(MessageFormat.format("消息文件{0}格式不正确。", "message_framework.xml"), e);
        }
    }

    /**
     * 从资源文件中加载错误消息到内存中
     */
    private void loadMessage(InputStream is) throws Exception {
        try {
            MessageParserHandler xmlHandler = new MessageParserHandler();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(is, xmlHandler);

            sysProps.putAll(xmlHandler.getProps());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // 忽略
                }
            }
        }
    }

    /**
     * 错误消息xml文件parser
     *
     * @author liufengyu
     */
    static class MessageParserHandler extends DefaultHandler {
        private Properties messages = null;
        private StringBuffer curValue = new StringBuffer();
        private String curKey = null;

        public Properties getProps() {
            return messages;
        }

        @Override
        public void startDocument() {
            messages = new Properties();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            curValue.setLength(0);
            if (attributes.getLength() > 0) {
                curKey = attributes.getValue(0);
            } else {
                curKey = null;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            curValue.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            String txtVal = curValue.toString().trim();
            if (qName.equals("message")) {
                messages.put(curKey, txtVal);
            }
        }
    }

    /**
     * 根据errorCode取得一个错误消息，如果没有对应的错误消息，则返回errorCode本身。
     *
     * @param errorCode
     * @return 如果有错误消息，则返回错误消息，否则，返回errorCode
     */
    public final String getMessage(String errorCode) {
        // 检测资源文件是否更新了
        String val = null;
        val = sysProps.getProperty(errorCode);

        if (val == null) {
            log.warn(MessageFormat.format("错误码{0}没有对应的错误消息。", errorCode));
            return errorCode;
        } else {
            return val;
        }
    }

    /**
     * 取得格式化后的消息。
     *
     * @param errorCode
     * @param args
     * @return
     */
    public final String getFormatedMessage(String errorCode, Object... args) {
        return "【" + errorCode + "】" + MessageFormat.format(this.getMessage(errorCode), args);
    }

}
