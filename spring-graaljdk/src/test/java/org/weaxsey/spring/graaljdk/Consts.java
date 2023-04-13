/*
 * Copyright (C) 2023 Weaxs
 *
 */
package org.weaxsey.spring.graaljdk;

import com.fasterxml.jackson.databind.node.TextNode;


public class Consts {

    protected static final TextNode ASSERT_OUT = new TextNode("Hello GraalVM!");
    protected static final String FUNC_NAME = "hello";

    protected static final String PY_MAIN_SCRIPT = "/scripts/hello.py";
    protected static final String PY_FUNC_SCRIPT = "/scripts/hello_func.py";
    protected static final String R_MAIN_SCRIPT = "/scripts/hello.R";
    protected static final String R_FUNC_SCRIPT = "/scripts/hello_func.R";
    protected static final String CPP_MAIN_SCRIPT = "/scripts/hello.cpp";
    protected static final String CPP_FUNC_SCRIPT = "/scripts/hello_func.cpp";
    protected static final String RUBY_MAIN_SCRIPT = "/scripts/hello.rb";
    protected static final String RUBY_FUNC_SCRIPT = "/scripts/hello_func.rb";
    protected static final String JS_MAIN_SCRIPT = "/scripts/hello.js";
    protected static final String JS_FUNC_SCRIPT = "/scripts/hello_func.js";



}
