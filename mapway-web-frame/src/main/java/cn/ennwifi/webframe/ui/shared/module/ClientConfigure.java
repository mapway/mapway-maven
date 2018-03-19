package cn.ennwifi.webframe.ui.shared.module;

/**
 * 客户端配置信息.
 */
public interface ClientConfigure {

    /**
     * Gets image upload proxy url.
     *
     * @return the image upload proxy url
     */
    String getImageUploadProxyUrl();

    /**
     * Gets image prefix.
     *
     * @return the image prefix
     */
    String getImagePrefix();

    /**
     * Gets mqtt server.
     *
     * @return the mqtt server
     */
    String getMqttServer();

    /**
     * Gets mqtt port.
     *
     * @return the mqtt port
     */
    String getMqttPort();

    /**
     * Gets mqtt path.
     *
     * @return the mqtt path
     */
    String getMqttPath();

    /**
     * Gets compile information.
     *
     * @return the compile information
     */
    String getCompileInformation();

    /**
     * Application Logo
     *
     * @return logo
     */
    String getLogo();
}
