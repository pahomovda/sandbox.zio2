ThisBuild / organization := "com.github.ymatigoosa"
ThisBuild / version := "0.0.1"
ThisBuild / scalaVersion := "3.2.1"

lazy val foundationReloadableConfig = project
  .in(file("foundation/reloadable-config"))
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
    libraryDependencies ++= Dependency.zioConfig,
    libraryDependencies ++= Dependency.zioConfigTypesafeConfig,
  )

lazy val foundationBulkhead = project
  .in(file("foundation/bulkhead"))
  .dependsOn(foundationReloadableConfig)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
  )

lazy val foundationServer = project
  .in(file("foundation/server-zio-http"))
  .dependsOn(foundationReloadableConfig)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
    libraryDependencies ++= Dependency.tapirJsonZio,
    libraryDependencies ++= Dependency.tapirZio,
    libraryDependencies ++= Dependency.tapirZioHttpServer,
  )



lazy val foundationHttpClientApi = project
  .in(file("foundation/http-client-api"))
  .dependsOn(foundationReloadableConfig)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
    libraryDependencies ++= Dependency.sttpCore,
  )

lazy val foundationHttpClient = project
  .in(file("foundation/http-client"))
  .dependsOn(foundationHttpClientApi, foundationReloadableConfig)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
    libraryDependencies ++= Dependency.sttpZioHttp,
  )

lazy val integrationCommon = project
  .in(file("integration/common"))
  .dependsOn(foundationHttpClientApi)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
  )

lazy val integrationGeo = project
  .in(file("integration/geo"))
  .dependsOn(foundationHttpClientApi, integrationCommon)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
  )

lazy val integrationWeather = project
  .in(file("integration/weather"))
  .dependsOn(foundationHttpClientApi, integrationCommon)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
  )

lazy val modulePlaces = project
  .in(file("module/places"))
  .dependsOn(integrationGeo)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
  )

lazy val moduleWeather = project
  .in(file("module/weather"))
  .dependsOn(integrationWeather)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
  )

lazy val moduleTodoList = project
  .in(file("module/todolist"))
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
  )

lazy val serviceTodoListApi = project
  .in(file("service/todolist-api"))
  .settings(
    libraryDependencies ++= Dependency.tapirCore,
    libraryDependencies ++= Dependency.tapirJsonZio,
  )

lazy val serviceTodoListApp = project
  .in(file("service/todolist-app"))
  .dependsOn(
    // tech
    foundationReloadableConfig,
    foundationBulkhead,
    foundationServer,
    foundationHttpClientApi,
    foundationHttpClient,
    // api
    serviceTodoListApi,
    // logic modules
    modulePlaces,
    moduleTodoList,
    moduleWeather,
  )
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
    libraryDependencies ++= Dependency.tapirJsonZio,
    libraryDependencies ++= Dependency.tapirZio,
    libraryDependencies ++= Dependency.tapirZioHttpServer,
  )
