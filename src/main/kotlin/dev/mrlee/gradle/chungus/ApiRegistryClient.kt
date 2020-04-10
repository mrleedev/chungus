package dev.mrlee.gradle.chungus

import java.net.URL

class ApiRegistryClient {
    fun fetchOpenApiSpecForService(url: String): String {
        return URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
    }
}