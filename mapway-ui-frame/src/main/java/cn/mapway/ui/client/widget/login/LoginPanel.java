package cn.mapway.ui.client.widget.login;

import cn.mapway.ui.client.event.HasMapwayHandlers;
import cn.mapway.ui.client.event.MapwayEvent;
import cn.mapway.ui.client.event.MapwayHandler;
import cn.mapway.ui.client.widget.MapwayButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * 登录UI.
 * @author zhangjianshe
 *
 */
public class LoginPanel extends Composite implements HasMapwayHandlers<LoginData> {

	private static LoginPanelUiBinder uiBinder = GWT
			.create(LoginPanelUiBinder.class);

	interface LoginPanelUiBinder extends UiBinder<Widget, LoginPanel> {
	}

	public LoginPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public HandlerRegistration addMapwayHandler(MapwayHandler<LoginData> handler) {
		return addHandler(handler, MapwayEvent.getType());
	}
	
	@UiField
	MapwayButton btnSubmit;
	@UiField
	TextBox txtName;
	@UiField
	PasswordTextBox txtPassword;
	
	
	@UiHandler("btnSubmit")
	void onSubmitClicked(ClickEvent event)
	{
		LoginData data=new LoginData();
		data.name=txtName.getValue();
		data.pwd=txtPassword.getValue();
		MapwayEvent.fire(this, 0, data);
	}
}
