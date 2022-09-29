import scalatags.Text.all._
import scalatags.Text

object Html {

  def createEpisodeBlock(episode: Episode) = {
    a(
      href := s"episode-${episode.number.toString()}.html",
      div(
        Style.episodeContainer,
        Style.scaleOnHover,
        img(
          Style.episode,
          src := s"images/${episode.image.getName()}",
          alt := s"""${episode.guest.getOrElse("Tooling Talks")} avatar"""
        ),
        createEpisodeTitle(episode)
      )
    )
  }

  def homePage(episodesHtml: Modifier) = {
    doctype("html")(
      html(
        lang := "en",
        Style.cascasdeRoot,
        headFrag(
          "Tooling Talks",
          "Tooling Talks podcast - A series of talks about Scala and Tooling.",
          s"http://www.tooling-talks.com/images/logo-thumbnail.jpg"
        ),
        body(
          div(
            Style.wrapper,
            headerFrag(),
            Text.tags2.main(
              div(Style.episodesContainer, episodesHtml)
            ),
            footerFrag()
          )
        )
      )
    )
  }

  def episodePage(episode: Episode) = {
    doctype("html")(
      html(
        lang := "en",
        Style.cascasdeRoot,
        headFrag(
          s"Tooling Talks - ${episode.title}",
          s"Tooling Talks episode ${episode.number} -  ${episode.guest
              .map(_ + ": ")
              .getOrElse("")}${episode.title}",
          s"https://www.tooling-talks.com/images/${episode.thumbnail.getName()}"
        ),
        body(
          div(
            Style.wrapper,
            headerFrag(),
            Text.tags2.main(
              div(
                Style.maxAndCenter,
                episodePageTitle(episode),
                div(
                  p(
                    Style.subText,
                    s"SEASON: ${episode.season.toString()} - EPISODE: ${episode.number.toString()}"
                  )
                )
              ),
              raw(episode.player),
              div(
                Style.maxAndCenter,
                Style.iconContainer,
                socialIcons(episode)
              ),
              h3(Style.maxAndCenter, "INTRO"),
              p(Style.maxAndCenter, episode.notes),
              episode.links match {
                case None => ()
                case Some(links) =>
                  div(
                    h4(Style.maxAndCenter, "SHOW LINKS"),
                    ul(
                      links.map(link =>
                        li(
                          Style.maxAndCenter,
                          a(
                            href := link.link,
                            target := "_blank",
                            rel := "noopener noreferrer"
                          )(p(Style.maxAndCenter, link.name))
                        )
                      )
                    )
                  )
              },
              a(
                href := "https://github.com/sponsors/ckipp01",
                target := "_blank",
                rel := "noopener noreferrer"
              )(p(Style.subText, "SUPPORT THE SHOW"))
            ),
            footerFrag()
          )
        )
      )
    )
  }

  private def headFrag(
      pageTitle: String,
      description: String,
      thumbnail: String
  ) = {
    head(
      meta(charset := "utf-8"),
      meta(
        name := "viewport",
        content := "width=device-width, initial-scale=1"
      ),
      meta(name := "description", content := description),
      meta(
        name := "keywords",
        content := "Tooling Talks, Chris Kipp, ckipp01, ckipp, Scala, Developer Tooling"
      ),
      meta(
        name := "thumbnail",
        content := thumbnail
      ),
      meta(
        name := "og:type",
        content := "website"
      ),
      meta(
        name := "og:title",
        content := "Tooling Talks"
      ),
      meta(
        name := "og:image",
        content := thumbnail
      ),
      meta(
        name := "og:description",
        content := description
      ),
      link(
        rel := "icon",
        href := "images/favicon.ico",
        `type` := "image/x-icon"
      ),
      Text.tags2.title(pageTitle),
      Text.tags2.style(Style.raw),
      Text.tags2.style(Style.styleSheetText),
      script(
        defer := true,
        `type` := "text/javascript",
        src := "https://api.pirsch.io/pirsch.js",
        id := "pirschjs",
        attr("data-code") := "vr9RbZIuF6o05rD9zTwRfeRzbbxGMVhb"
      )
    )
  }

  private def headerFrag() = {
    header(
      a(href := "index.html")(h1("TOOLING TALKS")),
      i("a series of talks about scala and tooling"),
      div(
        Style.iconContainer,
        a(
          href := "https://open.spotify.com/show/1OjXS5IBeP4Jku4Ue54wFA",
          target := "_blank",
          rel := "noopener noreferrer"
        )(
          img(
            Style.scaleOnHover,
            src := "../images/spotify.svg",
            alt := "Spotify logo"
          )
        ),
        a(
          href := "https://podcasts.apple.com/us/podcast/tooling-talks/id1580009576",
          target := "_blank",
          rel := "noopener noreferrer"
        )(
          img(
            Style.scaleOnHover,
            src := "../images/apple-podcast.svg",
            alt := "Apple Podcast logo"
          )
        ),
        a(href := "https://www.deezer.com/show/2874482")(
          img(
            Style.scaleOnHover,
            src := "../images/deezer-logo.svg",
            alt := "Deezer logo"
          )
        ),
        a(
          href := "https://podcasts.google.com/feed/aHR0cHM6Ly9mZWVkcy5idXp6c3Byb3V0LmNvbS8xODMwOTM2LnJzcw==",
          target := "_blank",
          rel := "noopener noreferrer"
        )(
          img(
            Style.scaleOnHover,
            src := "../images/google-podcast.svg",
            alt := "Google Podcasts logo"
          )
        ),
        a(href := "https://feeds.buzzsprout.com/1830936.rss")(
          img(
            Style.scaleOnHover,
            src := "../images/rss.svg",
            alt := "rss feed logo"
          )
        )
      )
    )
  }

  private def footerFrag() = {
    footer(
      a(
        href := "https://github.com/ckipp01/tooling-talks",
        target := "_blank",
        rel := "noopener noreferrer"
      )(
        img(
          Style.scaleOnHover,
          src := "../images/github.svg",
          alt := "GitHub logo"
        )
      ),
      a(href := "mailto:tooling-talks@chris-kipp.io")(
        img(
          Style.scaleOnHover,
          src := "../images/email.svg",
          alt := "email logo"
        )
      ),
      a(
        href := "https://twitter.com/ckipp01",
        target := "_blank",
        rel := "noopener noreferrer"
      )(
        img(
          Style.scaleOnHover,
          src := "../images/twitter.svg",
          alt := "Twitter logo"
        )
      )
    )
  }

  private def episodePageTitle(episode: Episode) =
    episode.guest match {
      case Some(guest) => h2(s"$guest: ${episode.title}")
      case None        => h2(episode.title)
    }

  private def socialIcons(episode: Episode) = {
    List(
      episode.github.map { github =>
        a(href := github, target := "_blank", rel := "noopener noreferrer")(
          img(
            Style.scaleOnHover,
            src := "../images/github.svg",
            alt := "GitHub logo"
          )
        )
      },
      episode.twitter.map { twitter =>
        a(href := twitter, target := "_blank", rel := "noopener noreferrer")(
          img(
            Style.scaleOnHover,
            src := "../images/twitter.svg",
            alt := "Twitter logo"
          )
        )
      },
      episode.website.map { website =>
        a(href := website, target := "_blank", rel := "noopener noreferrer")(
          img(
            Style.scaleOnHover,
            src := "../images/globe.svg",
            alt := "Website logo"
          )
        )
      }
    ).flatten
  }

  private def createEpisodeTitle(episode: Episode) = {
    if (episode.guest.nonEmpty)
      div(h1(episode.guest.get), p(i(episode.title)))
    else
      div(h1(episode.title))
  }

}
