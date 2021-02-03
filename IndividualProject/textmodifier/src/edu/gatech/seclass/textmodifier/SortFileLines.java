package edu.gatech.seclass.textmodifier;

import java.util.Comparator;

public class SortFileLines implements Comparator<String> {
    @Override
    public int compare(String inputString1, String inputString2) {
        String s1 = normalizeString(inputString1);
        String s2 = normalizeString(inputString2);
        if (s1.equals(s2)) return 0;
        int s1Len = s1.length();
        int s2Len = s2.length();
        int sLen = s1Len < s2Len ? s1Len : s2Len;
        for (int i = 0; i < sLen; i++) {
            Character ch1 = s1.charAt(i);
            Character ch2 = s2.charAt(i);
            if (Character.isDigit(ch1) && Character.isDigit(ch2)) {
                if (Character.getNumericValue(ch1) == Character.getNumericValue(ch2)) {
                    if (i + 1 >= sLen) {
                        return s1.compareTo(s2);
                    }
                    continue;
                } else if (Character.getNumericValue(ch1) < Character.getNumericValue(ch2)) return -1;
                else if (Character.getNumericValue(ch1) > Character.getNumericValue(ch2)) return 1;
            } else if (Character.isDigit(ch1) && Character.isLetter(ch2)) return -1;
            else if (Character.isDigit(ch2) && Character.isLetter(ch1)) return 1;
            else if (Character.isLetter(ch1) && Character.isLetter(ch2)) {
                if (ch1 > ch2) return 1;
                else if (ch1 == ch2) {
                    if (i + 1 >= sLen) {
                        return s1.compareTo(s2);
                    }
                    continue;
                } else return -1;
            }
            if (i + 1 >= sLen) {
                return s1.compareTo(s2);
            }
        }
        return 0;
    }

    private String normalizeString(String inputString) {
        if (inputString == null || inputString.isBlank() || inputString.isEmpty()) return inputString;
        return inputString.replaceAll("[^A-Za-z0-9]", "");
        //return inputString.replaceAll("[^A-Za-z0-9]", "").replaceAll("\\s", "").replaceAll(System.lineSeparator(), "");
    }
}