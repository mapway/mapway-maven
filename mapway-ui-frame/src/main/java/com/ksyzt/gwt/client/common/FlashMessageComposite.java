package com.ksyzt.gwt.client.common;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.shared.module.SystemConst;

// TODO: Auto-generated Javadoc

/**
 * The Class FlashMessageComposite.
 */
public abstract class FlashMessageComposite extends MessageComposite {

    /**
     * Instantiates a new flash message composite.
     */
    public FlashMessageComposite() {
	}

	// //////////////////////////////图片编辑区

	/**
	 * Inits the script.
	 */
	private native void init_script()
	/*-{
		var __this = this;

		$wnd.uploadresult = function(data) {
			__this.@com.ksyzt.gwt.client.common.FlashMessageComposite::onSaveImage(Ljava/lang/String;)(data);
		};
	}-*/;

    /**
     * On save image.
     *
     * @param json the json
     */
    protected abstract void onSaveImage(String json);

    /**
     * The m proxy.
     */
    PostImageProxy m_proxy;

    /**
     * The m needmove.
     */
    boolean m_needmove = false;

    /**
     * Movefalsh.
     */
    public void movefalsh() {
		if (m_needmove == true) {
			showflash();
			m_needmove = false;
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	@Override
	public void onLoad() {
		super.onLoad();
		init_script();
		m_needmove = true;
		m_proxy = PostImageProxy.getPostImageProxy(SystemConst.FLASHID);
	}

    /**
     * 隐藏FLASH控件.
     */
    public void hideflash() {
		m_proxy.hide();
		m_needmove = true;
	}

    /**
     * On show flash.
     */
    protected abstract void onShowFlash();

    /**
     * Showflash.
     */
    public void showflash() {
		m_proxy.show();
		onShowFlash();
	}

    /**
     * Show picture.
     *
     * @param path the path
     * @param id   the id
     */
    public void showPicture(String path, Integer id) {
		m_proxy.showPicture(path, id);
	}

    /**
     * 移动Flash控件到指定位置.
     *
     * @param left the left
     * @param top  the top
     */
    public void moveFlashTo(int left, int top) {
		m_proxy.moveTo(left, top);
	}

    /**
     * Gets the post image proxy.
     *
     * @return the post image proxy
     */
    public PostImageProxy getPostImageProxy() {
		return m_proxy;
	}

    /**
     * 插入节点.
     *
     * @param w the w
     */
    public void insertTo(Widget w) {
		Element p = w.getElement();
		Element ele = m_proxy.cast();
		if (!ele.getParentElement().equals(p)) {
			ele.removeFromParent();
			p.appendChild(ele);
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onUnload()
	 */
	@Override
	public void onUnload() {
		super.onUnload();
		hideflash();
	}
}
