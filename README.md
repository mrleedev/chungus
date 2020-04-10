# Chungus

**Automatically use open-api clients from your API registry**

## Example

Throw this in your gradle build script, and off you go:

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
```

This will generate a `petstore` API client and make it available to your project.