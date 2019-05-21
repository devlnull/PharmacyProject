package com.pharmacy.factories;

import com.pharmacy.context.ICurrentContext;
import com.pharmacy.context.InMemoryCurrentContext;

public class CurrentContextFactory {
    private static ICurrentContext instance = null;

    public static ICurrentContext create(){
        if(instance == null)
            instance = new InMemoryCurrentContext();
        return instance;
    }
}
