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

    /**
     * Sets image upload proxy url.
     *
     * @param imageUploadProxyUrl the image upload proxy url
     */
    public void setImageUploadProxyUrl(String imageUploadProxyUrl) {
        this.imageUploadProxyUrl = imageUploadProxyUrl;
    }

    @Override
    public String getImagePrefix() {
        return imagePrefix;
    }

    /**
     * Sets image prefix.
     *
     * @param imagePrefix the image prefix
     */
    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    @Override
    public String getMqttServer() {
        return mqttServer;
    }

    /**
     * Sets mqtt server.
     *
     * @param mqttServer the mqtt server
     */
    public void setMqttServer(String mqttServer) {
        this.mqttServer = mqttServer;
    }

    @Override
    public String getMqttPort() {
        return mqttPort;
    }

    /**
     * Sets mqtt port.
     *
     * @param mqttPort the mqtt port
     */
    public void setMqttPort(String mqttPort) {
        this.mqttPort = mqttPort;
    }

    public String getMqttPath() {
        return mqttPath;
    }

    /**
     * Sets mqtt path.
     *
     * @param mqttPath the mqtt path
     */
    public void setMqttPath(String mqttPath) {
        this.mqttPath = mqttPath;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    /**
     * Sets logo.
     *
     * @param logo the logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String getCompileInformation() {
        return compileInformation;
    }

    /**
     * Sets compile information.
     *
     * @param compileInformation the compile information
     */
    public void setCompileInformation(String compileInformation) {
        this.compileInformation = compileInformation;
    }

    /**
     * The Image upload proxy url.
     */
    public String imageUploadProxyUrl = "";
    /**
     * The Image prefix.
     */
    public String imagePrefix = "";
    /**
     * The Mqtt server.
     */
    public String mqttServer = "";
    /**
     * The Mqtt port.
     */
    public String mqttPort = "";
    /**
     * The Mqtt path.
     */
    public String mqttPath = "";
    /**
     * The Logo.
     */
    public String logo = "";
    /**
     * The Compile information.
     */
    public String compileInformation = "";

}
