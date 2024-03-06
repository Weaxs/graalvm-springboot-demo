# Demos for using graalvm with spring-boot

Demos for graalvm with springboot projects.

## spring with graalvm-jdk

**JDK**：[graalvm-jdk](https://www.graalvm.org/downloads/)

**dependency**: org.graalvm.sdk:graal-sdk

**support language**:


| icon                                                | language                        | requied                              | doc                                                                                     | github repo                                          |
| --------------------------------------------------- | ------------------------------- | ------------------------------------ | --------------------------------------------------------------------------------------- | ---------------------------------------------------- |
| <img height="30" src="icon/python.png" width="30"/> | python                          | gu install python                    | [Python Reference](https://www.graalvm.org/latest/reference-manual/python/)             | [graalpython](https://github.com/oracle/graalpython) |
| <img height="30" src="icon/r.png" width="30"/>      | R                               | gu install R                         | [R Reference](https://)                                                                 | [fastr](https://github.com/oracle/fastr)             |
| <img height="30" src="icon/js.png" width="30"/>     | js, nodejs                      | gu install js<br />gu install nodejs | [JavaScript and Node.js Reference](https://www.graalvm.org/latest/reference-manual/js/) | [graaljs](https://github.com/oracle/graaljs)         |
| <img height="30" src="icon/llvm.png" width="30"/>   | C/C++, Fortran, Rust and others | gu install llvm                      | [LLVM Reference](https://www.graalvm.org/latest/reference-manual/llvm/)                 |                                                      |
| <img height="30" src="icon/ruby.png" width="30"/>   | ruby                            | gu install ruby                      | [Ruby Reference](https://www.graalvm.org/latest/reference-manual/ruby/)                 | [truffleruby](https://github.com/oracle/truffleruby) |

> ✨ 1.gu command  in “../graalvm-ce-xx/Contents/Home/bin” directory<br/> ✨ 2. before use llvm, must compile c/c++/fortran/rus/others to executables with embedded bitcode. you can use clang/clang++ in llvm-toolchain, refer [Compiling to LLVM Bitcode
> ](https://www.graalvm.org/latest/reference-manual/llvm/Compiling/)

## spring with openjdk

**JDK**：[openjdk](https://openjdk.org/)

**dependency**: org.graalvm.sdk:graal-sdk, org.graalvm.polyglot:js-community, org.graalvm.polyglot:python-community, org.graalvm.polyglot:llvm-community, org.graalvm.polyglot:ruby-community

**support language**:


| icon                                                | language                                          | requied                               | doc                                                                                     | github repo                                          | maven repo                                                                                         |
| --------------------------------------------------- | ------------------------------------------------- | ------------------------------------- | --------------------------------------------------------------------------------------- | ---------------------------------------------------- | -------------------------------------------------------------------------------------------------- |
| <img height="30" src="icon/js.png" width="30"/>     | js, nodejs       (ECMAScript=>5-14/2015-2023)     | org.graalvm.polyglot:js-community     | [JavaScript and Node.js Reference](https://www.graalvm.org/latest/reference-manual/js/) | [graaljs](https://github.com/oracle/graaljs)         | [maven js-community](https://mvnrepository.com/artifact/org.graalvm.polyglot/js-community)         |
| <img height="30" src="icon/python.png" width="30"/> | python   (python=>3.10.8)                         | org.graalvm.polyglot:python-community | [Python Reference](https://www.graalvm.org/latest/reference-manual/python/)             | [graalpython](https://github.com/oracle/graalpython) | [maven python-community](https://mvnrepository.com/artifact/org.graalvm.polyglot/python-community) |
| <img height="30" src="icon/llvm.png" width="30"/>   | C/C++, Fortran, Rust and others   (llvm=>16.0.1;) | org.graalvm.polyglot:llvm-community   | [LLVM Reference](https://www.graalvm.org/latest/reference-manual/llvm/)                 |                                                      | [maven llvm-community](https://mvnrepository.com/artifact/org.graalvm.polyglot/llvm-community)     |
| <img height="30" src="icon/ruby.png" width="30"/>   | ruby          (ruby=>3.2.2)                       | org.graalvm.polyglot:ruby-community   | [Ruby Reference](https://www.graalvm.org/latest/reference-manual/ruby/)                 | [truffleruby](https://github.com/oracle/truffleruby) | [maven ruby-community](https://mvnrepository.com/artifact/org.graalvm.polyglot/ruby-community)     |

## sandbox resource limits

### Community

[delight-graaljs-sandbox](https://github.com/javadelight/delight-graaljs-sandbox)

[delight-nashorn-sandbox](https://github.com/javadelight/delight-nashorn-sandbox)

### graalvm official

[polyglot-sandbox](https://www.graalvm.org/latest/security-guide/polyglot-sandbox/)

[resource-limits](https://www.graalvm.org/latest/security-guide/polyglot-sandbox/#resource-limits)

## graalvm docker container

**Github Container Registry**: [https://github.com/orgs/graalvm/packages](https://github.com/orgs/graalvm/packages)

**Github Repo**: [https://github.com/graalvm/container](https://github.com/graalvm/container)


| container    | install command                                    | link                                                                                                                                 |
| ------------ | -------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| graalvm-ce   | docker pull ghcr.io/graalvm/graalvm-ce:{VERSION}   | [https://github.com/graalvm/container/pkgs/container/graalvm-ce](https://github.com/graalvm/container/pkgs/container/graalvm-ce)     |
| jdk          | docker pull ghcr.io/graalvm/jdk:{VERSION}          | [https://github.com/graalvm/container/pkgs/container/jdk](https://github.com/graalvm/container/pkgs/container/jdk)                   |
| native-image | docker pull ghcr.io/graalvm/native-image:{VERSION} | [https://github.com/graalvm/container/pkgs/container/native-image](https://github.com/graalvm/container/pkgs/container/native-image) |
| truffleruby  | docker pull ghcr.io/graalvm/truffleruby:{VERSION}  | [https://github.com/graalvm/container/pkgs/container/truffleruby](https://github.com/graalvm/container/pkgs/container/truffleruby)   |
| nodejs       | docker pull ghcr.io/graalvm/nodejs:{VERSION}       | [https://github.com/graalvm/container/pkgs/container/nodejs](https://github.com/graalvm/container/pkgs/container/nodejs)             |
| graalpy      | docker pull ghcr.io/graalvm/graalpy:{VERSION}      | [https://github.com/graalvm/container/pkgs/container/graalpy](https://github.com/graalvm/container/pkgs/container/graalpy)           |

## Reference

[graalvm-demos repo](https://github.com/graalvm/graalvm-demos)

[graalvm examples](https://github.com/graalvm/examples)

[graalvm docs](https://www.graalvm.org/latest/docs/)

![](icon/graalvm.png)
