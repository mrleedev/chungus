# Chungus

**Automatically use open-api clients from your API registry**

## Minimal example

Using the `chungus` plugin alone will simply pull your defined service specs into
the build. It would then be up to you to configure your OpenAPI plugin to handle those
files.

```gradle
plugins {
    id('dev.mrlee.gradle.chungus')
}
            
chungus {
    services {
        petstore {
            url = "https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/petstore.yaml"
            format = "yaml"
        }
    }
}

compileJava.dependsOn tasks.fetchOpenApiSpecs
```

## Full-fat example

`chungus` will provide more if it detects the presence of the `org.openapi.generator` plugin, and will—if you so choose—
generate the client code for you. This will be configurable just as if you were using the plugin by itself.

```gradle
plugins {
    id('dev.mrlee.gradle.chungus')
    id('org.openapi.generator')
}
            
chungus {
    services {
        petstore {
            url = "https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/petstore.yaml"
            format = "yaml"
            generator {
                ...
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