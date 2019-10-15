public class Security {

    public static void main(String[] args) {
        Security security = new Security();
        security.yunce();
    }


    /**
     * qwertasdfg  openkey
     * 123456  writekey
     */
    private void yunce() {
        String UID = "ZH000000";
        String PIN = "30000000";
        String RootKey = "zhome.cn20190218";

        printData("UID", UID);
        printData("PIN", PIN);
        printData("ROOTKey", RootKey);
    }

    private void printData(String label, String uid) {
        System.out.printf("%s\t:",label);
        byte[] bytes = uid.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.printf("%H", bytes[i]);
        }
        System.out.printf("\t%d",bytes.length);
        System.out.println();
    }
}
