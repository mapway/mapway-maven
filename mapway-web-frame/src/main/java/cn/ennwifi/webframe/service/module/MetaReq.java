package cn.ennwifi.webframe.service.module;

import java.util.List;

public class MetaReq {
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MetaReq> getSubmeta() {
		return submeta;
	}
	public void setSubmeta(List<MetaReq> submeta) {
		this.submeta = submeta;
	}
	private String name;
	private List<MetaReq> submeta;

}
