#!/usr/bin/env kotlin

@file:Repository("https://dl.bintray.com/jakubriegel/kotlin-shell")
@file:DependsOn("eu.jrie.jetbrains:kotlin-shell-core:0.2.1")
@file:DependsOn("org.slf4j:slf4j-simple:1.7.28")
@file:CompilerOptions("-Xopt-in=kotlin.RequiresOptIn")
@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

import eu.jrie.jetbrains.kotlinshell.shell.shell

shell {
    "mkdir someFiles"()
    "touch someFiles/text.txt"()

    pipeline {
        "echo Adding some line".process() pipe "tee someFiles/text.txt".process()
    }

    pipeline {
        file("someFiles/text.txt") pipe "grep Add".process()
    }

    "rm -rf someFiles"()
}
