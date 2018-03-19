package cn.ennwifi.webframe.ui.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.data.IFieldValue;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;


/**
 * 提供缺省的数据提供者接口.
 *
 * @author zhangjianshe
 */
public abstract class AbstractDataProvider implements IDataProvider<IFieldValue>, HasMessageHandlers {

    /**
     * The listners.
     */
    ArrayList<IListener> listners;

    /**
     * Instantiates a new abstract data provider.
     */
    public AbstractDataProvider() {
    listners = new ArrayList<IListener>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#getTitle()
   */
  @Override
  public abstract String getTitle();


  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#getPageSizeList()
   */
  @Override
  public int[] getPageSizeList() {
    return new int[] {10, 20, 30, 40, 50};
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#getTotalCount()
   */
  @Override
  public abstract Integer getTotalCount();

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#getCount()
   */
  @Override
  public abstract long getCount();

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#getHeaders()
   */
  @Override
  public List<HeaderInfo> getHeaders() {
    return new ArrayList<HeaderInfo>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#render(int, int,
   * com.ksyzt.gwt.client.data.IFieldValue, java.lang.Object)
   */
  @Override
  public Widget render(int row, int col, IFieldValue rowdata, Object cellData) {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#registerListener(cn.enn.neyun.ui.client.data.
   * IListener)
   */
  @Override
  public void registerListener(IListener listener) {
    if (listener != null) {
      listners.add(listener);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#unregisterListener(cn.enn.neyun.ui.client.data.
   * IListener)
   */
  @Override
  public void unregisterListener(IListener listener) {
    if (listener != null) {
      listners.remove(listener);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#getRowData(int)
   */
  @Override
  public abstract IFieldValue getRowData(int row);

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IDataProvider#notifyDataChange()
   */
  @Override
  public void notifyDataChange() {
    for (IListener l : listners) {
      l.invalidate();
    }
  }

    /**
     * Creates the {@link HandlerManager} used by this Widget. You can override this method to create
     * a custom {@link HandlerManager}.
     *
     * @return the {@link HandlerManager} you want to use
     */
    protected HandlerManager createHandlerManager() {
    return new HandlerManager(this);
  }

    /**
     * Adds this handler to the widget.
     *
     * @param <H>     the type of handler to add
     * @param handler the handler
     * @param type    the event type
     * @return {@link HandlerRegistration} used to remove the handler
     */
    public final <H extends EventHandler> HandlerRegistration addHandler(final H handler, GwtEvent.Type<H> type) {
    return ensureHandlers().addHandler(type, handler);
  }

    /**
     * Ensures the existence of the handler manager.
     *
     * @return the handler manager
     */
    HandlerManager ensureHandlers() {
    return handlerManager == null ? handlerManager = createHandlerManager() : handlerManager;
  }

  /** The handler manager. */
  private HandlerManager handlerManager;

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.event.shared.HasHandlers#fireEvent(com.google.gwt.event.shared.GwtEvent)
   */
  @Override
  public void fireEvent(GwtEvent<?> event) {
    if (handlerManager != null) {
      ensureHandlers().fireEvent(event);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.
   * MessageHandler)
   */
  @Override
  public HandlerRegistration addMessageHandler(MessageHandler handler) {
    return addHandler(handler, MessageEvent.TYPE);
  }
}
