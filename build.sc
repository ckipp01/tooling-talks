import mill._, scalalib._, scalafmt._

object site extends ScalaModule with ScalafmtModule {
  def scalaVersion = "2.13.7"

  def scalacOptions = Seq("-Wunused")

  def ivyDeps = Agg(
    ivy"com.lihaoyi::scalatags:0.11.0",
    ivy"tech.sparse::toml-scala:0.2.2",
    ivy"com.outr::scribe:3.6.4"
  )
}
