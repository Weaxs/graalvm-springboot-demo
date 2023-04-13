/*
 * Copyright (C) 2023 Weaxs
 *
 */
package org.weaxsey.spring.openjdk;

import com.fasterxml.jackson.databind.JsonNode;
import org.graalvm.polyglot.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;


@SpringBootTest(classes = SpringOpenJDKApplication.class)
public class JsScriptTest {

    private URL mainJsUrl;
    private URL funcJsUrl;
    private String mainJsCode;
    private String funcJsCode;

    @Autowired
    private ContextExecutorService service;

    @BeforeEach
    void init() throws URISyntaxException, IOException {
        mainJsUrl = getClass().getResource(Consts.JS_MAIN_SCRIPT);
        funcJsUrl = getClass().getResource(Consts.JS_FUNC_SCRIPT);
        mainJsCode = new String(Files.readAllBytes(Path.of(mainJsUrl.toURI())));
        funcJsCode = new String(Files.readAllBytes(Path.of(funcJsUrl.toURI())));
    }

    @Test
    void executeMainTestByGraalCtx() throws IOException {
        Source source = Source.newBuilder("js", mainJsUrl).build();
        service.executeMain(source);
    }

    @Test
    void executeMainTestByJsCtx() throws IllegalAccessException {
        service.executeMain("js", mainJsCode);
    }

    @Test
    void executeFuncByGraalCtx() throws IOException {
        Source source = Source.newBuilder("js", funcJsUrl).build();
        JsonNode ret = service.executeFunc(source, Consts.FUNC_NAME);
        Assertions.assertEquals(Consts.ASSERT_OUT, ret);
    }

    @Test
    void executeFuncByJsCtx() throws IllegalAccessException {
        JsonNode ret = service.executeFunc("js", funcJsCode, Consts.FUNC_NAME);
        Assertions.assertEquals(Consts.ASSERT_OUT, ret);
    }
}
