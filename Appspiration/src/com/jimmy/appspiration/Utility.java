package com.jimmy.appspiration;

import java.util.regex.Pattern;

public class Utility {
	
    private static Pattern pattern;
    
    public static boolean validate(String username) {
        pattern = Pattern.compile("[^a-zA-Z0-9]");
        boolean hasSpecialChar = pattern.matcher(username).find();
        return !hasSpecialChar;
    }
    
    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
}