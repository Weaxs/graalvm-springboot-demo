/*
 * Copyright (C) 2023 Weaxs
 *
 */

import com.fasterxml.jackson.databind.JsonNode;
import org.graalvm.polyglot.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.weaxsey.spring.graaljdk.ContextExecutorService;
import org.weaxsey.spring.graaljdk.SpringGraalApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;


@SpringBootTest(classes = SpringGraalApplication.class)
public class RScriptTest {

    private URL mainRUrl;
    private URL funcRUrl;
    private String mainRCode;
    private String funcRCode;

    @Autowired
    private ContextExecutorService service;

    @BeforeEach
    void init() throws URISyntaxException, IOException {
        mainRUrl = getClass().getResource(Consts.R_MAIN_SCRIPT);
        funcRUrl = getClass().getResource(Consts.R_FUNC_SCRIPT);
        mainRCode = new String(Files.readAllBytes(Path.of(mainRUrl.toURI())));
        funcRCode = new String(Files.readAllBytes(Path.of(funcRUrl.toURI())));
    }

    @Test
    void executeMainTestByGraalCtx() throws IOException {
        Source source = Source.newBuilder("R", mainRUrl).build();
        service.executeMain(source);
    }

    @Test
    void executeMainTestByRCtx() throws IllegalAccessException {
        service.executeMain("R", mainRCode);
    }

    @Test
    void executeFuncByGraalCtx() throws IOException {
        Source source = Source.newBuilder("R", funcRUrl).build();
        JsonNode ret = service.executeFunc(source, Consts.FUNC_NAME);
        Assertions.assertEquals(Consts.ASSERT_OUT, ret);
    }

    @Test
    void executeFuncByRCtx() throws IllegalAccessException {
        JsonNode ret = service.executeFunc("R", funcRCode, Consts.FUNC_NAME);
        Assertions.assertEquals(Consts.ASSERT_OUT, ret);
    }

}
