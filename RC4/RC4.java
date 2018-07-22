public class RC4 {
    private static int[] c;
    private static int[] k;
    private static int[] key;
    private static int[] content;
    private static int swapI = 0;
    private static int swapJ = 0;
    private static int max;

    public static String start(String key, String content){
        max = Math.max(content.length(),key.length());
        c = new int[max];
        k = new int[max];

        RC4.key = new int[max];
        RC4.content = new int[max];

        init(key, content);
        generates();
        preparation();

        return calc();
    }

    private static String calc(){
        StringBuilder content = new StringBuilder();
        int symbol;

        for (int i = 0; i < RC4.key.length; i++) {
            symbol = RC4.key[i] ^ RC4.content[i];
            content.append((char)symbol);
        }

        return content.toString();
    }

    private static void init(String key, String content){
        for (int i = 0; i < max; i++) {
            c[i] = i;
            k[i] = key.charAt(i % key.length());
            if (content.length() > i) RC4.content[i] = content.charAt(i);
        }
    }

    private static void generates(){
        int j = 0;
        for (int i = 0; i < max; i++) {
            j = (j + c[i] + k[i]) % max;
            swapI = i;
            swapJ = j;
            c[swapJ] = swapI;
            c[swapI] = swapJ;
        }
    }

    private static void preparation(){
        int j = 0;
        int m = 0;
        for (int i = 0; i < max; i++) {
            m = (m + 1) % max;
            j = (j + c[m]) % max;
            swapI = m;
            swapJ = j;
            c[swapJ] = swapI;
            c[swapI] = swapJ;
            key[i] = c[(c[m] + c[j]) % max];
        }
    }
}
