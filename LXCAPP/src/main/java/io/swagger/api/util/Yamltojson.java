package io.swagger.api.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangjianxin on 2016/12/29.
 */
public class Yamltojson<T>{

    private final YAMLFactory yamlFactory;
    private final ObjectMapper mapper;
    private final Class<T> klass;
    private static Map<String, String> env = new HashMap<String, String>();

    public Yamltojson(Class<T> klass) {
        this.klass = klass;
        this.yamlFactory = new YAMLFactory();
        this.mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    }

    public T build(String path) throws IOException {
        try {
            InputStream input =new FileInputStream(path);
            YAMLParser yamlParser = yamlFactory.createParser(input);
            final JsonNode node = mapper.readTree(yamlParser);
            TreeTraversingParser treeTraversingParser = new TreeTraversingParser(node);
            final T config = mapper.readValue(treeTraversingParser, klass);
            return config;
        } catch (Exception e) {
            throw e;
        }
    }
    public T build(byte[] in) throws IOException {
        try {
            InputStream in_nocode = new ByteArrayInputStream(in);
            YAMLParser yamlParser = yamlFactory.createParser(in_nocode);
            final JsonNode node = mapper.readTree(yamlParser);
            TreeTraversingParser treeTraversingParser = new TreeTraversingParser(node);
            final T config = mapper.readValue(treeTraversingParser, klass);
            return config;
        } catch (Exception e) {
            throw e;
        }
    }

    public static JSONObject exec() {
        env = System.getenv();
        JSONObject result = new JSONObject();
        Yamltojson<JSONObject> yamltojson = new Yamltojson<JSONObject>(JSONObject.class);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
