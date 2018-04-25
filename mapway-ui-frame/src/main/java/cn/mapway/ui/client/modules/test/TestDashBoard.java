package cn.mapway.ui.client.modules.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.widget.common.ButtonEx;
import cn.mapway.ui.client.widget.common.CueLabelEx;
import cn.mapway.ui.client.widget.common.DangerButton;
import cn.mapway.ui.client.widget.common.DashBoardItem;
import cn.mapway.ui.client.widget.common.PrimaryButton;

/**
 * The type Test dash board.
 */
@ModuleMarker(value = TestDashBoard.MODULE_CODE, group = "/测试", name = "主页面测试" , icon="avatar11.png")
public class TestDashBoard extends AbstractModule {

	private static TestDashBoardUiBinder uiBinder = GWT.create(TestDashBoardUiBinder.class);

    /**
     * The constant MODULE_CODE.
     */
    public static final String MODULE_CODE = "dashBoard";

    /**
     * The interface Test dash board ui binder.
     */
    interface TestDashBoardUiBinder extends UiBinder<Widget, TestDashBoard> {
	}

    /**
     * Instantiates a new Test dash board.
     */
    public TestDashBoard() {
		initModuleWidget(uiBinder.createAndBindUi(this));
		
		init1();
		
		init2();
		
		init3();
	}

    /**
     * The One.
     */
    @UiField
	HTMLPanel one;

    /**
     * The Two.
     */
    @UiField
	HTMLPanel two;

    /**
     * The T 1.
     */
    @UiField
	HTMLPanel t1;

    /**
     * The T 2.
     */
    @UiField
	HTMLPanel t2;

    /**
     * The T 3.
     */
    @UiField
	HTMLPanel t3;

    /**
     * The Item.
     */
    DashBoardItem item;
	
	@Override
	public String getModuleCode() {
		return MODULE_CODE;
	}

    /**
     * Init 1.
     */
    void init1(){
		item = new DashBoardItem();
		DangerButton btn = new DangerButton();
		btn.setText("我是按钮");
		item.setTitle("测试item");
		item.setLink("查看更多>>");
		item.addCenter(btn);
		item.addStyleName("gwtEx-DashBoard-item-1");
		one.add(item);
		
		item = new DashBoardItem();
		PrimaryButton pb = new PrimaryButton();
		pb.setText("测试用例");
		item.setTitle("测试按钮");
		item.addCenter(pb);
		item.addStyleName("gwtEx-DashBoard-item-1");
		one.add(item);
		
		item = new DashBoardItem();
		ButtonEx ex = new ButtonEx();
		ex.setText("按钮");
		item.setTitle("第三个框框");
		item.addCenter(ex);
		item.addStyleName("gwtEx-DashBoard-item-1");
		one.add(item);
		
		item = new DashBoardItem();
		CueLabelEx cue = new CueLabelEx();
		cue.setText("提示：这个会不会换行呢？");
		item.setTitle("第四个框框");
		item.addCenter(cue);
		item.addStyleName("gwtEx-DashBoard-item-1");
		one.add(item);
	}

    /**
     * Init 2.
     */
    void init2(){
		item = new DashBoardItem();
		item.setTitle("换行啦！！");
		item.setLink("查看更多>>");
		item.addStyleName("gwtEx-DashBoard-item-2");
		two.add(item);
		
		item = new DashBoardItem();
		item.setTitle("来个大的");
		item.addStyleName("gwtEx-DashBoard-item-2");
		item.getCenter().setHeight("500px");
		two.add(item);
		
	}

    /**
     * Init 3.
     */
    void init3(){
		
		item = new DashBoardItem();
		item.setTitle("第一个");
		t1.add(item);
		
		item = new DashBoardItem();
		item.setTitle("第二个");
		t1.add(item);
		
		item = new DashBoardItem();
		item.setTitle("第二排第一个");
		t2.add(item);
		
		item = new DashBoardItem();
		item.setTitle("第二排第二个");
		t2.add(item);
		
		item = new DashBoardItem();
		item.setTitle("来个长的");
		item.setHeight("546px");
		t3.add(item);
	}

}
