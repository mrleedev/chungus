# Chungus

**Automatically use open-api clients from your API registry**

![Tests](https://github.com/mrleedev/chungus/workflows/Tests/badge.svg)

------

## Minimal example

Using the `chungus` plugin alone will simply pull your defined service specs into
the build. It would then be up to you to configure your OpenAPI plugin to handle those
files.

```gradle
plugins {
    id('dev.mrlee.gradle.chungus')
}
            
chungus {
    specDir = "chungus/spec"
    clientDir = "chungus/client"
    
    services {
        petstore {
            url = uri("https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/petstore.yaml")
            format = "yaml"

            client {
                 // openApiGenerator config
            }
        }
    }
}

compileJava.dependsOn tasks.generateOpenApiClients
```

## Progress

- [x] Create plugin and minimal DSL
- [x] Fetch and store JSON in build cache
- [x] Functional testing of said behaviour
- [ ] Convert JSON/YAMLS into generated clients
- [ ] Expose clients as dependencies in the build
- [ ] Ensure clients are properly named/not-conflicting
- [ ] Functional testing of generated clients from config
- [ ] [Stretch] autodiscover specs from configured domains
