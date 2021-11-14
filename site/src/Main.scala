package site

import scalatags.Text.all._
import scala.io.Source
import toml.Codecs._
import java.io.File
import java.io.PrintWriter
import java.nio.file.Files
import Episode._

object Main extends App {

  scribe.info("Slurping up your episodes file")

  val rawEpisodes = Source.fromFile("episodes.toml").mkString

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

      Files.createDirectories(new File("site-out", "images").toPath())

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

      cleanup(new File("site-out"))

      (homepage :: episodePages).foreach { page =>
        scribe.info(s"Writing ${page.fileName}")
        val out = new File(s"./site-out/${page.fileName}")
        val pw = new PrintWriter(out)
        pw.write(page.content)
        pw.close()
      }

      new File("images").listFiles().foreach { image =>
        scribe.info(s"copying over ${image.getName()} to site-out/images")
        Files.copy(
          image.toPath(),
          new File(s"site-out/images/${image.getName()}").toPath()
        )
      }

      scribe.info("copying over the vercel file")
      Files.copy(
        new File("vercel.json").toPath(),
        new File("site-out/vercel.json").toPath()
      )

      scribe.info("Site is built!")
  }
}
