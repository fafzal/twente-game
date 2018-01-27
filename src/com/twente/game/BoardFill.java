package com.twente.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BoardFill {


    public String fill(String player, int move, Map <String, List <Integer>> userMap) {

        for (String key : userMap.keySet()) {
            if (!userMap.containsKey(player)) {
                List <Integer> a = new ArrayList <>();
                a.add(move);
                userMap.put(player, a);
            } else {
                List <Integer> c = userMap.get(key);
                if (c.contains(move)) {
                    return "move already added";
                } else {
                    userMap.get(player).add(move);
                    return "player added with move";
                }

            }
        }

        if (userMap.size() == 0) {

            List <Integer> list = new ArrayList <>();
            list.add(move);
            userMap.put(player, list);
        }

        return "player added with move";
    }
}
