import scalatags.Text.all._
import java.io.File
import toml.Codec
import toml.Value

case class Episode(
    guest: Option[String],
    title: String,
    image: File,
    thumbnail: File,
    player: String,
    twitter: Option[String],
    github: Option[String],
    website: Option[String],
    season: Int,
    number: Int,
    notes: String,
    links: Option[List[Link]]
)

case class Link(name: String, link: String)

object Episode {
  implicit val fileCodec: Codec[File] = Codec {
    case (Value.Str(value), _, _) if new File(value).exists =>
      Right(new File(value))
    case (Value.Str(value), _, _) => Left(List(), s"$value seems to not exist")
    case (_, _, _)                => Left(List(), s"$value must be a string")
  }
}

case class EpisodeList(episodes: List[Episode])

case class HtmlPage(fileName: String, content: String)
