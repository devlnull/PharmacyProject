package com.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class HashHelper {
    public static String hashToSHA256(String originalString)
    {
        return Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
    }

    public static boolean Equal(String RawString, String HashString){
        String newHash = Hashing.sha256()
                .hashString(RawString, StandardCharsets.UTF_8)
                .toString();
        return newHash.equals(HashString);
    }
}
