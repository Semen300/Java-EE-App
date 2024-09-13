package services;

public class StringService {
    public static void replaceAll(StringBuilder str, String oldStr, String newStr) {

        if (String.valueOf(str).equals("") || oldStr.equals("") || oldStr.equals(newStr)) {
            return;
        }
        if (newStr == null) {
            newStr = "";
        }
        final int strLength = str.length();
        final int oldStrLength = oldStr.length();

        for (int i = 0; i < strLength; i++) {
            int index = str.indexOf(oldStr, i);

            if (index == -1) {
                if (i == 0) {
                    return;
                }
                return;
            }
            str.replace(index, index + oldStrLength, newStr);

        }
    }
}
