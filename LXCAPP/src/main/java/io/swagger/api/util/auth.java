package io.swagger.api.util;


/**
 * Created by zhangjianxin on 2017/4/6.
 * @author  zhangjianxin
 */
public class auth {
    public static String code;
    public static auth instance;
    public static synchronized auth getInstance() {
        if (instance == null) {
            instance = new auth();
            code =  PropertyUtil.getProperty("Constants.Cluster.auth");
        }
        return instance;
    }
        public static String getBasicToken(){
            return code;
        }

        public static String getBearerToken(){
            return code;
        }

}
