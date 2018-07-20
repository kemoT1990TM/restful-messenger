package com.tkartas.database;

import com.tkartas.model.Message;
import com.tkartas.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
    // Not thread safe - for learning purposes

    // == fields ==
    private static Map<Long,Message> messages=new HashMap<>();
    private static Map<String,Profile> profiles=new HashMap<>();

    static{
        messages.put(1L,new Message(1L,"Hello World","Tomek"));
        messages.put(2L,new Message(2L,"Hello Jersey","Karol"));
        messages.put(3L,new Message(3L,"Hello There","Jarek"));
        profiles.put("Tomek", new Profile(1L,"Tomek","Tomasz","Kartasiński"));
        profiles.put("Karol", new Profile(1L,"Karol","Karol","Jarek"));
        profiles.put("Jarek", new Profile(1L,"Jarek","Karol","Jarek"));
    }

    // == public methods ==
    public static Map<Long,Message> getMessages(){
        return messages;
    }

    public static Map<String,Profile> getProfiles(){
        return profiles;
    }
}
