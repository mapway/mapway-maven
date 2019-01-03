package cn.mapway.document.ui.client.rpc.github;

import com.google.gwt.core.client.JavaScriptObject;

public class GithubUser extends JavaScriptObject {

    protected GithubUser() {
    }

    public native final Integer id()/*-{
        return this.id;
    }-*/;

    public native final void id(Integer id)/*-{
        this.id = id;
    }-*/;


    public native final String avatar()/*-{
        return this.avatar_url;
    }-*/;

    public native final void avator(String avatar)/*-{
        this.avatar_id = avatar;
    }-*/;

    public native final String name()/*-{
        return this.name;
    }-*/;

    public native final void name(String name)/*-{
        this.name = name;
    }-*/;
}
