package com.silmood.who_is;

/**
 * Created by silmood on 10/15/17.
 */

public class Character {
    int nameResId;
    int imageResId;

    Character(int name, int image){
        this.nameResId = name;
        this.imageResId = image;
    }

    public static String act(){
        return "Acting";
    }

    public static class Secodary {

    }
}
