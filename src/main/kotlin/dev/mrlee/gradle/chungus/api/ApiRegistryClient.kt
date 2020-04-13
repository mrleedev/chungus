package dev.mrlee.gradle.chungus.api

import java.net.URI



class ApiRegistryClient {
    fun fetchOpenApiSpecForService(uri: URI): String =
            uri.toURL().openStream().bufferedReader().use { it.readText() }

}