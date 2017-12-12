package cn.ennwifi.webframe.ui.shared.module;


import com.google.gwt.user.client.rpc.IsSerializable;


public class Pair<T, V> implements IsSerializable {

    public T key;

    public V value;

}