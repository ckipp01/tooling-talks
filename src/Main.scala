//> using scala "2.13.8"
//> using lib "com.lihaoyi::scalatags:0.11.1"
//> using lib "tech.sparse::toml-scala:0.2.2"
//> using lib "com.outr::scribe:3.10.1"
//> using option "-Wunused"
//> using resourceDir "./resources"

import scalatags.Text.all._
import scala.io.Source
import toml.Codecs._
import java.io.File
import java.io.PrintWriter
import java.nio.file.Files
import Episode._

object Main extends App {

  scribe.info("Slurping up your episodes file")

  val rawEpisodes = Source.fromResource("episodes.toml").mkString

  val parsed = toml.Toml.parseAs[EpisodeList](rawEpisodes)

  parsed match {
    case Left(err) =>
      scribe.error(err._2)
    case Right(episodeList) =>
      val episodesHtml =
        episodeList.episodes.reverse.map(Html.createEpisodeBlock)

      val episodePages = episodeList.episodes.map { episode =>
        HtmlPage(
          s"episode-${episode.number}.html",
          Html.episodePage(episode).render
        )
      }

      val homepage =
        HtmlPage(
          "index.html",
          Html
            .homePage(
              episodesHtml
            )
            .render
        )

      Files.createDirectories(new File("out", "images").toPath())

      def cleanup(file: File): Unit = {
        file.listFiles().foreach { file =>
          if (file.isDirectory()) {
            scribe.info(s"${file.getName()} is a directory, so cleaning it...")
            cleanup(file)
          } else {
            val deleted = file.delete()
            if (deleted) {
              scribe.info(s"Removed ${file.getName()}")
            } else {
              scribe.warn(s"Unable to remove ${file.getName()}... idn why")
            }
          }
        }
      }

      cleanup(new File("out"))

      (homepage :: episodePages).foreach { page =>
        scribe.info(s"Writing ${page.fileName}")
        val out = new File(s"./out/${page.fileName}")
        val pw = new PrintWriter(out)
        pw.write(page.content)
        pw.close()
      }

      new File("images").listFiles().foreach { image =>
        scribe.info(s"copying over ${image.getName()} to out/images")
        Files.copy(
          image.toPath(),
          new File(s"out/images/${image.getName()}").toPath()
        )
      }

      scribe.info("copying over your vercel file")
      Files.copy(
        new File("vercel.json").toPath(),
        new File("out/vercel.json").toPath()
      )

      scribe.info("Site is built!")
  }
}
