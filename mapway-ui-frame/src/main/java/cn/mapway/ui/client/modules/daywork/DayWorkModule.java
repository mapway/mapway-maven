package cn.mapway.ui.client.modules.daywork;

import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Day work module.
 */
@ModuleMarker(value = DayWorkModule.MODULE_CODE, group = "/测试", name = "空模块", summary = "--==--")
public class DayWorkModule extends BaseAbstractModule {

    /**
     * The constant MODULE_CODE.
     */
    public static final String MODULE_CODE = "MC_DAYWORK";

    @Override
    public String getModuleCode() {
        return MODULE_CODE;
    }

    private static DayWorkModuleUiBinder uiBinder = GWT.create(DayWorkModuleUiBinder.class);

    /**
     * The interface Day work module ui binder.
     */
    interface DayWorkModuleUiBinder extends UiBinder<Widget, DayWorkModule> {
    }

    /**
     * Instantiates a new Day work module.
     */
    public DayWorkModule() {
        initWidget(uiBinder.createAndBindUi(this));

    }

    @Override
    public boolean initialize(IModule parentModule, ModuleParameter parameters) {
        boolean b = super.initialize(parentModule, parameters);

        return b;
    }

    @Override
    public Widget getRootWidget() {
        return this;
    }

}
