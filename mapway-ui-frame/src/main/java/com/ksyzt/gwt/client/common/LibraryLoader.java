package com.ksyzt.gwt.client.common;

import java.util.ArrayList;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.core.client.prefetch.RunAsyncCode;

// TODO: Auto-generated Javadoc
/**
 * The Class LibraryLoader.
 */
public class LibraryLoader {

	/** The m callback. */
	LoaderCallback m_callback;

	/** The count. */
	int count = 0;
	
	/** The m urls. */
	ArrayList<String> m_urls;

	/**
	 * Instantiates a new library loader.
	 */
	public LibraryLoader() {
		// m_callback = callback;
		m_urls = new ArrayList<String>();
	}

	/**
	 * Adds the library.
	 *
	 * @param url the url
	 */
	public void addLibrary(String url) {
		m_urls.add(url);
	}

	/**
	 * Start.
	 *
	 * @param callback the callback
	 */
	public void start(LoaderCallback callback) {
		
		
		if (callback != null) {
			m_callback = callback;
		}

		for (String url : m_urls) {
			ScriptInjector.fromUrl(url)
					.setCallback(new Callback<Void, Exception>() {
						public void onFailure(Exception reason) {
							if (m_callback != null) {
								m_callback.onResult(false, reason.getMessage());
							}
						}

						public void onSuccess(Void result) {
							count++;
							if (count == m_urls.size()) {
								if (m_callback != null) {
									m_callback.onResult(true, "success");
								}
							}
						}
					}).inject();
		}
	}
}
