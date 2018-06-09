package cn.mapway.ui.client.test;

import cn.mapway.ui.client.history.HistoryManager;
import cn.mapway.ui.client.modules.test.NoSubModule;
import cn.mapway.ui.client.modules.test.TestSubModule;
import cn.mapway.ui.client.mvc.IModuleDispatcher;
import cn.mapway.ui.client.mvc.ModuleParameter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The type Test entry.
 */
public class TestEntry implements EntryPoint {


    /**
     * The D.
     */
    IModuleDispatcher d;

    @Override
    public void onModuleLoad() {


        final TestSubModule test = new TestSubModule();

        final HorizontalPanel p = new HorizontalPanel();
        final HTMLPanel con = new HTMLPanel("");

        d = test;
        HistoryManager his = HistoryManager.get(d);

        RootPanel.get().add(p);
        RootPanel.get().add(con);
        p.setSpacing(5);

        Button btnMul = new Button("多模块");
        p.add(btnMul);
        btnMul.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                con.clear();
                con.add(test);
                test.setSize("800px", "600px");
                test.initialize(null, null);
                test.switchModule("MC_DAYWO", null, true);
            }
        });

        Button btnMul1 = new Button("单模块");
        p.add(btnMul1);
        btnMul1.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

            }
        });

        Button btnMul3 = new Button("定位多磨扩的子模块");
        p.add(btnMul3);
        btnMul3.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                con.clear();
                con.add(test);

                ModuleParameter mp = new ModuleParameter();
                mp.setSubModule(NoSubModule.MODULE_CODE);
                test.initialize(null, mp);
            }
        });

        Button btnMul4 = new Button("定位多磨扩的主模块");
        p.add(btnMul4);
        btnMul4.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                con.clear();
                con.add(test);

                ModuleParameter mp = new ModuleParameter();
                mp.setSubModule(TestSubModule.MODULE_CODE);
                test.initialize(null, mp);
            }
        });
    }
}
