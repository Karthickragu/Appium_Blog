package mobile.controlpoint.utilites;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import mobile.controlpoint.reusable.GlobalConstants;

import javax.activation.*;

public class TriggerMail {

	public void sendExecutionStatus(int total, int passed, int failed, int skipped) throws IOException {
		// Recipient's email ID needs to be mentioned.

		// Sender's email ID needs to be mentioned
		String from = "ragukar@gmail.com";

		// Assuming you are sending email from localhost
		String host = "smtp.complany.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		// properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties, null);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			Address[] addresses = new Address[7];
			addresses[0] = new InternetAddress("");
			addresses[1] = new InternetAddress("");
			addresses[2] = new InternetAddress("");
			addresses[3] = new InternetAddress("");
			addresses[4] = new InternetAddress("");
			addresses[5] = new InternetAddress("");
			addresses[6] = new InternetAddress("");

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.TO, addresses);


			// Set Subject: header field
			message.setSubject("Automation report");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message

			messageBodyPart.setContent("" + "<!DOCTYPE html>" + "<html>" + "<head>" + "<style>"
					+ "table, th, td {  border: 1px solid black;}" + "</style>" + "</head>" + "<body>"
					+ "<p>Hi All,</p>"

					+ "<p>Please find below Test Exectuion status for Health check,</p> " + "<table style=wdth:100%>"
					+ " <tr>" + "<th>S.no</th>" + "<th>Total</th>" + "<th>Passed</th>" + "<th>Failed</th>"
					+ "<th>Skipped</th>" + "</tr>" + "<tr>" + "<td>1</td>" + "<td>" + total + "</td> " + "<td>" + passed
					+ "</td>" + "<td>" + failed + "</td>" + "<td>" + skipped + "</td>" + "</tr>" + "</table>"
					+ "<p>Regards,</p>" + "<p>Ragav</p>" + "</body>" + "</html>", "text/html");
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String path = new File(".").getCanonicalPath();
			String filename = path + GlobalConstants.testReportLocation + "_TestReport" + ".html";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("TestReport.html");
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts

			messageBodyPart = new MimeBodyPart();
			String filename1 = path + GlobalConstants.pdfreportLocation + "TestReport.pdf";
			DataSource source1 = new FileDataSource(filename1);
			messageBodyPart.setDataHandler(new DataHandler(source1));
			messageBodyPart.setFileName("TestReport1.pdf");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
