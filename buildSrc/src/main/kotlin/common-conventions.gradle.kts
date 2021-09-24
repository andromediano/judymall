/*
 * Copyright 2019. OPENTEST. All rights reserved.
 */

plugins {
  // Apply the java Plugin to add support for Java.
  java
  id("idea")
}

val projectGroup: String by project
val projectVersion: String by project

group = projectGroup
version = projectVersion
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
}

dependencies {
  constraints {
    // Define dependency versions as constraints
    //implementation("org.apache.commons:commons-text:1.9")
  }

  implementation("org.springframework.boot:spring-boot-starter-json")
  //implementation("org.springframework.boot:spring-boot-starter-validation")

  // JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
  // JUnit Platform : 테스트 프레임워크가 동작하도록 TestEngine API를 정의하는 모듈
  // JUnit Jupiter : JUnit 5에서 지원하는 새로운 기능들을 위한 모듈
  // JUnit Vintage : JUnit 5 플랫폼에서 JUnit 3나 JUnit 4 기반의 테스트를 지원하기 위한 모듈
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    // 5버전만 사용하자. 헤깔리니까. JUnit 4 모듈은 제외하도록하자.
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
}

// https://docs.gradle.org/current/dsl/org.gradle.plugins.ide.idea.model.IdeaModule.html
idea {
  module.isDownloadJavadoc = true
  module.isDownloadSources = true
}

// https://docs.gradle.org/current/dsl/org.gradle.api.tasks.compile.JavaCompile.html
// https://docs.gradle.org/current/dsl/org.gradle.api.tasks.compile.CompileOptions.html
//  -Xlint:unchecked:   Shows information about unchecked or unsafe operations.
//  -Xlint:deprecation: Shows information about deprecated members.
tasks.withType<JavaCompile>() {
  options.compilerArgs.addAll(arrayOf("-Xlint:unchecked", "-Xlint:deprecation"))
}

tasks.test {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}
