package io.swagger.api.util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 2017/3/23.
 */
public class File2Json {
    public static void main(String[] args) {
        load();
    }

    private static Logger logger = LoggerFactory.getLogger(File2Json.class);

    private static Map<String, JSONObject> file_json_hm = new HashMap<String, JSONObject>();

    public static void load() {
        List<JSONObject> jsonList = getResorcesJson();
        logger.info("Loadding JsonConfig ... ... ..."+jsonList);
        if (jsonList != null && jsonList.size() > 0) {
            for (JSONObject json : jsonList) {
                String name = json.getString("name");
                String path = json.getString("path");
                String str = FileUtil.readTxtFile(path);
                JSONObject jsonConfig = JSON.parseObject(str);
                file_json_hm.put(name, jsonConfig);
                logger.info("JsonParser: " + name + ".json to JSONObject");
            }
        }
    }


    public static String getValue(String fileName, String key) {
        return getConfig(fileName) == null ? null : getConfig(fileName).getString(key);
    }

    public static JSONObject getConfig(String fileName) {
        return file_json_hm.get(fileName);
    }

    private static List<JSONObject> getResorcesJson() {
        String basePath = File2Json.class.getClassLoader().getResource(".").getPath();
        if (!OSUtil.isWindowsOS()) {

        }
        System.out.println(basePath);
        File resourcesFile = new File(basePath);
        String[] files = null;
        files = resourcesFile.list();
        List<JSONObject> list = new ArrayList<JSONObject>();
        if (files != null && files.length > 0) {
            for (String fileName : files) {
                if (fileName.endsWith(".json")) {
                    String path = basePath + fileName;
                    String name = fileName.substring(0, fileName.lastIndexOf("."));
                    JSONObject json = new JSONObject();
                    json.put("name", name);
                    json.put("path", path);
                    list.add(json);
                }
            }
        }
        return list;
    }


}
