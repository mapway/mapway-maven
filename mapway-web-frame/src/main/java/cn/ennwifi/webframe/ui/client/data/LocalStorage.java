package cn.ennwifi.webframe.ui.client.data;

import com.google.gwt.storage.client.Storage;

/**
 * The type Local storage.
 */
public class LocalStorage {

    /**
     * The Constant storage.
     */
    static final Storage storage = Storage.getLocalStorageIfSupported();

    /**
     * Save.
     *
     * @param key  the key
     * @param data the data
     */
    public static void save(String key, String data) {
        if (storage == null) {
            return;
        }
        storage.setItem(key, data);
    }

    /**
     * Val.
     *
     * @param key the key
     * @return the string
     */
    public static String val(String key) {
        if (storage == null) {
            return null;
        }

        String data = storage.getItem(key);
        return data;
    }
}
