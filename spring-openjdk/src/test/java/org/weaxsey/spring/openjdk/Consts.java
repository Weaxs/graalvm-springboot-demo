/*
 * Copyright (C) 2023 Weaxs
 *
 */
package org.weaxsey.spring.openjdk;

import com.fasterxml.jackson.databind.node.TextNode;


public class Consts {

    protected static final TextNode ASSERT_OUT = new TextNode("Hello GraalVM!");
    protected static final String FUNC_NAME = "hello";

    protected static final String JS_MAIN_SCRIPT = "/scripts/hello.js";
    protected static final String JS_FUNC_SCRIPT = "/scripts/hello_func.js";

}
