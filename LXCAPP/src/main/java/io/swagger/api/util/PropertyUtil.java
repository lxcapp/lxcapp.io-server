package io.swagger.api.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ZHANGJIANXIN on 2017/2.
 */
public class PropertyUtil {
    private static Properties properties;
    private static Map<String, String> env = new HashMap<String, String>();
    //读取配置文件
    private static Properties properties() {
        if (properties == null) {
            try {
                env = System.getenv();
                properties = new Properties();
                if (env.containsKey("LXC_HOME")) {
                    System.out.println(env.get("LXC_HOME"));
                    properties.load(new FileInputStream(env.get("LXC_HOME")
                            + "/Config.properties"));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }
    //获取配置文件属性
    public static String getProperty(String name) {
        return properties().getProperty(name);
    }
}
