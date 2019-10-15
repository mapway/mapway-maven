package cn.mapway.doc.parser.module;

/**
 * docklet
 */
public interface IDoclet {
    void setName(String name);
    void addComment(String comment);
    void setGroup(String group);
    void setAuthor(String author);
    void addTag(String tag);
    void addRef(String t);
    void addStyle(String t);
    void setDate(String date);
}
