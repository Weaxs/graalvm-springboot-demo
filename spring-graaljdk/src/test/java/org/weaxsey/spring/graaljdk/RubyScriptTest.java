/*
 * Copyright (C) 2023 Weaxs
 *
 */
package org.weaxsey.spring.graaljdk;

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


@SpringBootTest(classes = SpringGraalApplication.class)
public class RubyScriptTest {

    private URL mainRubyUrl;
    private URL funcRubyUrl;
    private String mainRubyCode;
    private String funcRubyCode;

    @Autowired
    private ContextExecutorService service;

    @BeforeEach
    void init() throws URISyntaxException, IOException {
        mainRubyUrl = getClass().getResource(Consts.RUBY_MAIN_SCRIPT);
        funcRubyUrl = getClass().getResource(Consts.RUBY_FUNC_SCRIPT);
        mainRubyCode = new String(Files.readAllBytes(Path.of(mainRubyUrl.toURI())));
        funcRubyCode = new String(Files.readAllBytes(Path.of(funcRubyUrl.toURI())));
    }

    @Test
    void executeMainTestByGraalCtx() throws IOException {
        Source source = Source.newBuilder("ruby", mainRubyUrl).build();
        service.executeMain(source);
    }

    @Test
    void executeMainTestByRubyCtx() throws IllegalAccessException {
        service.executeMain("ruby", mainRubyCode);
    }

    @Test
    void executeFuncByGraalCtx() throws IOException {
        Source source = Source.newBuilder("ruby", funcRubyUrl).build();
        JsonNode ret = service.executeFunc(source, Consts.FUNC_NAME);
        Assertions.assertEquals(Consts.ASSERT_OUT, ret);
    }

    @Test
    void executeFuncByRubyCtx() throws IllegalAccessException {
        JsonNode ret = service.executeFunc("ruby", funcRubyCode, Consts.FUNC_NAME);
        Assertions.assertEquals(Consts.ASSERT_OUT, ret);
    }

}
