import Dependencies._

scalaVersion := "2.11.5"

val okcorpus = project.in(file(".")).enablePlugins(WebappPlugin)

organization := "org.allenai"

name := "okcorpus"

homepage := Some(url("https://okcorpus.dev.allenai.org"))

description := "buildin' them electric tables"

import sbtrelease.ReleaseStateTransformations._

// Override the problematic new release plugin.
ReleaseKeys.releaseProcess := Seq(
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion,
  pushChanges
)

scmInfo := Some(ScmInfo(
  url("https://github.com/allenai/okcorpus"),
  "https://github.com/allenai/okcorpus.git"))

pomExtra :=
  <developers>
    <developer>
      <id>allenai-dev-role</id>
      <name>Allen Institute for Artificial Intelligence</name>
      <email>dev-role@allenai.org</email>
    </developer>
  </developers>

PublishTo.ai2Private

libraryDependencies ++= Seq(
    allenAiCommon,
    allenAiTestkit,
    allenAiDatastore,
    nlpstackModule("tokenize") exclude("org.allenai", "datastore_2.11"),
    nlpstackModule("postag") exclude("org.allenai", "datastore_2.11"),
    nlpstackModule("chunk") exclude("org.allenai", "datastore_2.11"),
    nlpstackModule("lemmatize") exclude("org.allenai", "datastore_2.11"),
    nlpstackModule("segment") exclude("org.allenai", "datastore_2.11"),
    lucene("core"),
    lucene("analyzers-common"),
    lucene("highlighter"),
    lucene("queries"),
    lucene("queryparser"),
    "com.typesafe.slick" %% "slick" % "2.1.0",
    "com.github.tminglei" %% "slick-pg" % "0.8.2",
    "com.typesafe.play" %% "play-json" % "2.3.8",
    "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
    "nl.inl" %% "blacklab" % "1.0-ALLENAI-5",
    "com.medallia.word2vec" % "Word2VecJava" % "0.10.3",
    "com.google.guava" % "guava" % "18.0",
    "org.apache.thrift" % "libthrift" % "0.9.1",
    sprayModule("caching"),
    "com.papertrailapp" % "logback-syslog4j" % "1.0.0",
    scopt)

javaOptions in Revolver.reStart += "-Xmx14G"

mainClass in Revolver.reStart := Some("org.allenai.dictionary.DictionaryToolWebapp")

fork in run := true

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

conflictManager := ConflictManager.default

dependencyOverrides ++= Set(
  "org.allenai.common" %% "common-core" % "1.0.13",
  sprayJson,
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.2",
  "commons-codec" % "commons-codec" % "1.6",
  "org.apache.commons" % "commons-compress" % "1.8",
  "org.scala-lang" % "scala-reflect" % "2.11.5"
)

enablePlugins(ReleasePlugin)
