package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.widget.common.ButtonEx;
import cn.mapway.ui.client.widget.common.DangerButton;
import cn.mapway.ui.client.widget.common.GridEx;
import cn.mapway.ui.client.widget.common.ImageEx;
import cn.mapway.ui.client.widget.common.ListBoxEx;
import cn.mapway.ui.client.widget.common.NumberTextBox;
import cn.mapway.ui.client.widget.common.PrimaryButton;
import cn.mapway.ui.client.widget.common.RadioButtonEx;
import cn.mapway.ui.client.widget.common.TabBarEx;
import cn.mapway.ui.client.widget.common.TableEx;
import cn.mapway.ui.client.widget.common.TextBoxEx;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

@ModuleMarker(value = ItemTest.MODULE_CODE, name = "組件測試", icon = "avatar11.png")
public class ItemTest extends AbstractModule {

  private static ItemTestUiBinder uiBinder = GWT.create(ItemTestUiBinder.class);

  public static final String MODULE_CODE = "ITEM_TEST";

  interface ItemTestUiBinder extends UiBinder<Widget, ItemTest> {
  }

  public ItemTest() {
    initModuleWidget(uiBinder.createAndBindUi(this));

    shuru.setPlaceHolder("请输入你的梦想");
    btn.setData("理想的状态");
    btn.addClickHandler(btnHandler);

    primaryBtn.setData("草长莺飞");
    primaryBtn.addClickHandler(primaryBtnHandler);

    listBox.clear();
    listBox.addItem("小红", "1");
    listBox.addItem("小绿", "2");
    listBox.addItem("小蓝", "3");
    listBox.addItem("小黄", "4");
    listBox.addItem("小紫", "5");
    listBox.addItem("小灰", "6");

    image.setUrl("../images/timg.jpg");

    initTable();

    initTab();

    initGrid();

  }

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }


  @UiField
  TextBoxEx shuru;

  @UiField
  ButtonEx btn;

  @UiField
  PrimaryButton primaryBtn;

  @UiField
  HTMLPanel checkList;

  @UiField
  ListBoxEx listBox;

  @UiField
  HTMLPanel radioList;

  @UiField
  TableEx table;

  @UiField
  NumberTextBox numText;

  @UiField
  TabBarEx tab;

  @UiField
  HTMLPanel tabContent;

  @UiField
  DangerButton dangerBtn;

  TestDialogBox dialog;

  @UiField
  GridEx grid;

  @UiField
  ImageEx image;

  @UiHandler("dangerBtn")
  void onClick(ClickEvent event) {
    dialog = new TestDialogBox();
    dialog.setGlassEnabled(true);
    dialog.setAutoHideEnabled(true);
    dialog.setModal(true);
    dialog.center();
  }


  ClickHandler btnHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      // int count = checkList.getWidgetCount();
      // String text = "";
      // for (int i = 0; i < count; i++) {
      // CheckBoxEx ex = (CheckBoxEx)checkList.getWidget(i);
      // Boolean value = ex.getValue();
      // if( value == true ){
      // text += ex.getText()+",";
      // }
      // }
      // Window.alert(text);

      boolean flag = numText.checkNum();
      GWT.log(flag + "");
    }
  };

  ClickHandler primaryBtnHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      // ButtonEx ex = (ButtonEx)event.getSource();
      // String data = (String)ex.getData();
      // Window.alert(data);

      int count = radioList.getWidgetCount();
      for (int i = 0; i < count; i++) {
        RadioButtonEx ex = (RadioButtonEx) radioList.getWidget(i);
        if (ex.getValue() == true) {
          Window.alert(ex.getText());
        }
      }
    }
  };

  ClickHandler checkHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      // CheckBoxEx ex = (CheckBoxEx)event.getSource();
      // Boolean value = ex.getValue();
      // if(value == true ){
      // Window.alert(ex.getText());
      // }
    }

  };

  void initTable() {
    table.removeAllRows();
    int column = 0;
    int row = 0;
    table.setText(row, column++, "ID");
    table.setText(row, column++, "用户");
    row++;
    column = 0;
    table.setText(row, column++, "1");
    table.setText(row, column++, "yolanda");
    row++;
    column = 0;
    table.setText(row, column++, "2");
    table.setText(row, column++, "Joanna");

    table.format(true);
  }

  void initTab() {
    tab.addTab("tab1");
    tab.addTab("tab2");
    tab.addTab("tab3");
    tab.addSelectionHandler(selectedHandler);
    tab.selectTab(0);
  }

  SelectionHandler<Integer> selectedHandler = new SelectionHandler<Integer>() {

    @Override
    public void onSelection(SelectionEvent<Integer> event) {
      tabContent.clear();
      if (event.getSelectedItem() == 0) {
        PrimaryButton btn = new PrimaryButton();
        btn.setText("测试tab1");
        tabContent.add(btn);
      }
    }
  };

  void initGrid() {
    // int row = 0;
    // int cloumn = 0;
    // grid.setText(row, cloumn++, "测试id");
    // grid.setText(row, cloumn++, "测试用户名");
    // grid.setText(row, cloumn++, "测试密码");
    //
    // grid.setText(row, cloumn++, "1");
    // grid.setText(row, cloumn++, "huanglina");
    // grid.setText(row, cloumn++, "123456");
    //
    // grid.setText(row, cloumn++, "2");
    // grid.setText(row, cloumn++, "黄丽娜");
    // grid.setText(row, cloumn++, "123456");
  }

}
