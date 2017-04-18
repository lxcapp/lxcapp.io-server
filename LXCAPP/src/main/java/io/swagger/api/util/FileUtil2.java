package io.swagger.api.util;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文件操作代码
 *
 * @author zhangjainxin
 * @date
 */
public class FileUtil2 {
    /**
     * 将文本文件中的内容读入到buffer中
     *
     * @param buffer   buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     * @author zhangjainxin
     * @date
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     *
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     * @author zhangjainxin
     * @date 2017-1-7
     */
    public static String readFile(String filePath) {
        StringBuffer sb = new StringBuffer();
        try {
            FileUtil2.readToBuffer(sb, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param filePath 文件路径
     * @param suffix   后缀名, 为空则表示所有文件
     * @param isdepth  是否遍历子目录
     * @return list
     * 文件路径及其子目录下的所有文件名的集合
     */
    public static List<String> getListFiles(String filePath, String suffix,
                                            boolean isdepth) {
        List<String> lstFileNames = new ArrayList<String>();
        File file = new File(filePath);
        return FileUtil2.listFile(lstFileNames, file, suffix, isdepth);
    }

    public static List<String> listFile(List<String> lstFileNames, File f,
                                        String suffix, boolean isdepth) {
        // 若是目录, 采用递归的方法遍历子目录
        if (f.isDirectory()) {
            File[] t = f.listFiles();

            for (int i = 0; i < t.length; i++) {
                if (isdepth || t[i].isFile()) {
                    listFile(lstFileNames, t[i], suffix, isdepth);
                }
            }
        } else { //是文件
            String filePath = f.getAbsolutePath();
            if (!suffix.equals("")) {
                int begIndex = filePath.lastIndexOf("."); // 最后一个.(即后缀名前面的.)的索引
                String tempsuffix = "";

                if (begIndex != -1) {
                    tempsuffix = filePath.substring(begIndex + 1,
                            filePath.length());
                    if (tempsuffix.equals(suffix)) {
                        lstFileNames.add(filePath);
                    }
                }
            } else {
                lstFileNames.add(filePath);
            }
        }
        return lstFileNames;
    }

    public static List<byte[]> fileBlock2(File file, int size) {
        List<byte[]> list = new LinkedList<byte[]>();
        try {
            InputStream in = null;
            in = new FileInputStream(file);
            int totalSize = in.available();
            int part = 0;
            int mod = 0;
            part = totalSize / size;
            mod = totalSize % size;
            for (int i = 0; i < part; i++) {
                byte[] buffer = new byte[size];
                in.read(buffer);
                list.add(buffer);
            }
            if (mod > 0) {
                byte[] buffer = new byte[mod];
                in.read(buffer);
                list.add(buffer);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<byte[]> fileBlock4(File file, int size) {
        List<byte[]> list = new LinkedList<byte[]>();
        try {
            InputStream in = null;
            in = new FileInputStream(file);
            String charset = getCharset(file);
            InputStreamReader isr = new InputStreamReader(in, charset);
            BufferedReader br = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();
            String line; // 用来保存每行读取的内容
            line = br.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                sb.append(line); // 将读到的内容添加到 buffer 中
                sb.append("\r\n"); // 添加换行符
                line = br.readLine(); // 读取下一行
            }

            String str = sb.toString();
            int index = str.lastIndexOf("\r\n");
            str = str.substring(1, index);
            //System.out.println("ssss:" + str);
            byte[] totalBytes = str.getBytes(charset);
            int totalSize = totalBytes.length;
            int part = 0;
            int mod = 0;
            part = totalSize / size;
            mod = totalSize % size;
            for (int i = 0; i < part; i++) {
                byte[] buffer = new byte[size];
                for (int j = 0; j < size; j++) {
                    buffer[j] = totalBytes[j + i * size];
                }

                list.add(buffer);
            }
            if (mod > 0) {
                byte[] buffer = new byte[mod];
                for (int j = 0; j < mod; j++) {
                    buffer[j] = totalBytes[j + part * size];
                }
                list.add(buffer);
            }


            br.close();
            in.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<byte[]> fileBlock3(File file, int size) {
        List<byte[]> list = new LinkedList<byte[]>();
        try {
            InputStream in = null;
            in = new FileInputStream(file);
            String charset = getCharset(file);
            InputStreamReader isr = new InputStreamReader(in, charset);

            BufferedReader br = new BufferedReader(isr);
            String line = null;
            String str = "";
            String enter = new String("\r\n".getBytes(), charset);
            while ((line = br.readLine()) != null) {
                str = str + enter + line;
                //str = str + line;
            }
            //str = str.substring(str.indexOf(enter) + 3);

            byte[] totalBytes = str.getBytes(charset);
            int totalSize = totalBytes.length;
            int part = 0;
            int mod = 0;
            part = totalSize / size;
            mod = totalSize % size;
            for (int i = 0; i < part; i++) {
                byte[] buffer = new byte[size];
                for (int j = 0; j < size; j++) {
                    buffer[j] = totalBytes[j + i * size];
                }

                list.add(buffer);
            }
            if (mod > 0) {
                byte[] buffer = new byte[mod];
                for (int j = 0; j < mod; j++) {
                    buffer[j] = totalBytes[j + part * size];
                }
                list.add(buffer);
            }
//            for(int i=0;i<list.size();i++){
//                System.out.println(new String(list.get(i),charset));
//            }

            br.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static List<byte[]> fileBlock(File f) {
        List<byte[]> list = new LinkedList<byte[]>();
        try {

            ByteBuffer buf = ByteBuffer.allocate(20);
            FileInputStream fin = new FileInputStream(f);
            FileChannel channel = fin.getChannel();
            //读取文件进行切块，注意边界的处理，不能出现断行
            //从文件中读取块指定大小的数据
            while ((channel.read(buf)) != -1) {
                //处理边界文件，从buf的最后开始向前找最近的“\r\n”,进行切割，之前的做为一块，剩余的留到下一块作为开头
                buf.flip();

                int keyPosition = -1;
                for (int i = buf.limit() - 1; ; i--) {
                    if ('\n' == (char) buf.get(i)) {
                        keyPosition = i + 1;
                        break;
                    }
                }

                byte block[] = new byte[keyPosition];
                buf.get(block);

                byte temp[] = new byte[buf.limit() - keyPosition];
                buf.get(temp);

                list.add(block);
                buf.clear();

                buf.put(temp);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    /**
     * 多线程执行
     *
     * @param threaNum 线程数
     * @param cls      执行模块
     */
    public static void threadPoolRun(int threaNum, Class<?> cls) {
        ExecutorService pool = Executors.newFixedThreadPool(threaNum);

        for (int i = 0; i < threaNum; i++) {
            Class clss = null;
            try {
                clss = Class.forName(cls.getName());
                Constructor constructor = clss.getConstructor(); //构造函数参数列表的class类型
                Runnable able = (Runnable) constructor.newInstance(); //传参
                Thread thread = new Thread(able);
                thread.setName(cls.getName() + (i + 1));
                pool.execute(thread);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public static String getCharset(File fileName) throws IOException {

        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        byte[] b = new byte[10];
        bin.read(b, 0, b.length);

        String first = toHex(b);

        //这里可以看到各种编码的前几个字符是什么，gbk编码前面没有多余的
        String code = null;
        if (first.startsWith("EFBBBF")) {
            code = "UTF-8";
        } else if (first.startsWith("FEFF00")) {
            code = "UTF-16BE";
        } else if (first.startsWith("FFFE")) {
            code = "Unicode";
        } else if (first.startsWith("FFFE")) {
            code = "Unicode";
        } else {
            code = "GBK";
        }
        return code;
    }


    public static String toHex(byte[] byteArray) {
        int i;
        StringBuffer buf = new StringBuffer("");
        int len = byteArray.length;

        for (int offset = 0; offset < len; offset++) {

            i = byteArray[offset];

            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");

            buf.append(Integer.toHexString(i));
        }

        return buf.toString().toUpperCase();
    }


    /**
     * 判断文件的编码格式
     *
     * @param fileName :file
     * @return 文件编码格式
     * @throws Exception
     */
    public static String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        System.out.println(p);
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 12594:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }

        return code;
    }


}