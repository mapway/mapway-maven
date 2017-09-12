package com.mapway;

import java.util.LinkedList;
import java.util.List;

import org.nutz.lang.Strings;
import org.nutz.lang.random.R;

/**
 * 打印表格
 * 
 * @author zhangjianshe
 *
 */
public class TableBuilder {

  public static String[][] UTF8TABLE = { {"┌", "─", "┬", "─", "┐"}, {"│", " ", "┼", " ", "│"},
      {"├", "─", "┼", "─", "┤"}, {"│", " ", "┼", " ", "│"}, {"└", "─", "┴", "─", "┘"}};
  public static String[][] ASICIITABLE = { {"+", "-", "+", "-", "+"}, {"+", "-", "+", "-", "+"},
      {"+", "-", "+", "-", "+"}, {"+", "-", "+", "-", "+"}, {"+", "-", "+", "-", "+"}};

  public String[][] TABLE = ASICIITABLE;

  int padding = 1;

  public void setPadding(int padding) {
    this.padding = padding;
  }

  List<String[]> rows = new LinkedList<String[]>();
  int columns = 0;

  /**
   * 添加一行
   * 
   * @param cols
   */
  public void addRow(String... cols) {
    rows.add(cols);
  }

  /**
   * 列的宽度
   * 
   * @return
   */
  private int[] colWidths() {

    for (String[] row : rows)
      columns = Math.max(columns, row.length);

    // 获取最大的列数

    int[] widths = new int[columns];

    for (String[] row : rows) {
      for (int colNum = 0; colNum < row.length; colNum++) {
        widths[colNum] = Math.max(widths[colNum], length((row[colNum])));
      }
    }

    return widths;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder();

    int[] colWidths = colWidths();

    // 输出头部
    printTopLine(buf, colWidths);


    for (int index = 0; index < rows.size(); index++) {
      String[] row = rows.get(index);
      for (int colNum = 0; colNum < row.length; colNum++) {
        if (colNum == 0) {
          buf.append(TABLE[3][0]);
        }

        int colwidth = colWidths[colNum];
        buf.append(Strings.dup(' ', padding));
        buf.append(row[colNum]);
        buf.append(Strings.dup(' ', colwidth - length(row[colNum])));
        buf.append(Strings.dup(' ', padding));
        if (colNum == colWidths.length - 1) {
          buf.append('│');
        } else {
          buf.append('│');
        }
      }
      buf.append('\n');

      if (index == rows.size() - 1) {
        printBottomLine(buf, colWidths, row);
      } else {
        printMiddleLine(buf, colWidths, row);

      }

    }

    return buf.toString();
  }

  private void printTopLine(StringBuilder buf, int[] colWidths) {
    for (int colNum = 0; colNum < colWidths.length; colNum++) {
      if (colNum == 0) {
        buf.append(TABLE[0][0]);
      }
      int colwidth = colWidths[colNum];
      buf.append(Strings.dup(TABLE[0][1], colwidth + padding * 2));
      if (colNum == colWidths.length - 1) {
        buf.append(TABLE[0][4]);
      } else {
        buf.append(TABLE[0][2]);
      }
    }
    buf.append('\n');
  }

  private void printMiddleLine(StringBuilder buf, int[] colWidths, String[] row) {
    for (int colNum = 0; colNum < colWidths.length; colNum++) {
      if (colNum == 0) {
        buf.append(TABLE[1][0]);
      }

      int colwidth = colWidths[colNum];

      buf.append(Strings.dup(TABLE[1][1], colwidth + padding * 2));

      if (colNum == colWidths.length - 1) {
        buf.append(TABLE[1][2]);
      } else {
        buf.append(TABLE[1][1]);
      }

    }
    buf.append('\n');
  }

  private void printBottomLine(StringBuilder buf, int[] colWidths, String[] row) {
    for (int colNum = 0; colNum < colWidths.length; colNum++) {
      if (colNum == 0) {
        buf.append(TABLE[2][0]);
      }

      int colwidth = colWidths[colNum];

      buf.append(Strings.dup(TABLE[2][1], colwidth + padding * 2));

      if (colNum == colWidths.length - 1) {
        buf.append(TABLE[1][2]);
      } else {

        buf.append(TABLE[2][2]);
      }
    }
    buf.append('\n');
  }

  public static boolean isLetter(char c) {
    int k = 0x80;
    return c / k == 0 ? true : false;
  }

  /**
   * 判断字符串是否为空
   * 
   * @param str
   * @return
   */
  public static boolean isNull(String str) {
    if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
   * 
   * @param String s 需要得到长度的字符串
   * @return int 得到的字符串长度
   */
  public static int length(String s) {
    if (s == null)
      return 0;
    char[] c = s.toCharArray();
    int len = 0;
    for (int i = 0; i < c.length; i++) {
      len++;
      if (!isLetter(c[i])) {
        len++;
      }
    }
    return len;
  }


  /**
   * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
   * 
   * @param String s 需要得到长度的字符串
   * @return int 得到的字符串长度
   */
  public static double getLength(String s) {
    double valueLength = 0;
    String chinese = "[\u4e00-\u9fa5]";
    // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
    for (int i = 0; i < s.length(); i++) {
      // 获取一个字符
      String temp = s.substring(i, i + 1);
      // 判断是否为中文字符
      if (temp.matches(chinese)) {
        // 中文字符长度为1
        valueLength += 1;
      } else {
        // 其他字符长度为0.5
        valueLength += 0.5;
      }
    }
    // 进位取整
    return Math.ceil(valueLength);
  }

  public static void main(String[] args) {
    TableBuilder tb = new TableBuilder();
    tb.addRow("title", "url", "special");
    for (int i = 0; i < 10; i++) {
      tb.addRow(R.sg(4, 25).next(), "http://www.baidu.com/" + i);
    }
    tb.addRow("", "", "Hello", "touch");
    System.out.println(tb);
  }
}
