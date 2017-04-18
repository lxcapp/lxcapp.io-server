package io.swagger.api.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.LoadImageCmd;
import com.github.dockerjava.api.command.PullImageCmd;
import com.github.dockerjava.api.command.SaveImageCmd;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.PullImageResultCallback;
import com.github.dockerjava.netty.NettyDockerCmdExecFactory;

import java.io.InputStream;


/**
 * Created by zhangjianxin on 2016/12/14.
 */
public class DockerEngine {
    private static DockerEngine engine;
    private static String DOCKERHOST = PropertyUtil.getProperty("DOCKER_H");
    private static String REGISTRY = PropertyUtil.getProperty("DOCKER_REG");
    /*Docker获取实例*/
    NettyDockerCmdExecFactory factory = new NettyDockerCmdExecFactory();
    DefaultDockerClientConfig.Builder configBuilder = new DefaultDockerClientConfig.Builder()
            .withDockerTlsVerify(false)
            .withDockerHost(DOCKERHOST)
            .withRegistryUrl(REGISTRY)
            .withApiVersion("1.24");

    public static DockerEngine getInstance(){
        if (engine == null) {
            engine = new DockerEngine();
        }
        return engine;
    }

    DockerClient client = DockerClientBuilder.getInstance(configBuilder)
            .withDockerCmdExecFactory(factory)
            .build();

    /*****
     * 保存镜像 -> InputStream
     * @param imagesURL
     * @return imageIO
     */
    public static InputStream saveImage(String imagesURL){
        SaveImageCmd saveImageCmd = DockerEngine.getInstance().client.saveImageCmd(imagesURL);
        InputStream imageIO = saveImageCmd.exec();
        return  imageIO;
    }

    /*****
     * 发送InputStream -->slave
     * @param in
     */
    public static void saveInputStream(InputStream in){
        LoadImageCmd loadImageCmd =  DockerEngine.getInstance().client.loadImageCmd(in);
        loadImageCmd.exec();
    }

    /****
     * 拉去镜像到本地
     * @param t
     * @param r
     * @param i
     * @return
     */
    public static Boolean pullImagesCMD(String t,String r,String i ){

        PullImageCmd pullImageCmd = DockerEngine.getInstance().client.pullImageCmd(i);
        pullImageCmd.withTag(t);
        pullImageCmd.withRegistry(r);
        pullImageCmd.exec(new PullImageResultCallback()).awaitSuccess();

        return true;
    }

    public static void main(String[] args) {
        loadConfig.getInstance(); //初始化文件

        DockerEngine engine = DockerEngine.getInstance();//实例

        Info dinfo =  engine.client.infoCmd().exec();

        System.out.println(dinfo.getContainers());

        System.out.println(dinfo);

        PullImageCmd pullImageCmd = engine.client.pullImageCmd("192.168.1.18:5000/uniboss/java");
        pullImageCmd.withTag("latest");
        pullImageCmd.withRegistry("http://192.168.1.18:5000/v2/");
        pullImageCmd.exec(new PullImageResultCallback()).awaitSuccess();

        System.out.println(pullImageCmd.getRegistry());

        SaveImageCmd saveImageCmd= engine.client.saveImageCmd("192.168.1.18:5000/uniboss/java");
        saveImageCmd.withTag("latest");
        InputStream imageIO = saveImageCmd.exec();//获得文件流

        //加载镜像到本地。
        LoadImageCmd loadImageCmd = engine.client.loadImageCmd(imageIO);

    }

}
