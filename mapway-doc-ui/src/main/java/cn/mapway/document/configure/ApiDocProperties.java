package cn.mapway.document.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置属性
 */
@ConfigurationProperties(prefix = "mapway.apidoc")
public class ApiDocProperties {
    /**
     * The Url.
     */
    public String url="/doc/*";
    /**
     * The Logo.
     */
    public String logo="";
    /**
     * The Author.
     */
    public String author="zhangjianshe@gmail.com";
    /**
     * The Base path.
     */
    public String basePath="";
    /**
     * The Domain.
     */
    public String domain="mapway.cn";
    /**
     * The Scans.
     */
    public String scans="";
    /**
     * The Sub title.
     */
    public String subTitle="接口文档说明";
    /**
     * The Title.
     */
    public String title="接口文档";
    /**
     * The Copyright.
     */
    public String copyright="版权信息";
    /**
     * The Version.
     */
    public String version="1.0.0";
    /**
     * The Home.
     */
    public String home="../../";
    /**
     * The Style.
     */
    public String style="";

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets logo.
     *
     * @return the logo
     */
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

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets base path.
     *
     * @return the base path
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Sets base path.
     *
     * @param basePath the base path
     */
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * Gets domain.
     *
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets domain.
     *
     * @param domain the domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Gets scans.
     *
     * @return the scans
     */
    public String getScans() {
        return scans;
    }

    /**
     * Sets scans.
     *
     * @param scans the scans
     */
    public void setScans(String scans) {
        this.scans = scans;
    }

    /**
     * Gets sub title.
     *
     * @return the sub title
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * Sets sub title.
     *
     * @param subTitle the sub title
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets copyright.
     *
     * @return the copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets copyright.
     *
     * @param copyright the copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets home.
     *
     * @return the home
     */
    public String getHome() {
        return home;
    }

    /**
     * Sets home.
     *
     * @param home the home
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * Gets style.
     *
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets style.
     *
     * @param style the style
     */
    public void setStyle(String style) {
        this.style = style;
    }
}
