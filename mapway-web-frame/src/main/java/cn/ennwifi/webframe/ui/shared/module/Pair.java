package cn.ennwifi.webframe.ui.shared.module;


import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * The type Pair.
 *
 * @param <T> the type parameter
 * @param <V> the type parameter
 */
public class Pair<T, V> implements IsSerializable {

    /**
     * The Key.
     */
    public T key;

    /**
     * The Value.
     */
    public V value;

}