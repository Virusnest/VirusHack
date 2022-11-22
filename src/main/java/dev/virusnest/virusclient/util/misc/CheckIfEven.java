package dev.virusnest.virusclient.util.misc;

public class CheckIfEven {
    public static boolean isEvenSaneMethod(int n){
        return (n%2)==0;
    }
    public static boolean isEvenStringMethod(float n){
        return !Float.toString(n/2f).contains(".");
    }
    public static boolean isEvenLoopMethod(int n){
        boolean state = false;
        for (int i =0; i==n;i++){
            state = !state;
        }
        return state;
    }
    public static boolean isEvenLastCharMethod(int n){
        boolean[] lastDigit = new boolean[]{true,false,true,false,true,false,true,false,true,false};
        String value = Integer.toString(n);
        return lastDigit[value.charAt(value.length()-1)];
    }
}
