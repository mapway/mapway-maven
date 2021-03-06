package cn.ennwifi.webframe.ui.client.main.menu;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 主菜单.
 *
 * @author zhangjianshe
 */
public class MainMenu extends MenuBar {

    /**
     * The Class Node.
     */
    public static class Node {

        /**
         * The data.
         */
        S_RESOURCEObj data;

        /**
         * The name.
         */
        String name;

        /**
         * The children.
         */
        List<Node> children;

        /**
         * Instantiates a new node.
         */
        public Node() {
            children = new ArrayList<MainMenu.Node>();
        }

        /**
         * To string.
         *
         * @param indent the indent
         * @return the string
         */
        public String toString(int indent) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < indent; i++) {
                sb.append("\t");
            }
            sb.append(name).append("\r\n");
            for (int i = 0; i < children.size(); i++) {
                sb.append(children.get(i).toString(indent + 1));
            }
            return sb.toString();
        }
    }

    /**
     * The m root.
     */
    Node mRoot;

    /**
     * Gets the root.
     *
     * @return the root
     */
    public Node getRoot() {
        return mRoot;
    }

    /**
     * Instantiates a new main menu.
     */
    public MainMenu() {
        mRoot = new Node();
        mRoot.data = null;
        mRoot.name = "/";
    }

    /**
     * 创建主菜单.
     *
     * @param aus the aus
     */
    public void build(List<S_RESOURCEObj> aus) {
        this.clearItems();
        Node root = parse(aus);
        if (root.children.size() == 0) {
            return;
        }
        root = root.children.get(0);
        for (int i = 0; i < root.children.size(); i++) {
            Node n = root.children.get(i);
            MenuItem item;
            item = genMenuItem(n);
            this.addItem(item);
        }
    }

    /**
     * Builds the sub menu.
     *
     * @param n the n
     * @return the menu item
     */
    private MenuItem buildSubMenu(Node n) {
        MenuBar menu = new MenuBar(true);
        SafeHtml text;

        if (n.data.getIcon() != null && n.data.getIcon().length() > 0) {
            String icon = ClientContext.getContext().getConfigure().getImagePrefix() + n.data.getIcon();
            text = MenuItemTemplate.TEMPLATE.textWithIcon(icon, n.name);
        } else {
            text = MenuItemTemplate.TEMPLATE.text(n.name);
        }

        MenuItem item = new MenuItem(text, menu);

        for (int i = 0; i < n.children.size(); i++) {
            MenuItem subItem;
            Node node = n.children.get(i);
            subItem = genMenuItem(node);
            menu.addItem(subItem);
        }

        return item;
    }

    private MenuItem genMenuItem(Node node) {
        MenuItem subItem;
        if (node.children.size() > 0) {
            subItem = buildSubMenu(node);
        } else {
            if (node.data.getIcon() != null && node.data.getIcon().length() > 0) {
                String icon = ClientContext.getContext().getConfigure().getImagePrefix() + node.data.getIcon();
                SafeHtml text = MenuItemTemplate.TEMPLATE.textWithIcon(icon, node.name);
                subItem = new MenuItem(text);
                subItem.setScheduledCommand(new MenuItemCommand(node.data));
            } else {
                SafeHtml text = MenuItemTemplate.TEMPLATE.text(node.name);
                subItem = new MenuItem(text);
                subItem.setScheduledCommand(new MenuItemCommand(node.data));
            }
        }
        return subItem;
    }

    /**
     * The mapper.
     */
    java.util.HashMap<Long, Node> mapper;

    /**
     * Parses the.
     *
     * @param aus the aus
     * @return the node
     */
    private Node parse(List<S_RESOURCEObj> aus) {
        mapper = new HashMap<Long, Node>();
        mRoot.children.clear();
        for (S_RESOURCEObj a : aus) {
            Node parent = findParentNode(a);
            Node n = new Node();
            n.name = a.getName();
            n.data = a;
            mapper.put(a.getId(), n);
            parent.children.add(n);
        }
        return mRoot;
    }

    /**
     * Find node.
     *
     * @param m the path
     * @return the node
     */
    private Node findParentNode(S_RESOURCEObj m) {
        if (m == null || m.getPid() == null || m.getPid() == 0) {
            return mRoot;
        }
        Node n = mapper.get(m.getPid());
        if (n == null) {
            return mRoot;
        } else {
            return n;
        }
    }

}
