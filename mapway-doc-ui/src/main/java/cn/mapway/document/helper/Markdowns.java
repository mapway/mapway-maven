package cn.mapway.document.helper;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

/**
 * markdown handler
 */
public class Markdowns {

    private static Markdowns INSTANCE;

    protected Markdowns() {

    }

    public final static Markdowns get() {
        if (INSTANCE == null) {
            INSTANCE = new Markdowns();
        }
        return INSTANCE;
    }

    private Parser parser;
    private HtmlRenderer renderer;
    private List<Extension> extensions;

    public synchronized Parser getParser() {
        if (parser == null) {
            parser = Parser.builder().extensions(getExtensions()).build();
        }
        return parser;
    }

    public synchronized final HtmlRenderer getHtmlRender() {
        if (renderer == null) {
            renderer = HtmlRenderer.builder().extensions(getExtensions()).build();
        }
        return renderer;
    }

    public synchronized final List<Extension> getExtensions() {
        if (extensions == null) {
            extensions = Arrays.asList(TablesExtension.create());
        }
        return extensions;
    }

    /**
     * 转换器
     *
     * @param desc
     * @return
     */
    public String translate(String desc) {
        Node node = getParser().parse(desc);
        String html = getHtmlRender().render(node);
        return html;
    }
}
