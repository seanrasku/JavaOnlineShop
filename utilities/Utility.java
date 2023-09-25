package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.utilities;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.Main;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl.MainMenu;

import java.util.InputMismatchException;

public final class Utility {
    public static boolean isStringANumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch(InputMismatchException | NumberFormatException e) {
            return false;
        }
    }
    public static boolean isValidRange(String s, int start, int end) {
        if(!isStringANumber(s)) return false;
        int num = Integer.parseInt(s);
        return num >= start && num <= end;
    }
    public static boolean isMalformedString(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isLetter(s.charAt(i))) return true;
        }
        return false;
    }
    public static boolean equalsReservedWord(String s) {
        return s.equals(MainMenu.MENU_COMMAND) || s.equals(Main.EXIT_COMMAND);
    }
}
