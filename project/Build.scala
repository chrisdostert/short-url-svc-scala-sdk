import java.time.Instant

import sbt.Keys._
import sbt._
import scoverage.ScoverageKeys._


object Build extends Build with LibraryDependencyVersions {

  lazy val commonSettings =
    Seq(
      credentials += Credentials(
        "Bintray API Realm",
        "api.bintray.com",
        sys.env.getOrElse(
          "BINTRAY_USERNAME",
          ""
        ),
        sys.env.getOrElse(
          "BINTRAY_API_KEY",
          ""
        )
      ),
      pomExtra :=
        (
          <scm>
            <url>https://github.com/urlable/short-url-svc-scala-sdk</url>
            <connection>scm:git:git@github.com:urlable/short-url-svc-scala-sdk.git</connection>
          </scm>
            <developers>
              <developer>
                <id>chrisdostert</id>
                <name>Chris Dostert</name>
                <url>https://github.com/chrisdostert</url>
              </developer>
            </developers>
          ),
      pomIncludeRepository := { _ => false },
      publishMavenStyle := true,
      publishTo := Some("Bintray API Realm" at "https://api.bintray.com/maven/urlable/maven/short-url-svc-scala-sdk/;publish=1"),
      version := s"0.1.0+${Instant.now().getEpochSecond}",
      coverageEnabled in test := true,
      coverageMinimum := 90,
      coverageFailOnMinimum := true,
      homepage := Some(new URL("https://github.com/urlable/short-url-svc-scala-sdk")),
      licenses +=("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
      updateOptions := updateOptions
        .value
        .withCachedResolution(true),
      organization := "com.urlable",
      scalaVersion := "2.11.7",
      scalacOptions := Seq(
        "-unchecked",
        "-deprecation",
        "-encoding",
        "utf8"
      ),
      libraryDependencies ++=
        Seq(
          "org.testobjects" %% "test-objects-for-scala" % testObjectsForScalaVersion % "test,it",
          "org.scalatest" %% "scalatest" % scalaTestVersion % "test,it",
          "org.mockito" % "mockito-core" % mockitoVersion % "test,it",
          "com.iheart" %% "ficus" % ficusVersion % "test,it",
          "com.typesafe.akka" %% "akka-stream" % akkaStreamAndHttpVersion,
          "com.typesafe.akka" %% "akka-http-core" % akkaStreamAndHttpVersion,
          "com.typesafe.akka" %% "akka-http-experimental" % akkaStreamAndHttpVersion,
          "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamAndHttpVersion
        )
    ) ++
      Defaults
        .itSettings

  lazy val root =
    Project(
      id = "root",
      base = file(".")
    )
      .settings(
        commonSettings
      )
      .configs(IntegrationTest extend Test)

}