package cn.ennwifi.webframe.service.module;

import java.util.List;

/**
 * The type Meta req.
 */
public class MetaReq {
	private String code;

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
		return code;
	}

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
		this.code = code;
	}

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
		return name;
	}

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets submeta.
     *
     * @return the submeta
     */
    public List<MetaReq> getSubmeta() {
		return submeta;
	}

    /**
     * Sets submeta.
     *
     * @param submeta the submeta
     */
    public void setSubmeta(List<MetaReq> submeta) {
		this.submeta = submeta;
	}
	private String name;
	private List<MetaReq> submeta;

}
