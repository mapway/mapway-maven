package cn.ennwifi.webframe.ui.client.modules.authority.authoritydefine;

import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.module.Pair;
import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;
import cn.mapway.ui.client.widget.common.CheckBoxEx;
import cn.mapway.ui.client.widget.common.IconItem;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TreeItem;
import com.ksyzt.gwt.client.event.MessageEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 权限树.
 *
 * @author zhangjianshe
 */
public class AuthorityTree extends cn.ennwifi.webframe.ui.client.common.LocalTree {

    /**
     * The open handler.
     */
    private OpenHandler<TreeItem> openHandler = new OpenHandler<TreeItem>() {

        @Override
        public void onOpen(OpenEvent<TreeItem> event) {
            S_RESOURCEObj g = (S_RESOURCEObj) event.getTarget().getUserObject();
            if (g != null) {
                setOpen(g.getId() + "", true);
            }
        }
    };

    /**
     * The close handler.
     */
    private CloseHandler<TreeItem> closeHandler = new CloseHandler<TreeItem>() {

        @Override
        public void onClose(CloseEvent<TreeItem> event) {
            S_RESOURCEObj g = (S_RESOURCEObj) event.getTarget().getUserObject();
            if (g != null) {
                setOpen(g.getId() + "", false);
            }
        }
    };

    /**
     * The Class ValueChangedData.
     */
    public class ValueChangedData {

        /**
         * The authority id.
         */
        public Long resourceId;

        /**
         * The checked.
         */
        public boolean checked;
    }

    /**
     * The m module.
     */
    private List<S_RESOURCEObj> mModule;

    /**
     * The root.
     */
    TreeItem root;

    /**
     * Instantiates a new authority tree.
     */
    public AuthorityTree() {
        initEvent();
        refresh();

    }

    /**
     * 缺省不显示选择框.
     */
    private boolean mEnabledCheckBox = false;

    /**
     * 数据装载完毕事件.
     */
    public static final Integer DATA_LOADED = 3000;

    /**
     * 设置是否显示checkBox.
     *
     * @param checkbox the checkbox
     */
    public void enableCheckBox(boolean checkbox) {
        mEnabledCheckBox = checkbox;
        updateView();
    }

    /**
     * Checks if is enabled check box.
     *
     * @return true, if is enabled check box
     */
    public boolean isEnabledCheckBox() {
        return mEnabledCheckBox;
    }

    /**
     * The current.
     */
    TreeItem current = null;

    /**
     * Inits the event.
     */
    private void initEvent() {

        this.addOpenHandler(openHandler);
        this.addCloseHandler(closeHandler);

        this.addSelectionHandler(new SelectionHandler<TreeItem>() {

            @Override
            public void onSelection(SelectionEvent<TreeItem> event) {
                if (event.getSelectedItem() != null) {
                    if (current != null) {
                        current.getElement().removeAttribute("s");
                    }
                    current = event.getSelectedItem();
                    current.getElement().setAttribute("s", "true");

                    S_RESOURCEObj obj = (S_RESOURCEObj) event.getSelectedItem().getUserObject();

                    MessageEvent ev = new MessageEvent(MessageEvent.ITEMCLICK, obj);
                    fireEvent(ev);

                }

            }
        });
    }

    /**
     * 加载数据.
     */
    public void refresh() {

        WebFrameProxy.get().getAllMenu(new AsyncCallback<List<S_RESOURCEObj>>() {

            @Override
            public void onSuccess(List<S_RESOURCEObj> result) {
                mModule = result;
                updateView();
                fireEvent(new MessageEvent(DATA_LOADED, null));
            }

            @Override
            public void onFailure(Throwable caught) {
                fireEvent(new MessageEvent(MessageEvent.MESSAGE, caught.getMessage()));
            }
        });
    }

    /**
     * The mapper.
     */
    HashMap<Long, TreeItem> mapper;

    /**
     * The checker mapper.
     */
    HashMap<Long, CheckBoxEx> checkerMapper;

    /**
     * The check value changed handler.
     */
    private ValueChangeHandler<Boolean> checkValueChangedHandler = new ValueChangeHandler<Boolean>() {

        @Override
        public void onValueChange(ValueChangeEvent<Boolean> event) {
            Boolean checked = event.getValue();
            CheckBoxEx box = (CheckBoxEx) event.getSource();

            S_RESOURCEObj res = (S_RESOURCEObj) box.getData();
            TreeItem treeItem = mapper.get(res.getId());
            if (checked == true) {
                // 添加所有的父节点
                TreeItem parent = treeItem.getParentItem();
                while (parent != null) {
                    CheckBoxEx w = (CheckBoxEx) parent.getWidget();
                    if (w != null) {
                        w.setValue(true);
                    }
                    parent = parent.getParentItem();
                }

                // 添加所有的子节点
                addAllChildren(treeItem);
            } else {
                // 所有子节点全部取消选择
                cancelAllChildren(treeItem);
            }

            MessageEvent ev = new MessageEvent(MessageEvent.VALUECHANGE, null);
            fireEvent(ev);
        }
    };

