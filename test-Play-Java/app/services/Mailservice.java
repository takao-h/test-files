package services;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruth on 2017/12/17.
 */
public class Mailservice {
    @Inject MailerClient mailerClient;

    public void sendEmail() {
        String cid = "1234";
        Email email = new Email()
                .setSubject("Simple email")
                .setFrom("Mister FROM <ht.almond.dyt@email.com>")
                .addTo("Miss TO <hayashi_talao@gmail.com>")
                // adds attachment
//                .addAttachment("attachment.pdf", new File("/some/path/attachment.pdf"))
                // adds inline attachment from byte array
//                .addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE)
                // adds cid attachment
//                .addAttachment("image.jpg", new File("/some/path/image.jpg"), cid)
                // sends text, HTML or both...
                .setBodyText(Alert());
//                .setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>");
        mailerClient.send(email);
    }


    private String Alert(){
        return "this is Alert mail";
    }

    private List<String> nonEntryList(){
        List <String> nonEntry = new ArrayList<>();
        nonEntry.add("hoge");
        nonEntry.add("hige");
        return nonEntry;
    }
}
