package cn.mapway.document.ui.client.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import cn.mapway.document.ui.client.component.Clients;
import cn.mapway.document.ui.client.module.Entry;
import cn.mapway.document.ui.client.module.ObjectInfo;

/**
 * The type Input parameter panel.
 */
public class InputParameterPanel extends Composite {

  private static InputParameterPanelUiBinder uiBinder = GWT
      .create(InputParameterPanelUiBinder.class);

    /**
     * The interface Input parameter panel ui binder.
     */
    interface InputParameterPanelUiBinder extends UiBinder<Widget, InputParameterPanel> {
  }

    /**
     * Instantiates a new Input parameter panel.
     */
    public InputParameterPanel() {
    initWidget(uiBinder.createAndBindUi(this));
  }

    /**
     * The Para path.
     */
    @UiField
  ObjectsInfoPanel paraPath;
    /**
     * The Para query.
     */
    @UiField
  ObjectsInfoPanel paraQuery;
    /**
     * The Para body.
     */
    @UiField
  ParameterPanel paraBody;

    /**
     * The Lb url.
     */
    @UiField
  Label lbUrl;
    /**
     * The Lb none.
     */
    @UiField
  Label lbNone;

    /**
     * Parse entry.
     *
     * @param entry the entry
     */
    public void parseEntry(Entry entry) {

    boolean show = true;
    boolean urlShow = false;
    String url = entry.url();

    if (entry.pathParas().length() == 0) {
      paraPath.setVisible(false);
    } else {
      paraPath.setVisible(true);
      paraPath.parse(entry.pathParas(), "路径参数", "");
      show = false;
      urlShow = true;

      for (int i = 0; i < entry.pathParas().length(); i++) {
        ObjectInfo oi = entry.pathParas().get(i);
        url = url.replaceAll("\\{" + oi.name() + "\\}", oi.example() == null ? "" : oi.example());
      }
    }

    if (entry.queryParas().length() == 0) {
      paraQuery.setVisible(false);
    } else {
      paraQuery.setVisible(true);
      paraQuery.parse(entry.queryParas(), "查询参数", "");
      show = false;
      urlShow = true;

      url = url + "?";
      for (int i = 0; i < entry.queryParas().length(); i++) {
        ObjectInfo oi = entry.queryParas().get(i);
        if (i > 0) {
          url += "&";
        }
        url = url + oi.name() + "=" + (oi.example() == null ? "" : oi.example());
      }

    }



    lbUrl.setText("URL例子:" + Clients.getHostPort() + url);
    lbUrl.setVisible(urlShow);

    if (entry.input().length() == 0) {
      paraBody.setVisible(false);
    } else {
      paraBody.setVisible(true);
      ObjectInfo obj = entry.input().get(0);

      paraBody.parse(obj, "POST数据");
      show = false;
    }

    lbNone.setVisible(show);

  }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

  }

}
