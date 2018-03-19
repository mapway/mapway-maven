package com.ksyzt.gwt.shared.module;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc

/**
 * 描述网站信息.
 *
 * @author Administrator
 */

public class SiteInformation implements Serializable {

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
		return name;
	}

    /**
     * Sets the name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets the summary.
     *
     * @return the summary
     */
    public String getSummary() {
		return summary;
	}

    /**
     * Sets the summary.
     *
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
		this.summary = summary;
	}

    /**
     * Gets the when.
     *
     * @return the when
     */
    public Date getWhen() {
		return when;
	}

    /**
     * Sets the when.
     *
     * @param when the when to set
     */
    public void setWhen(Date when) {
		this.when = when;
	}

    /**
     * Gets the version.
     *
     * @return the version
     */
    public int getVersion() {
		return version;
	}

    /**
     * Sets the version.
     *
     * @param version the version to set
     */
    public void setVersion(int version) {
		this.version = version;
	}

    /**
     * Gets the icp.
     *
     * @return the icp
     */
    public String getIcp() {
		return icp;
	}

    /**
     * Sets the icp.
     *
     * @param icp the icp to set
     */
    public void setIcp(String icp) {
		this.icp = icp;
	}

    /**
     * Gets the secno.
     *
     * @return the secno
     */
    public String getSecno() {
		return secno;
	}

    /**
     * Sets the secno.
     *
     * @param secno the secno to set
     */
    public void setSecno(String secno) {
		this.secno = secno;
	}

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
		return email;
	}

    /**
     * Sets the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
		this.email = email;
	}

    /**
     * Gets the web.
     *
     * @return the web
     */
    public String getWeb() {
		return web;
	}

    /**
     * Sets the web.
     *
     * @param web the web to set
     */
    public void setWeb(String web) {
		this.web = web;
	}

    /**
     * Gets the open.
     *
     * @return the open
     */
    public boolean getOpen() {
		return open;
	}

    /**
     * Sets the open.
     *
     * @param open the open to set
     */
    public void setOpen(boolean open) {
		this.open = open;
	}

    /**
     * Checks if is allowreg.
     *
     * @return the allowreg
     */
    public boolean isAllowreg() {
		return allowreg;
	}

    /**
     * Sets the allowreg.
     *
     * @param allowreg the allowreg to set
     */
    public void setAllowreg(boolean allowreg) {
		this.allowreg = allowreg;
	}

    /**
     * Gets the keywords.
     *
     * @return the keywords
     */
    public String getKeywords() {
		return keywords;
	}

    /**
     * Sets the keywords.
     *
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

    /**
     * Gets the siteowner.
     *
     * @return the siteowner
     */
    public String getSiteowner() {
		return siteowner;
	}

    /**
     * Sets the siteowner.
     *
     * @param siteowner the siteowner to set
     */
    public void setSiteowner(String siteowner) {
		this.siteowner = siteowner;
	}

    /**
     * Gets the serialversionuid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5990058217383767521L;

	
	/** The name. */
	private String name;
	
	/** The summary. */
	private String summary;
	
	/** The when. */
	private Date when;

	/** The version. */
	private int version;
	
	/** The icp. */
	private String icp;
	
	/** The secno. */
	private String secno;
	
	/** The email. */
	private String email;
	
	/** The web. */
	private String web;
	
	/** The open. */
	private boolean open;
	
	/** The allowreg. */
	private boolean allowreg;
	
	/** The keywords. */
	private String keywords;
	
	/** The siteowner. */
	private String siteowner;
	
	/** The tel. */
	private String tel;

    /**
     * Gets the tel.
     *
     * @return the tel
     */
    public String getTel() {
		return tel;
	}

    /**
     * Sets the tel.
     *
     * @param tel the tel to set
     */
    public void setTel(String tel) {
		this.tel = tel;
	}
}
