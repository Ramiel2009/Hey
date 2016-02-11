package ua.hey.hey;

import java.util.Arrays;

/**
 * Created by Maxim on 2/6/16.
 */
public  class ParseResponse {
    public static String[] parse(String s){
        String message[] = s.split(";");
        System.out.println(Arrays.toString(message));
        return message;
    }
}
