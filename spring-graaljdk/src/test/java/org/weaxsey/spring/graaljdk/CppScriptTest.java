/*
 * Copyright (C) 2023 Weaxs
 *
 */
package org.weaxsey.spring.graaljdk;

import org.graalvm.polyglot.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URL;


@SpringBootTest(classes = SpringGraalApplication.class)
public class CppScriptTest {

    private URL mainCppUrl;
    private URL funcCppUrl;

    @Autowired
    private ContextExecutorService service;

    @BeforeEach
    void init() {
        mainCppUrl = getClass().getResource(Consts.CPP_MAIN_SCRIPT);
        funcCppUrl = getClass().getResource(Consts.CPP_FUNC_SCRIPT);
    }

    @Test
    void executeMainTestByGraalCtx() throws IOException {
        Source source = Source.newBuilder("llvm", mainCppUrl).build();
        service.executeMain(source);
    }

    @Test
    void executeFuncTestByGraalCtx() throws IOException {
        Source source = Source.newBuilder("llvm", funcCppUrl).build();
        service.executeMain(source);
    }

}
