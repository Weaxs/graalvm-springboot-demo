/*
 * Copyright (C) 2023 Weaxs
 *
 */

package org.weaxsey.spring.openjdk;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class ContextExecutorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContextExecutorService.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final Map<String, Context> ctxMap;

    @Autowired
    public ContextExecutorService(Map<String, Context> ctxMap) {
        this.ctxMap = ctxMap;
    }

    public void executeMain(Source source) {
        Context graalCtx = ctxMap.get("graalCtx");
        graalCtx.eval(source);
    }

    public void executeMain(String languageId, CharSequence source) throws IllegalAccessException {
        Context context = ctxMap.get(languageId.toLowerCase() + "Ctx");
        if (context == null) {
            throw new IllegalAccessException("A language with id '" + languageId + "' is not support.");
        }
        context.eval(languageId, source);
    }

    public JsonNode executeFunc(Source source, String function, Object... params) {
        Context graalCtx = ctxMap.get("graalCtx");
        graalCtx.eval(source);
        String language = source.getLanguage();
        Value func = graalCtx.getBindings(language).getMember(function);
        Value ret;
        if (params == null) {
            ret = func.execute();
        } else {
            ret = func.execute(params);
        }
        return MAPPER.valueToTree(ret.as(Object.class));
    }

    public JsonNode executeFunc(String languageId, CharSequence source, String function, Object... params) throws IllegalAccessException {
        Context context = ctxMap.get(languageId.toLowerCase() + "Ctx");
        if (context == null) {
            throw new IllegalAccessException("A language with id '" + languageId + "' is not support.");
        }

        context.eval(languageId, source);
        Value func = context.getBindings(languageId).getMember(function);
        Value ret;
        if (params == null) {
            ret = func.execute();
        } else {
            ret = func.execute(params);
        }
        return MAPPER.valueToTree(ret.as(Object.class));
    }

}
