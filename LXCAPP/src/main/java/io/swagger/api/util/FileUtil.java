package io.swagger.api.util;

import java.io.*;

/**
 * Created by lcy on 2017/3/23.
 */
public class FileUtil {
    public static String readTxtFile(String filePath) {
        InputStreamReader read=null;
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                String respStr = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    respStr += lineTxt;
                }
                return respStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(read!=null){
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
