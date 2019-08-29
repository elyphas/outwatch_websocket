
package manik1.manik1.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>"""),_display_(/*6.17*/message),format.raw/*6.24*/("""</title>
        <link rel="stylesheet" href="/assets/stylesheets/main.min.css">
    </head>
    <body>
        <div id="root">
        </div>
        """),_display_(/*12.10*/scalajs/*12.17*/.html.scripts("client", name => s"/assets/$name", name => getClass.getResource(s"/public/$name") != null)),format.raw/*12.122*/("""
    """),format.raw/*13.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Aug 29 13:49:59 CDT 2019
                  SOURCE: /home/elyphas/Prjs/outwatch_websocket/server/src/main/twirl/manik1/manik1/index.scala.html
                  HASH: f00ae8248bf52360a43506da92256c9eda88f233
                  MATRIX: 574->1|685->19|712->20|788->70|815->77|994->229|1010->236|1137->341|1169->346
                  LINES: 14->1|19->2|20->3|23->6|23->6|29->12|29->12|29->12|30->13
                  -- GENERATED --
              */
          