package io.swagger.api.util; /**
 * Created by 张建新 on 2017/3/8 0008.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.UUID;

public class base64yaml {

    /**
     * 将文件转成base64 字符串
     * @return  *
     * @throws Exception
     */

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new  String(Base64.encode(buffer));

    }

    /**
     * 将base64字符解码保存文件
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void decoderBase64File(String base64Code, String targetPath)
            throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        String str = new String(buffer);
        System.out.println("str: "+str);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();

    }

    /**
     * 将base64字符保存文本文件
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void toFile(String base64Code, String targetPath)
            throws Exception {
        System.out.println(base64Code);
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }


    public static String initTemplateConfig(){
        File file = new File((System.getenv("APP_HOME_CONF_DIR")+"/Store.json"));
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);

        return (str+"-"+temp).substring(0,62);
    }

    public static void main(String[] args) {
        try {
            System.out.println(getUUID().length());
            JSONObject store = JSON.parseObject(initTemplateConfig());
            JSONObject metadata = store.getJSONObject("metadata");
            JSONObject data = store.getJSONObject("data");

            System.out.println(metadata);
            System.out.println(data);
            System.out.println("all:"+store);
            String base64Code = encodeBase64File("D:\\uea.yaml");
            System.out.println(base64Code);
            decoderBase64File(base64Code, "D:\\back");
            toFile(base64Code, "D:\\uea_base64.yaml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
