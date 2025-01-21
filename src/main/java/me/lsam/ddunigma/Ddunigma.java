package me.lsam.ddunigma;

import java.nio.charset.StandardCharsets;
import java.util.*;

public abstract class Ddunigma {
    static final String[] dduChars = new String[] { "뜌", "땨", "이", "우", "야", "!", "?", "." };
    static final String paddingChar = "뭐"; // 72 53 02 00
    // 58 + 45
    // 103

    public static String encode(String text) {
        try {
            StringBuilder encodedBinaries = new StringBuilder();
            StringBuilder result = new StringBuilder();

            for (byte ch : text.getBytes(StandardCharsets.UTF_8)) {
                encodedBinaries.append(padLeft(Integer.toBinaryString(ch & 0xFF), 8, '0'));
            }

            ArrayList<String> encoded = new ArrayList<>(splitString(encodedBinaries.toString(), 6));

            int paddingLength = 6 - encoded.getLast().length();
            encoded.set(encoded.size() - 1, padRight(encoded.getLast(), 6, '0'));

            for (String ch : encoded) {
                int charInt = Integer.parseInt(ch, 2);
                result.append(dduChars[charInt / 8]).append(dduChars[charInt % 8]);
            }

            result.append(paddingChar.repeat((int) ((double) paddingLength / 2)));

            return result.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String decode(String text) {
        try {
            StringBuilder decodedBinaries = new StringBuilder();
            ArrayList<Byte> decodedBytes = new ArrayList<>();
            int paddingCount = countOccurrences(text, paddingChar);
            text = text.substring(0, text.length() - paddingCount);

            for (String chunk : splitString(text, 2)) {
                String ch = Integer.toBinaryString(
                        Integer.parseInt(
                                String.valueOf(indexOf(dduChars, Character.toString(chunk.charAt(0)))) +
                                        indexOf(dduChars, Character.toString(chunk.charAt(1))),
                                8
                        )
                );

                decodedBinaries.append(padLeft(ch, 6, '0'));
            }

            decodedBinaries.setLength(decodedBinaries.length() - paddingCount * 2);

            for (String chunk : splitString(decodedBinaries.toString(), 8)) {
                decodedBytes.add((byte) Integer.parseInt(chunk, 2));
            }

            byte[] byteArray = new byte[decodedBytes.size()];

            for (int i = 0; i < decodedBytes.size(); i++) {
                byteArray[i] = decodedBytes.get(i);
            }

            return new String(byteArray, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> splitString(String s, int length) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i += length) {
            int end = Math.min(i + length, s.length());
            result.add(s.substring(i, end));
        }
        return result;
    }

    public static String padLeft(String word, int totalWidth, char paddingChar) {
        return String.format("%" + totalWidth + "s", word).replace(' ', paddingChar);
    }

    public static String padRight(String word, int totalWidth, char paddingChar) {
        return String.format("%-" + totalWidth + "s", word).replace(' ', paddingChar);
    }

    public static int countOccurrences(String str, String subStr) {
        int count = 0;
        int index = 0;

        // indexOf를 반복적으로 호출하며 등장 횟수 카운트
        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length(); // 중복되지 않게 다음 위치로 이동
        }

        return count;
    }

    public static <T> int indexOf(T[] array, T target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i; // 요소를 찾으면 인덱스 반환
            }
        }

        return -1; // 요소를 찾지 못하면 -1 반환
    }
}
