package dev.virusnest.virusclient.util;

import java.util.ArrayList;
import java.util.List;

public class StateManager {
    private static List<Boolean> mobile = new ArrayList<>();

    private static Boolean checkState(List<Boolean> states){
        for (Boolean state:states){
            if (state) return state;
        }
        return false;
    }

    public static void setMobileState(Boolean state){
        if (mobile.isEmpty()&&state){
            mobile.add(state);
        }else if(!state&&!mobile.isEmpty()){
            mobile.remove(!state);
        }
    }

    public static Boolean getMobileState(){
        return checkState(mobile);
    }

}
