package io.swagger.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by zhangjianxin on 2017/3/17 0017.
 */
public class loadConfig {
    private static loadConfig instance;
    private loadConfig (){
        File confFile = new File((System.getenv("LXC_HOME")+"/config.json"));
        Scanner confFileScanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            confFileScanner = new Scanner(confFile, "utf-8");
            while (confFileScanner.hasNextLine()) {
                buffer.append(confFileScanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

        } finally {
            if (confFileScanner != null) {
                confFileScanner.close();
            }
        }
        String [] template = buffer.toString().split("---");
        System.out.println(template.length);
    }
    public static synchronized loadConfig getInstance() {
        if (instance == null) {
            instance = new loadConfig();
        }
        return instance;
    }
}
