/*
 * Copyright (C) 2023 Weaxs
 *
 */

package org.weaxsey.spring.openjdk;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayOutputStream;
import java.util.Date;


@Configuration
public class PolyglotContextFactory {

    public static final ByteArrayOutputStream JS_OUT = new ByteArrayOutputStream();
    public static final ByteArrayOutputStream GRAAL_OUT = new ByteArrayOutputStream();

    @Bean(destroyMethod = "close")
    public Context jsCtx() {
        HostAccess hostAccess = HostAccess.newBuilder(HostAccess.ALL)
                // js -> java
                .targetTypeMapping(Date.class, Object.class, null, v -> v)
                .targetTypeMapping(Double.class, Object.class, null, v -> {
                    if (v.longValue() == v) {
                        return v.longValue();
                    } else {
                        return v;
                    }
                })
                .build();

        return Context.newBuilder("js")
                .allowHostAccess(hostAccess)
                .allowExperimentalOptions(true)
                .option("js.ecmascript-version", "2017")
                .option("js.foreign-object-prototype", "true")
                .option("js.console", "true")
                .option("js.print", "true")
                .option("js.unhandled-rejections", "throw")

                // nodejs modules path
//                .option("js.commonjs-require", "true")
//                .option("js.commonjs-require-cwd", "/xxx/.npm/node_modules")
                .out(JS_OUT).build();
    }

    @Bean(destroyMethod = "close")
    public Context graalCtx() {
        return Context.newBuilder().allowAllAccess(true).out(GRAAL_OUT).build();
    }


}
