package cn.ennwifi.webframe.ui.client.modules.meta;



import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;
import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * The type Meta data module.
 */
@ModuleMarker(value = MetaDataModule.MODEULE_CODE, group = "/系统",name = "元数据管理", icon = "meta.png")
public class MetaDataModule extends AbstractModule {

    /**
     * The constant MODEULE_CODE.
     */
    public final static String MODEULE_CODE = "MC_META_DATA";

  @Override
  public String getModuleCode() {
    return MODEULE_CODE;
  }

  private static MetaDataModuleUiBinder uiBinder = GWT.create(MetaDataModuleUiBinder.class);

    /**
     * The interface Meta data module ui binder.
     */
    interface MetaDataModuleUiBinder extends UiBinder<Widget, MetaDataModule> {
  }

  private SelectionHandler<TreeItem> metaSelectHandler = new SelectionHandler<TreeItem>() {

    @Override
    public void onSelection(SelectionEvent<TreeItem> event) {
      S_METAObj meta = (S_METAObj) event.getSelectedItem().getUserObject();
      if (meta != null) {
        editor.editMeta(meta);
        treeToUI(event.getSelectedItem());
      } else {
        editor.createRoot();
        treeToUI(event.getSelectedItem());
      }
    }
  };
  private MessageHandler editorHandler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == MessageEvent.REFRESH) {
        tree.loadData();
      }
    }
  };

  @Override
  public boolean initialize(cn.mapway.ui.client.mvc.IModule parentModule,
      cn.mapway.ui.client.mvc.ModuleParameter parameters) {
    boolean b = super.initialize(parentModule, parameters);
    tree.loadData();
    updateTools(editor.tools);
    return b;
  };


    /**
     * Tree to ui.
     *
     * @param selectedItem the selected item
     */
    protected void treeToUI(TreeItem selectedItem) {
    tbl.removeAllRows();
    int count = selectedItem.getChildCount();
    for (int i = 0; i < count; i++) {
      TreeItem item = selectedItem.getChild(i);
      S_METAObj meta = (S_METAObj) item.getUserObject();
      tbl.setText(i, 0, meta.getName());
      tbl.setText(i, 1, meta.getCode());
    }
  }


    /**
     * Instantiates a new Meta data module.
     */
    public MetaDataModule() {
    initModuleWidget(uiBinder.createAndBindUi(this));
    tree.addSelectionHandler(metaSelectHandler);
    editor.addMessageHandler(editorHandler);
  }

  @Override
  public Widget getRootWidget() {
    return this;
  }

    /**
     * The Editor.
     */
    @UiField
  EditMeta editor;
    /**
     * The Tree.
     */
    @UiField
  MetaTree tree;

    /**
     * The Tbl.
     */
    @UiField
  FlexTable tbl;

}
