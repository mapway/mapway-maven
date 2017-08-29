package cn.mapway.ui.client.modules.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import cn.mapway.ui.client.widget.common.DialogBoxEx;

public class TestDialogBox extends DialogBoxEx {

	private static TestDialogBoxUiBinder uiBinder = GWT.create(TestDialogBoxUiBinder.class);

	interface TestDialogBoxUiBinder extends UiBinder<Widget, TestDialogBox> {
	}

	public TestDialogBox() {
		setWidget(uiBinder.createAndBindUi(this));
		setText("测试弹出框");
	}

}
