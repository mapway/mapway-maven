package com.ksyzt.gwt.client.test;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.ksyzt.gwt.client.ui.richeditor.EditOption;
import com.ksyzt.gwt.client.ui.richeditor.XhEditor;

// TODO: Auto-generated Javadoc
/**
 * The Class EntryTest.
 */
public class EntryTest implements EntryPoint {

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
   */
  @Override
  public void onModuleLoad() {

    testTimeRange();
  }

  private void testTimeRange() {

  }

  /**
   * 
   */
  private void testEditor() {
    EditOption option = EditOption.create();
    // option.width("100%");
    // option.height("200px");
    option.setSkin(EditOption.SKIN_NOSTYLE);
    option.tools(EditOption.TS_SIMPLE);
    option.setLinkExt(EditOption.LINK_EXT);
    String upload_path = GWT.getModuleBaseURL() + "../gwtfileuploader";
    String editRoot = GWT.getModuleBaseURL() + "../js/editor/";
    option.setUploadImagePath(upload_path);
    option.setUploadLinkExt(upload_path);
    option.setEditorRoot(editRoot);
    option.setUrlBase(GWT.getModuleBaseURL() + "../");
    option.enableHtml5Upload(false);
    XhEditor editor = new XhEditor();
    editor.setWidth("100%");
    editor.setHeight("400px");
    RootPanel.get("editor").add(editor);
    editor.initControl(option);
    editor.setSource("<b> wangmingli</b>");
  }

}
