package com.pharmacy.factories;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;

public class ContextFactory {
    private static IContext instance = null;

    public static IContext create(){
        if(instance == null)
            instance = new InMemoryContext();
        return instance;
    }
}
