package io.swagger.api.util;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.LogLevel;
import org.influxdb.InfluxDBFactory;

import java.io.IOException;

/**
 * Created by zhangjianxin on 2016/11/29.
 */
public class APIInfluxdbHelper {
    private static InfluxDB influxDB;
    private static String default_databaseName = PropertyUtil.getProperty("influxdb.database");//默认数据库
    private static String Influxdb_connect_url = "http://" + PropertyUtil.getProperty("influxdb.hostaddr");
    private static String Influxdb_connect_port = PropertyUtil.getProperty("influxdb.port");
    private static String default_databaseUserName = PropertyUtil.getProperty("influxdb.username");//默认数据库用户名
    private static String default_databaseUserPass = PropertyUtil.getProperty("influxdb.password");//默认数据库密码

    public static void setUp() throws InterruptedException, IOException {
        influxDB = InfluxDBFactory.connect(Influxdb_connect_url + ":" + Influxdb_connect_port, default_databaseUserName, default_databaseUserPass);
        boolean influxDBstarted = false;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        influxDB.setLogLevel(LogLevel.NONE);//日志级别
        System.out.println("##################################################################################");
        System.out.println("#  Connected to InfluxDB Version: " + influxDB.version() + " #");
        System.out.println("##################################################################################");
    }

    public APIInfluxdbHelper() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String dateFormat(Long val) {
        String date = "";
        try {
            date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(val * 1000L));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
//select * from  memory_free_mb_size  where time < now() -1h limit 10
