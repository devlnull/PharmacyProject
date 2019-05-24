package com.pharmacy.factories;

import com.pharmacy.entities.Script;
import com.pharmacy.entities.ScriptDetail;
import com.pharmacy.services.IScriptService;
import com.pharmacy.services.ScriptService;

public class ScriptFactory {
    public IScriptService create(){
        return new ScriptService(
                new RepoFactory<Script>().create(Script.class),
                new RepoFactory<ScriptDetail>().create(ScriptDetail.class));
    }
}