    /**
     * Update view.
     */
    protected void updateView() {
        if (mEnabledCheckBox) {
            checkerMapper = new HashMap<Long, CheckBoxEx>();
        }
        if (mModule != null) {
            this.clear();
            mapper = new HashMap<Long, TreeItem>();
            root = new TreeItem();
            root.setText("资源定义");
            root.setUserObject(null);

            this.addItem(root);

            for (int i = 0; i < mModule.size(); i++) {

                S_RESOURCEObj obj = mModule.get(i);
                TreeItem resourceItem = new TreeItem();

                resourceItem.setUserObject(obj);

                if (mEnabledCheckBox) {
                    CheckBoxEx checkBox = new CheckBoxEx();
                    checkBox.setStyleName("");
                    checkBox.addValueChangeHandler(checkValueChangedHandler);
                    checkBox.setData(obj);
                    checkBox.getElement().getStyle().setMargin(0, Unit.PX);
                    checkerMapper.put(obj.getId(), checkBox);
                    checkBox.setText(obj.getName());
                    resourceItem.setWidget(checkBox);

                } else {
                    IconItem ri = new IconItem();
                    ri.setIcon("", 100, 100);
                    ri.setText(obj.getName());
                    resourceItem.setWidget(ri);
                }
                mapper.put(obj.getId(), resourceItem);

                TreeItem item = findParent(obj);

                item.addItem(resourceItem);
                // 设置状态

                S_RESOURCEObj m = (S_RESOURCEObj) item.getUserObject();
                if (m != null) {
                    item.setState(isOpen(m.getId() + ""));
                }
            }
            root.setState(true);
        }
    }

    /**
     * Add all children.
     *
     * @param treeItem the tree item
     */
    protected void addAllChildren(TreeItem treeItem) {
        for (int i = 0; i < treeItem.getChildCount(); i++) {
            TreeItem item = treeItem.getChild(i);
            CheckBoxEx w = (CheckBoxEx) item.getWidget();
            if (w != null) {
                w.setValue(true);
            }
            cancelAllChildren(item);
        }

    }

    /**
     * Cancel all children.
     *
     * @param treeItem the tree item
     */
    protected void cancelAllChildren(TreeItem treeItem) {
        for (int i = 0; i < treeItem.getChildCount(); i++) {
            TreeItem item = treeItem.getChild(i);
            CheckBoxEx w = (CheckBoxEx) item.getWidget();
            if (w != null) {
                w.setValue(false);
            }
            cancelAllChildren(item);
        }
    }

    /**
     * 找到父节点
     *
     * @param obj
     * @return
     */
    private TreeItem findParent(S_RESOURCEObj obj) {
        if (obj == null) {
            return root;
        }
        if (obj.getPid() == null || obj.getPid() == 0) {
            return root;
        }
        return mapper.get(obj.getPid());
    }

    /**
     * 设置权限点的选中状态.
     *
     * @param resourceId the authorityid
     * @param checked    the checked
     */
    public void enableAuthorityChecked(Long resourceId, boolean checked) {
        if (checkerMapper != null) {
            CheckBoxEx box = checkerMapper.get(resourceId);
            if (box != null) {
                box.setValue(checked);
            }
        }
    }

    /**
     * 设置CheckBox的状态.
     *
     * @param b the b
     */
    public void resetChecked(boolean b) {
        if (checkerMapper != null) {
            for (Long key : checkerMapper.keySet()) {
                CheckBoxEx box = checkerMapper.get(key);
                box.setValue(b);
            }
        }
    }

    /**
     * 设置CheckBox是否可以点击.
     *
     * @param b the b
     */
    public void resetEnabled(boolean b) {
        if (checkerMapper != null) {
            for (Long key : checkerMapper.keySet()) {
                CheckBoxEx box = checkerMapper.get(key);
                box.setEnabled(b);
            }
        }
    }

    /**
     * 返回用户打钩的资源
     *
     * @return checked item
     */
    public List<Pair<Long, String>> getCheckedItem() {

        List<Pair<Long, String>> r = new ArrayList<Pair<Long, String>>();
        if (checkerMapper != null) {
            for (Long key : checkerMapper.keySet()) {
                CheckBoxEx box = checkerMapper.get(key);
                if (box.getValue()) {
                    Pair<Long, String> p = new Pair<Long, String>();
                    p.key = key;
                    p.value = box.getText();
                    r.add(p);
                }
            }
        }
        return r;
    }

}
