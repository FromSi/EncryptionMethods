public class Caesar {

    private static final char[] CHARS = {'!', '@', '"', '#', '№', '$',
            ';', '%', ':', ';', '&', '?',
            '(', ')', '-', '_', '+', '=',
            '[', ']', '{', '}', '/', '|',
            '*', '.', ',', '<', '>', ' ',
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'Ё', 'ё'};

    public static String encrypt(String lang, String content, int key) {
        return lang(lang, content, key, true);
    }

    public static String decrypt(String lang, String content, int key) {
        return lang(lang, content, key, false);
    }

    private static String lang(String lang, String content, int key, boolean encrypt) {
        switch (lang) {
            case "en":
                return start(content, key, encrypt, 26, (int) '\u0041', (int) '\u0061');
            case "ru":
                return start(content, key, encrypt, 32, (int) '\u0410', (int) '\u0430');
            default:
                return start(content, key, encrypt, 26, (int) '\u0041', (int) '\u0061');
        }
    }

    private static int calcEncrypt(int dec, int key, int size) {
        return (dec + key) % size;
    }

    private static int calcDecrypt(int dec, int key, int size) {
        return (dec - key + size) % size;
    }

    private static String start(String content, int key, boolean encrypt,
                                int size, int minA, int minB) {
        if (size <= key)
            return "Error";

        char[] text = new char[content.length()];

        for (int i = 0; i < content.length(); i++) {
            int dec = (int) content.charAt(i);

            if (minA <= dec && dec <= minA + size) {

                if (encrypt)
                    text[i] = (char) (minA + calcEncrypt(dec - minA, key, size));
                else
                    text[i] = (char) (minA + calcDecrypt(dec - minA, key, size));
            } else if (minB <= dec && dec <= minB + size) {

                if (encrypt)
                    text[i] = (char) (minB + calcEncrypt(dec - minB, key, size));
                else
                    text[i] = (char) (minB + calcDecrypt(dec - minB, key, size));
            } else {
                dec = findChar(dec);

                if (dec != 0)
                    text[i] = (char) dec;
                else
                    return "Error";
            }
        }

        return new String(text);
    }

    private static int findChar(int dec) {
        for (char CHAR : CHARS)
            if (CHAR == dec)
                return dec;
        return 0;
    }
}
