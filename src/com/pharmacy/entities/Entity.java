package com.pharmacy.entities;
import com.utils.StringHelpers;

import java.util.UUID;

public class Entity{
    private String id;

    Entity(){
        if(StringHelpers.IsNullOrEmpty(id))
            id = UUID.randomUUID().toString();
    }

    public String getId(){
        return this.id;
    }
    public boolean IsEqualId(String id){
        return this.id.equals(id);
    }
}