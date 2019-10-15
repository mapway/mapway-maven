package cn.mapway.document.parser;

public class TestY {


    String sec_xinke="321312";
    public static void main(String[] args) {
        String data="299792458ZHOMECN";
        String data1="6oojtzsfRSqDT74M";
        printdata(data);
        printdata(data1);
    }

    private static void printdata(String data) {
        for(int i=0;i<data.length();i++)
        {
            char c=data.charAt(i);
            System.out.print(String.format("%H",c));
        }
        System.out.println();
    }
}
