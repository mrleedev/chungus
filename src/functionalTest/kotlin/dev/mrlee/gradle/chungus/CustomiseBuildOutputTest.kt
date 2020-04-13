package dev.mrlee.gradle.chungus

import dev.mrlee.gradle.chungus.support.BaseFunctionalTest
import kotlin.test.Test
import kotlin.test.assertTrue

class CustomiseBuildOutputTest: BaseFunctionalTest() {

    @Test fun `output directories for clients and specs can be changed`() {
        buildScript("""
            plugins {
                id("dev.mrlee.gradle.chungus")
                id("org.openapi.generator") version "4.3.0"
            }
            
            chungus {
                specDir = "my-spec-folder"
                clientDir = "my-clients-folder"
                
                services {
                    petstore {
                        url = uri("https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/petstore.yaml")
                        format = "yaml"
                    }
                }
            }
        """)

        runBuildWithArgs("fetchOpenApiSpecs")

        assertTrue(projectDir.resolve("build/my-spec-folder/petstore.yaml").exists())
    }
}
