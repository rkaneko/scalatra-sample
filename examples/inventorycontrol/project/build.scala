import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object InventorycontrolBuild extends Build {
  val Organization = "com.rkaneko"
  val Name = "InventoryControl"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.2"
  val ScalatraVersion = "2.2.1"

  lazy val project = Project (
    "inventorycontrol",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
        ,
        "com.github.seratch" %% "scalikejdbc"  % "[1.6,)",
        "com.github.seratch" %% "scalikejdbc-interpolation" % "[1.6,)",
        "joda-time" % "joda-time" % "2.3",
        "org.slf4j"          %  "slf4j-simple" % "[1.7,)",
        "com.h2database"     %  "h2"           % "[1.3,)",
        "org.specs2"         %% "specs2"       % "1.14" % "test",
        "org.scalatra" %% "scalatra-json" % "2.2.1",
        "org.json4s"   %% "json4s-jackson" % "3.2.4",
        "org.json4s" % "json4s-ext_2.10" % "3.2.4"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
