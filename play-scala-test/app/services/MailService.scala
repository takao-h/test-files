import play.api.libs.mailer._
import java.io.File

import org.apache.commons.mail.EmailAttachment
import javax.inject.Inject

import akka.stream.stage.Context
import com.google.inject.Provider
import com.sun.deploy.Environment
import com.sun.tools.doclets.internal.toolkit.Configuration
import play.{ApplicationLoader, Routes}
import play.api.BuiltInComponentsFromContext

class MailerService @Inject()(mailerClient: MailerClient) {

  def sendEmail = {
    val cid = "1234"
    val email = Email(
      "Simple email",
      "Mister FROM <from@email.com>",
      Seq("Miss TO <to@email.com>"),
      // adds attachment
      attachments = Seq(
        AttachmentFile("attachment.pdf", new File("/some/path/attachment.pdf")),
        // adds inline attachment from byte array
        AttachmentData("data.txt", "data".getBytes, "text/plain", Some("Simple data"), Some(EmailAttachment.INLINE)),
        // adds cid attachment
        AttachmentFile("image.jpg", new File("/some/path/image.jpg"), contentId = Some(cid))
      ),
      // sends text, HTML or both...
      bodyText = Some("A text message"),
      bodyHtml = Some(s"""<html><body><p>An <b>html</b> message with cid <img src="cid:$cid"></p></body></html>""")
    )
    mailerClient.send(email)
  }
}

class MyComponent(mailerClient: MailerClient) {

  def sendEmail = {
    val email = Email("Simple email", "Mister FROM <from@email.com>", Seq("Miss TO <to@email.com>"), bodyText = Some("A text message"))
    mailerClient.send(email)
  }

}


class MyApplicationLoader extends ApplicationLoader {
  def load(context: Context) = {
    new ApplicationComponents(context).application
  }
}

class ApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context) with MailerComponents {
  lazy val myComponent = new MyComponent(mailerClient)
  // create your controllers here ...
  lazy val router = new Routes(...) // inject your controllers here
}

class CustomSMTPConfigurationProvider extends Provider[SMTPConfiguration] {
  override def get() = {
    // Custom configuration
    new SMTPConfiguration("typesafe.org", 1234)
  }
}

class CustomMailerConfigurationModule extends Module {
  def bindings(environment: Environment, configuration: Configuration) = Seq(
    bind[SMTPConfiguration].toProvider[CustomSMTPConfigurationProvider]
  )
}
