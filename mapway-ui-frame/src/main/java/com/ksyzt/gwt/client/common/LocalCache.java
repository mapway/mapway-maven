package com.ksyzt.gwt.client.common;

import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;

// TODO: Auto-generated Javadoc
/**
 * The Class LocalCache.
 */
public class LocalCache {
	
	/** The Constant localStorage. */
	private final static Storage localStorage =Storage.getLocalStorageIfSupported();

	/**
	 * Gets the cache.
	 *
	 * @return the cache
	 */
	public static StorageMap getCache() {
		if (localStorage == null) {
			return null;
		} else {
			return new StorageMap(localStorage);
		}
	}
}
