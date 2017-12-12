package cn.ennwifi.webframe.ui.shared.module;

/**
 * 客户端配置信息.
 */
public interface ClientConfigure {

    String getImageUploadProxyUrl();

    String getImagePrefix();

    String getMqttServer();

    String getMqttPort();

    String getMqttPath();

    String getCompileInformation();

    /**
     * Application Logo
     *
     * @return
     */
    String getLogo();
}
