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
public class PythonScriptTest {

    private URL mainPyUrl;
    private URL funcPyUrl;
    private String mainPyCode;
    private String funcPyCode;

    @Autowired
    private ContextExecutorService service;

    @BeforeEach
    void init() throws URISyntaxException, IOException {
        mainPyUrl = getClass().getResource(Consts.PY_MAIN_SCRIPT);
        funcPyUrl = getClass().getResource(Consts.PY_FUNC_SCRIPT);
        mainPyCode = new String(Files.readAllBytes(Path.of(mainPyUrl.toURI())));
        funcPyCode = new String(Files.readAllBytes(Path.of(funcPyUrl.toURI())));
    }

    @Test
    void executeMainTestByGraalCtx() throws IOException {
        Source source = Source.newBuilder("python", mainPyUrl).build();
        service.executeMain(source);
    }

    @Test
    void executeMainTestByPyCtx() throws IllegalAccessException {
        service.executeMain("python", mainPyCode);
    }

    @Test
    void executeFuncByGraalCtx() throws IOException {
        Source source = Source.newBuilder("python", funcPyUrl).build();
        JsonNode ret = service.executeFunc(source, Consts.FUNC_NAME);
        Assertions.assertEquals(Consts.ASSERT_OUT, ret);
    }

    @Test
    void executeFuncByPythonCtx() throws IllegalAccessException {
        JsonNode ret = service.executeFunc("python", funcPyCode, Consts.FUNC_NAME);
        Assertions.assertEquals(Consts.ASSERT_OUT, ret);
    }


}
