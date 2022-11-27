import scalatags.stylesheet._
import scalatags.Text.all._
import scalatags.Text.styles2

object Style extends CascadingStyleSheet {
  initStyleSheet()

  val cream = "rgb(252, 243, 217)"
  val gray = "rgb(106, 112, 110)"
  val black = "rgb(0, 0, 0)"

  def cascasdeRoot = cls(
    fontSize := 18,
    body(
      height := "100%",
      background := cream
    ),
    header(
      display.flex,
      flexDirection.column,
      justifyContent.center,
      alignItems.center,
      flexShrink := 0,
      marginTop := "1rem",
      h1(
        fontWeight.bold,
        margin := "1rem",
        fontSize := "3rem",
        textAlign.center
      )
    ),
    h1(
      fontWeight.bold,
      fontSize := "1.5rem"
    ),
    h2(
      fontWeight.bold,
      fontSize := "1.3rem",
      lineHeight := "2rem"
    ),
    h3(
      fontWeight.bold,
      fontSize := "1.2rem",
      lineHeight := "2rem"
    ),
    h4(
      fontWeight.bold,
      fontSize := "1.1rem",
      lineHeight := "2rem"
    ),
    Selector("main")(
      padding := "1rem",
      flexGrow := 1,
      lineHeight := "1.75rem",
      maxWidth := 800,
      margin := "0 auto"
    ),
    i(fontStyle.italic),
    footer(
      display.flex,
      justifyContent.center,
      margin := "5px 0",
      flexShrink := 0,
      a(
        width := 40
      ),
      img(
        padding := 10,
        maxHeight := 20,
        margin := "5px 0"
      )
    ),
    li(
      marginLeft := "20px",
      listStyleType.circle
    )
  )

  def iconContainer = cls(
    display.flex,
    justifyContent.center,
    a(
      width := 40
    ),
    img(
      padding := 10,
      maxHeight := 20,
      margin := "5px 0"
    )
  )

  def scaleOnHover = cls.hover(
    styles2.transform := "scale(1.1)"
  )

  def wrapper = cls(
    minHeight := "100%",
    display.flex,
    flexDirection.column
  )

  def episode = cls(
    maxWidth := 200
  )

  def episodeContainer = cls(
    margin := "1rem",
    display.flex,
    flexDirection.column,
    alignItems.center,
    maxWidth := 225,
    h1(textAlign.center),
    p(textAlign.center)
  )

  def episodesContainer = cls(
    display.flex,
    justifyContent.center,
    alignItems.center,
    flexWrap.wrap
  )

  def maxAndCenter = cls(
    maxWidth := 750,
    margin := "5px auto"
  )

  def showLinks = cls(
    a(
      borderBottomWidth := "2px",
      borderBottomStyle.dashed,
      &.hover(background := black, color := cream)
    )
  )

  def subText = cls(
    fontSize := "0.8rem",
    color := gray
  )

  // There isn't a good way to do things like @import or * with scalatags, so
  // we just do it raw here.
  lazy val raw = s"""* {
               |  margin:0;
               |  padding:0;
               |  border:0;
               |  outline:0;
               |  border-spacing:0;
               |  text-decoration:none;
               |  font-weight:inherit;
               |  font-style:inherit;
               |  color:inherit;
               |  font-size:100%;
               |  font-family:inherit;
               |  vertical-align:baseline;
               |  list-style:none;
               |  border-collapse:collapse;
               |  -webkit-font-smoothing: antialiased;
               |  -moz-osx-font-smoothing: grayscale;
               |  }
               |
               |  @import url('https://rsms.me/inter/inter.css');
               |  html { font-family: 'Inter', sans-serif; }
               |  @supports (font-variation-settings: normal) {
               |    html { font-family: 'Inter var', sans-serif; }
               |  }
               |
               |*:focus {
               |  border: 1px dotted black;
               |}""".stripMargin

}
