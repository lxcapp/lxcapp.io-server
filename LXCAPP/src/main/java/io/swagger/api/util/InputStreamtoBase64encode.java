package io.swagger.api.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
public class InputStreamtoBase64encode {

    public static String filetobase64encode(InputStream fileInputStream){
        String  base64code  = null;
        byte[] buffer = null;
        ByteArrayOutputStream bos = null;
        try {
            InputStream inputStream =fileInputStream;
            bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = inputStream.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            bos.close();
            buffer = bos.toByteArray();
            base64code = new String(Base64.encode(buffer));
            System.out.println("start-------------------------------------------------------------------------------------------");
            System.out.println("base64:" + base64code);
            System.out.println("end---------------------------------------------------------------------------------------------");
            fileInputStream.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return base64code;
    }
}
