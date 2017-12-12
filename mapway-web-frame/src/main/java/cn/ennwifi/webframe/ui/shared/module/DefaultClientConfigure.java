package cn.ennwifi.webframe.ui.shared.module;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 缺省的客户端配置实现
 */
public class DefaultClientConfigure implements ClientConfigure, IsSerializable {


    @Override
    public String getImageUploadProxyUrl() {
        return imageUploadProxyUrl;
    }

    public void setImageUploadProxyUrl(String imageUploadProxyUrl) {
        this.imageUploadProxyUrl = imageUploadProxyUrl;
    }

    @Override
    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    @Override
    public String getMqttServer() {
        return mqttServer;
    }

    public void setMqttServer(String mqttServer) {
        this.mqttServer = mqttServer;
    }

    @Override
    public String getMqttPort() {
        return mqttPort;
    }

    public void setMqttPort(String mqttPort) {
        this.mqttPort = mqttPort;
    }

    public String getMqttPath() {
        return mqttPath;
    }

    public void setMqttPath(String mqttPath) {
        this.mqttPath = mqttPath;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String getCompileInformation() {
        return compileInformation;
    }

    public void setCompileInformation(String compileInformation) {
        this.compileInformation = compileInformation;
    }

    public String imageUploadProxyUrl = "";
    public String imagePrefix = "";
    public String mqttServer = "";
    public String mqttPort = "";
    public String mqttPath = "";
    public String logo = "";
    public String compileInformation = "";

}
