/*
 * Copyright 2019. OPENTEST. All rights reserved.
 */

import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  id("library-conventions")
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  id("io.freefair.lombok")
}

tasks.getByName<BootJar>("bootJar") {
  enabled = false
}
tasks.getByName<Jar>("jar") {
  enabled = true
}
