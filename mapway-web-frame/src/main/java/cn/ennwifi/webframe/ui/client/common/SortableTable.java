package cn.ennwifi.webframe.ui.client.common;

import cn.ennwifi.webframe.ui.client.data.HeaderInfo;
import cn.ennwifi.webframe.ui.client.data.IDataProvider;
import cn.ennwifi.webframe.ui.client.data.IListener;
import cn.ennwifi.webframe.ui.client.resource.SysResource;
import cn.ennwifi.webframe.ui.shared.module.Pair;
import cn.ennwifi.webframe.ui.shared.module.SearchReq;
import cn.mapway.ui.client.widget.common.TableEx;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import com.ksyzt.gwt.client.common.SimpleMessage;
import com.ksyzt.gwt.client.data.IFieldValue;

import java.util.ArrayList;
import java.util.List;


/**
 * 可排序表格.
 *
 * @author zhangjianshe
 */
public class SortableTable extends VerticalPanel implements IListener,
    HasValueChangeHandlers<SearchReq>, ChangeHandler {

  /** The sort table title. */
  NeLabel sortTableTitle;

  /** The top buttons. */
  HorizontalPanel topButtons;

  /** The refresh btn. */
  NeButton refreshBtn;

  /** The list. */
  ListBox list;

  /** The table. */
  TableEx table;

  /** The pages. */
  HorizontalPanel pages;

  SearchReq req;

  public SearchReq getReq() {
    return req;
  }

  public void setReq(SearchReq req) {
    this.req = req;
  }

  public Widget getCellWidget(int row, int column) {
    return table.getWidget(row, column);
  }

  /** The page size. */
  int pageSize = 2;

  /** The current page. */
  int currentPage = 1;

  /**
   * Instantiates a new sortable table.
   */
  public SortableTable() {
    topButtons = new HorizontalPanel();
    topButtons.setWidth("100%");
    this.add(topButtons);

    // refreshBtn = new NeButton();
    // refreshBtn.setText("刷新");
    // refreshBtn.setWidth("50%");
    // refreshBtn.addClickHandler(refreshClickHandler);
    // topButtons.add(refreshBtn);

    list = new ListBox();
    list.setWidth("20%");
    list.addChangeHandler(this);
    topButtons.add(list);
    topButtons.setCellHorizontalAlignment(list, HasHorizontalAlignment.ALIGN_RIGHT);

    table = new TableEx();
    table.setWidth("100%");
    this.add(table);
    this.setSpacing(4);
    pages = new HorizontalPanel();
    this.add(pages);
    this.setCellHorizontalAlignment(pages, HasHorizontalAlignment.ALIGN_CENTER);
    pages.setSpacing(5);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.event.dom.client.ChangeHandler#onChange(com.google.gwt.event.dom.client.
   * ChangeEvent)
   */
  @Override
  public void onChange(ChangeEvent event) {
    fireRequest();
  }

  /** The m provider. */
  private IDataProvider<IFieldValue> mProvider;

  /** The sorted header. */
  private List<HeaderInfo> sortedHeader;

  /** The order changed handler. */
  private ValueChangeHandler<String> orderChangedHandler = new ValueChangeHandler<String>() {

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
      OrderLabel lb = (OrderLabel) event.getSource();

      String name = lb.name;
      int order = lb.order;
      boolean find = false;

      for (HeaderInfo k : sortedHeader) {
        if (k.name.equals(name)) {
          find = true;
          k.order = order;

          if (order == HeaderInfo.ORDER_NULL) {
            sortedHeader.remove(k);
          }

          break;
        }
      }
      if (find == false) {
        if (order != HeaderInfo.ORDER_NULL) {
          HeaderInfo kv = new HeaderInfo(name);
          kv.fieldName = lb.fieldName;
          kv.order = lb.order;
          sortedHeader.add(kv);
        }
      }

      fireRequest();
    }
  };

  /** The page change handler. */
  private ClickHandler pageChangeHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      NeLabel btn = (NeLabel) event.getSource();
      currentPage = (Integer) btn.getData();
      fireRequest();
    }
  };

  /*
   * (non-Javadoc)
   * 
   * @see cn.enn.neyun.ui.client.data.IListener#invalidate()
   */
  @Override
  public void invalidate() {

    pageSize = mProvider.getPageSize();
    currentPage = mProvider.getPageNumber();

    table.removeAllRows();
    if (mProvider == null || mProvider.getTotalCount() == 0) {
      topButtons.clear();
      SimpleMessage label = new SimpleMessage("当前列表为空");
      topButtons.add(label);
      topButtons.setCellHorizontalAlignment(label, HasHorizontalAlignment.ALIGN_CENTER);
      return;
    }

    topButtons.clear();
    topButtons.add(list);
    topButtons.setCellHorizontalAlignment(list, HasHorizontalAlignment.ALIGN_RIGHT);
    List<HeaderInfo> headers = mProvider.getHeaders();

    int row = 0;
    int col = 0;
    for (int i = 0; i < headers.size(); i++) {
      HeaderInfo hi = headers.get(i);

      if (hi.colShow == true) {
        if (hi.widget != null) {
          Widget w = hi.widget;
          table.setWidget(row, col++, w);
        } else if (hi.safeUri == null
            || hi.safeUri.equals(SysResource.INSTANCE.checkTrue().getSafeUri())) {
          OrderLabel lb =
              new OrderLabel(hi.name, hi.fieldName, findOrder(hi), hi.safeUri, hi.colShow);
          lb.addValueChangeHandler(orderChangedHandler);
          table.setWidget(row, col++, lb);
        } else {
          HorizontalPanel hp = new HorizontalPanel();
          Image img = new Image();
          img.setUrl(hi.safeUri);
          img.setPixelSize(15, 15);
          hp.add(img);
          OrderLabel lb =
              new OrderLabel(hi.name, hi.fieldName, findOrder(hi), hi.safeUri, hi.colShow);
          lb.addValueChangeHandler(orderChangedHandler);
          hp.add(lb);
          table.setWidget(row, col++, hp);
        }
      } else {
        col++;
      }

    }

    row++;
    for (long i = 0; i < mProvider.getCount(); i++) {
      col = 0;
      IFieldValue rowData = mProvider.getRowData(row - 1);
      for (int j = 0; j < headers.size(); j++) {
        Object obj = rowData.getFieldValue(headers.get(j).fieldName, headers.get(j).fieldIndex);
        Widget w = mProvider.render(row - 1, col, rowData, obj);

        if (headers.get(j).colShow == true) {
          if (w == null) {
            if (obj == null) {
              table.setWidget(row, col++, new Label("-"));
            } else {
              Label lb = new Label(obj.toString());
              table.setWidget(row, col++, lb);
            }
          } else {
            table.setWidget(row, col++, w);
          }
        } else {
          col++;
        }
      }
      row++;
    }
    table.format(true);

    if (mProvider.getTotalCount() == mProvider.getCount()) {
      // 无需分页
      list.setVisible(true);
      pages.setVisible(false);
    } else {
      list.setVisible(true);
      pages.setVisible(true);
      setPageButton();
      int index = 0;
      for (index = 0; index < list.getItemCount(); index++) {
        String c = list.getValue(index);
        if (c.equals(mProvider.getPageSize() + "")) {
          list.setSelectedIndex(index);
          break;
        }
      }

    }
  }

  /**
   * Sets the page button.
   */
  private void setPageButton() {
    pages.clear();

    NeLabel prePage = new NeLabel();
    if (currentPage == 1) {
      prePage.setData(currentPage);
    } else {
      prePage.setData(currentPage - 1);
    }
    prePage.setText("<前页");
    prePage.setStyleName(SysResource.INSTANCE.getCss().pagebtnOff());
    prePage.addClickHandler(pageChangeHandler);
    pages.add(prePage);

    int pageCount = (int) Math.ceil((double) mProvider.getTotalCount() / pageSize);
    if (pageCount > 20) {
      if (currentPage < 5 || currentPage > pageCount - 4) {
        for (int i = 1; i <= 5; i++) {
          NeLabel btn = new NeLabel();
          btn.setData(i);
          btn.setText(i + "");
          btn.setStyleName(SysResource.INSTANCE.getCss().pagebtnOff());
          btn.addClickHandler(pageChangeHandler);
          pages.add(btn);

          if (currentPage == i) {
            btn.setStyleName(SysResource.INSTANCE.getCss().pagebtnOn());
          }
        }
        NeLabel omit = new NeLabel();
        omit.setText("......");
        pages.add(omit);
        for (int i = pageCount - 4; i <= pageCount; i++) {
          NeLabel btn = new NeLabel();
          btn.setData(i);
          btn.setText(i + "");
          btn.setStyleName(SysResource.INSTANCE.getCss().pagebtnOff());
          btn.addClickHandler(pageChangeHandler);
          pages.add(btn);

          if (currentPage == i) {
            btn.setStyleName(SysResource.INSTANCE.getCss().pagebtnOn());
          }
        }
      } else {

        NeLabel first = new NeLabel();
        first.setData(1);
        first.setText(1 + "");
        first.setStyleName(SysResource.INSTANCE.getCss().pagebtnOff());
        first.addClickHandler(pageChangeHandler);
        pages.add(first);

        NeLabel omit = new NeLabel();
        omit.setText("...");
        pages.add(omit);

        for (int i = currentPage - 2; i < currentPage + 3; i++) {
          NeLabel btn = new NeLabel();
          btn.setData(i);
          btn.setText(i + "");
          btn.setStyleName(SysResource.INSTANCE.getCss().pagebtnOff());
          btn.addClickHandler(pageChangeHandler);
          pages.add(btn);

          if (currentPage == i) {
            btn.setStyleName(SysResource.INSTANCE.getCss().pagebtnOn());
          }
        }

        NeLabel omit2 = new NeLabel();
        omit2.setText("...");
        pages.add(omit2);

        NeLabel last = new NeLabel();
        last.setData(pageCount);
        last.setText(pageCount + "");
        last.setStyleName(SysResource.INSTANCE.getCss().pagebtnOff());
        last.addClickHandler(pageChangeHandler);
        pages.add(last);
      }
    } else {
      for (int i = 1; i <= pageCount; i++) {

        NeLabel btn = new NeLabel();
        btn.setData(i);
        btn.setText(i + "");
        btn.setStyleName(SysResource.INSTANCE.getCss().pagebtnOff());
        btn.addClickHandler(pageChangeHandler);
        pages.add(btn);

        if (currentPage == i) {
          btn.setStyleName(SysResource.INSTANCE.getCss().pagebtnOn());
        }
      }
    }

    NeLabel afterPage = new NeLabel();
    if (currentPage == pageCount) {
      afterPage.setData(currentPage);
    } else {
      afterPage.setData(currentPage + 1);
    }
    afterPage.setText("后页>");
    afterPage.setStyleName(SysResource.INSTANCE.getCss().pagebtnOff());
    afterPage.addClickHandler(pageChangeHandler);
    pages.add(afterPage);

  }

  private int getCurrentPageSize() {
    if (list.getSelectedIndex() >= 0) {
      String v = list.getValue(list.getSelectedIndex());
      if (!v.equals(pageSize + "")) {
        currentPage = 1;
      }
      pageSize = Integer.parseInt(v);
    }
    return pageSize;
  }

  /**
   * 当用户在页面上操作表格 选择页码 ,选择页大小 都会调用此函数.
   */
  public void fireRequest() {

    getCurrentPageSize();
    req = new SearchReq(currentPage, pageSize, mProvider.getTotalCount());
    setReq(req);
    req.orders.addAll(getSqlOrder());
    ValueChangeEvent.fire(this, req);
  }

  /**
   * 获取当期用户选择的排序列.
   *
   * @return the sql order
   */
  private List<Pair<String, String>> getSqlOrder() {
    List<Pair<String, String>> orders = new ArrayList<Pair<String, String>>();
    if (sortedHeader == null) {
      return orders;
    }

    for (HeaderInfo kv : sortedHeader) {

      Pair<String, String> p = new Pair<String, String>();
      p.key = kv.fieldName;

      if (kv.order == HeaderInfo.ORDER_ASC) {
        p.value = "asc";
      } else if (kv.order == HeaderInfo.ORDER_DESC) {
        p.value = "desc";
      }
      orders.add(p);
    }
    return orders;
  }

  /**
   * Find order.
   *
   * @param hi the hi
   * @return the int
   */
  private int findOrder(HeaderInfo hi) {

    if (sortedHeader == null) {
      return HeaderInfo.ORDER_NULL;
    }

    for (HeaderInfo h : sortedHeader) {
      if (h.name.equals(hi.name)) {
        return h.order;
      }
    }
    return HeaderInfo.ORDER_NULL;
  }

  /**
   * Sets the data provider.
   *
   * @param provider the new data provider
   */
  public void setDataProvider(IDataProvider<IFieldValue> provider) {
    if (provider == null) {
      return;
    }
    if (mProvider != null) {
      mProvider.unregisterListener(this);
    }
    mProvider = provider;
    sortedHeader = new ArrayList<HeaderInfo>();
    mProvider.registerListener(this);

    list.clear();
    int[] pageSizeList = mProvider.getPageSizeList();
    for (int i = 0; i < pageSizeList.length; i++) {
      list.addItem("每页" + pageSizeList[i] + "条数据", "" + pageSizeList[i]);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt
   * .event.logical.shared.ValueChangeHandler)
   */
  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<SearchReq> handler) {
    return addHandler(handler, ValueChangeEvent.getType());
  }

}
