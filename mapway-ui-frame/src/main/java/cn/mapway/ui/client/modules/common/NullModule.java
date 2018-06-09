package cn.mapway.ui.client.modules.common;

import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Null module.
 */
@ModuleMarker(value = NullModule.MODULE_CODE, group = "/系统", name = "空模块")
public class NullModule extends BaseAbstractModule {

    /**
     * The constant MODULE_CODE.
     */
    public final static String MODULE_CODE = "NULL_MODULE";

    @Override
    public String getModuleCode() {
        return MODULE_CODE;
    }

    private static NullModuleUiBinder uiBinder = GWT.create(NullModuleUiBinder.class);

    /**
     * The interface Null module ui binder.
     */
    interface NullModuleUiBinder extends UiBinder<Widget, NullModule> {
    }

    /**
     * Instantiates a new Null module.
     */
    public NullModule() {
        initModuleWidget(uiBinder.createAndBindUi(this));
    }

}
