/*
 * Copyright 2019. OPENTEST. All rights reserved.
 */

//
// https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_management
//
pluginManagement {

  val springBootVer: String by settings
  val dependencyManagementVer: String by settings
  val lombokPluginVer: String by settings

  plugins {
    id("org.springframework.boot") version springBootVer
    id("io.spring.dependency-management") version dependencyManagementVer
    id("io.freefair.lombok") version lombokPluginVer
  }
}

rootProject.name = "judymall"

// Root project 'judymall'
// +--- ':judymall-front'
// |    +--- ':judymall-domain'
// |         `--- ':judymall-core'
// +--- ':judymall-domain'
// |    `--- ':judymall-core'
// `--- Project ':judymall-core'
include("judymall-core")
include("judymall-domain")
include("judymall-front")
