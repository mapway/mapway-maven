package cn.ennwifi.webframe.ui.client.modules.dashboard;

import java.util.List;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.modules.AbstractModuleWithEvent;
import cn.ennwifi.webframe.ui.client.resource.SysResource;
import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.module.DashboardData;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

@ModuleMarker(value = DashBoardModule.MODULE_CODE, name = "看板")
public class DashBoardModule extends AbstractModuleWithEvent {

    public final static String MODULE_CODE = "MC_DASHBOARD";
    private static DashBoardModuleUiBinder uiBinder = GWT.create(DashBoardModuleUiBinder.class);

    interface DashBoardModuleUiBinder extends UiBinder<Widget, DashBoardModule> {
    }

    protected List<DashboardData> mData;

    @Override
    public String getModuleCode() {
        return MODULE_CODE;
    }

    public DashBoardModule() {
        initModuleWidget(uiBinder.createAndBindUi(this));
        loading.setUrl(SysResource.INSTANCE.loadding().getSafeUri());
        lbVersion.setText(ClientContext.getContext().getConfigure().getCompileInformation());
    }

    @UiField
    Image loading;

    @Override
    public boolean initialize(IModule parentModule, ModuleParameter parameters) {
        boolean b = super.initialize(parentModule, parameters);

        return b;
    }

    protected void refreshView() {
        root.clear();

        for (DashboardData d : mData) {
            DashItem item = new DashItem();
            item.render(d);
            root.add(item);
        }
    }

    @UiField
    HTMLPanel root;

    @UiField
    Label lbVersion;

}
