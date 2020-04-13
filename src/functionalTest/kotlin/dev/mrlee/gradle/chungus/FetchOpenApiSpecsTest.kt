package dev.mrlee.gradle.chungus

import dev.mrlee.gradle.chungus.support.BaseFunctionalTest
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchOpenApiSpecsTest: BaseFunctionalTest() {
    @Test fun `fetches the yaml for a single service`() {
        buildScript("""
            plugins {
                id("dev.mrlee.gradle.chungus")
                id("org.openapi.generator") version "4.3.0"
            }
            
            chungus {
                services {
                    petstore {
                        url = uri("https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/petstore.yaml")
                        format = "yaml"
                    }
                }
            }
        """)

        runBuildWithArgs("fetchSpecs")

        assertTrue(projectDir.resolve("build/chungus/spec/petstore.yaml").exists())
    }

    @Test fun `fetches the yaml for a multiple services`() {
        // Setup the test build
        buildScript("""
            plugins {
                id("dev.mrlee.gradle.chungus")
                id("org.openapi.generator") version "4.3.0"
            }
            
            chungus {
                services {
                    petstore {
                        url = uri("https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/petstore.yaml")
                        format = "yaml"
                    }
                    uspto {
                        url = uri("https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/uspto.yaml")
                        format = "yaml"
                    }
                }
            }
        """)

        runBuildWithArgs("fetchSpecs")

        val expectedFiles = listOf("petstore.yaml", "uspto.yaml")

        projectDir.resolve("build/chungus/spec").listFiles()?.let { files ->
            val actualFiles = files.map(File::getName)
            assertEquals(expectedFiles.sorted(), actualFiles.sorted())
        }
    }
}
