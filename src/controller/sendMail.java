package controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendMail {

	private static String str;

	public static String getStr() {
		return str;
	}

	public static void setStr(String str) {
		sendMail.str = str;
	}

	public static String send(String to) {
		str = "";
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
			str = str + (rand.nextInt(9)); // Số ngẫu nhiên từ 0 - 9
		}
		final String user = "22520756@gm.uit.edu.vn";// gmail người gửi
		final String pass = "agdj nkne hovq ezyf";
		String sub = "OTP HRM";
		String msg = str + " là mã OTP của bạn";
		Properties props = System.getProperties();
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setContent(msg, "text/html; charset=UTF-8");
			Transport.send(message);
			System.out.println("Thành công!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return str;
	}

}
