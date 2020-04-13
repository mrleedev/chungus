package dev.mrlee.gradle.chungus.api

import java.net.URI
import java.net.URL

class ApiRegistryClient {
    fun fetchOpenApiSpecForService(uri: URI): String =
            uri.toURL().openStream().bufferedReader().use { it.readText() }

}