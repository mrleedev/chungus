package dev.mrlee.gradle.chungus

import java.net.URL

class ApiRegistryClient {
    fun fetchOpenApiSpecForService(): String {
        return URL("https://services-uk.dev.babylontech.co.uk/api-registry/v1/input-matcher/input-matcher-v1/openapi")
                .openStream()
                .bufferedReader()
                .use { it.readText() }
    }
}