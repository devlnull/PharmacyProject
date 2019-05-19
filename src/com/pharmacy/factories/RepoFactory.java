package com.pharmacy.factories;

import com.pharmacy.context.IContext;
import com.pharmacy.entities.Entity;
import com.pharmacy.repo.IRepo;
import com.pharmacy.repo.InMemoryRepo;

public class RepoFactory<T extends Entity> {
    public IRepo<T> create(Class<?> classT){
        IContext context = ContextFactory.create();
        return new InMemoryRepo<T>(context, classT);
    }
}
