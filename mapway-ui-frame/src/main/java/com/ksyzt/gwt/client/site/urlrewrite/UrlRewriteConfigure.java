package com.ksyzt.gwt.client.site.urlrewrite;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.CommonServerProxy;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;
import com.ksyzt.gwt.client.ui.dialog.Alert;
import com.ksyzt.gwt.shared.module.SystemConst;

// TODO: Auto-generated Javadoc

/**
 * The Class UrlRewriteConfigure.
 */
public class UrlRewriteConfigure extends MessageComposite {

  /** The ui binder. */
  private static UrlRewriteConfigureUiBinder uiBinder = GWT
      .create(UrlRewriteConfigureUiBinder.class);

    /**
     * The Interface UrlRewriteConfigureUiBinder.
     */
    interface UrlRewriteConfigureUiBinder extends UiBinder<Widget, UrlRewriteConfigure> {
  }

    /**
     * The btn new.
     */
    @UiField
  Anchor btnNew;

    /**
     * On btn new.
     *
     * @param e the e
     */
    @UiHandler("btnNew")
  void onBtnNew(ClickEvent e) {
    CellUrlMapper cell = new CellUrlMapper();
    cell.addMessageHandler(m_row_handler);
    center.remove(btnNew);
    center.add(cell);
    cell.setStyleName("site-Row" + (index++ % 2));
  }

  /** The m delete handler. */
  private AsyncCallback<List<RewriteData>> m_delete_handler =
      new AsyncCallback<List<RewriteData>>() {

        @Override
        public void onSuccess(List<RewriteData> result) {
          renderData(result);
        }

        @Override
        public void onFailure(Throwable caught) {
          message(caught.getMessage());

        }
      };

  /** The m update handle. */
  private AsyncCallback<List<RewriteData>> m_update_handle =
      new AsyncCallback<List<RewriteData>>() {

        @Override
        public void onSuccess(List<RewriteData> result) {
          message("修改成功");
          renderData(result);

        }

        @Override
        public void onFailure(Throwable caught) {
          message(caught.getMessage());

        }
      };

  /** The m row handler. */
  private MessageHandler m_row_handler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {

      RewriteData d = (RewriteData) value;
      if (d.from.length() == 0 || d.to.length() == 0) {
        message("请输入正确的映射关系");
      } else if (d.from == d.to) {
        Alert alert = new Alert(SystemConst.CAPTION_WRONG, SystemConst.MSG_SITE_URL_IDENTITY);
        alert.show();
        alert.center();
      } else {
        message("开始更新");
        if (message == MessageEvent.DELETE) {
          CommonServerProxy.SITE_MANAGER.updateRewriteData(d, m_delete_handler);
        } else if (message == MessageEvent.EDIT) {
          CommonServerProxy.SITE_MANAGER.updateRewriteData(d, m_update_handle);
        }
      }

    }
  };

    /**
     * The index.
     */
    int index = 0;

  /** The m on data. */
  private AsyncCallback<List<RewriteData>> m_on_data = new AsyncCallback<List<RewriteData>>() {

    @Override
    public void onSuccess(List<RewriteData> result) {
      message("已获取数据");
      renderData(result);
    }

    @Override
    public void onFailure(Throwable caught) {
      message(caught.getMessage());
    }
  };

    /**
     * Instantiates a new url rewrite configure.
     */
    public UrlRewriteConfigure() {
    initWidget(uiBinder.createAndBindUi(this));
  }

    /**
     * Inits the.
     */
    public void init() {
    message("开始请求数据");
    CommonServerProxy.SITE_MANAGER.getRewriteData(m_on_data);
  }

  /**
   * Render data.
   *
   * @param result the result
   */
  private void renderData(List<RewriteData> result) {
    center.clear();
    index = 0;
    for (RewriteData d : result) {
      CellUrlMapper cell = new CellUrlMapper();
      cell.addMessageHandler(m_row_handler);
      center.add(cell);
      cell.setStyleName("site-Row" + (index++ % 2));
      cell.renderCell(d);
    }
  }

    /**
     * The center.
     */
    @UiField
  HTMLPanel center;
}
