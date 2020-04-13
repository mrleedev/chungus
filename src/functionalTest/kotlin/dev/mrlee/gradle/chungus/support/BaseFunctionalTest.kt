package dev.mrlee.gradle.chungus.support

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import java.io.File

abstract class BaseFunctionalTest {
    protected val projectDir = File("build/functionalTest")

    init {
        projectDir.mkdirs()
        projectDir.resolve("settings.gradle").writeText("")
    }

    protected fun buildScript(script: String) {
        projectDir.resolve("build.gradle").writeText(script)
    }

    protected fun runBuildWithArgs(vararg arguments: String): BuildResult? {
        return GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments(*arguments)
                .withProjectDir(projectDir)
                .build()
    }

}