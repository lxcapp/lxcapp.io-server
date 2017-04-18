package io.swagger.api;


import com.alibaba.fastjson.JSONObject;
import io.swagger.api.util.ClassUtil;
import io.swagger.api.util.File2Json;
import io.swagger.api.util.auth;
import io.swagger.api.util.loadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.msf4j.MicroservicesRunner;
import org.wso2.msf4j.analytics.metrics.MetricsInterceptor;

import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by zhangjianxin on 2017/3/23.
 */
public class entrypoint {

    private static Logger logger = LoggerFactory.getLogger(entrypoint.class);
    public static MicroservicesRunner runner;
    public static void main(String[] args) throws Exception {
        //File2Json.load();
        //loadConfig.getInstance();//一次性初始化
        //auth.getInstance();
        startServer();
    }

    private static void startServer() throws IllegalAccessException, InstantiationException {
        //获取配置
        //JSONObject apiJson = File2Json.getConfig("api");
        //JSONObject serverJson = apiJson.getJSONObject("server");
        //int port = serverJson.getInteger("port");
        //获取所有service类
        List<Class<?>> serviceList = ClassUtil.getAllClassByAnnotation(Path.class, "io.swagger.api");
        runner = new MicroservicesRunner(9098);
        logger.info("Loadding All Service ... ...");
        for (Class serviceCls : serviceList) {
            runner.deploy(serviceCls.newInstance());
            runner.addInterceptor(new MetricsInterceptor());//添加Metrics监控
            Path annot = (Path) serviceCls.getAnnotation(Path.class);
            logger.info("***Loaded Service Class [" + serviceCls.getName() + "] Request Path [" + annot.value() + "]");
        }
        runner.start();
    }
}
