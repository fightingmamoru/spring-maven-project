package org.mamoru.common.service;

import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import java.util.Properties;

public class MailServiceTest
{
	private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

//	private String host = "smtp.gmail.com";
//	private int port = 587;
//	private String username = "fightingmamoru@gmail.com";
//	private String password = "ma131026mo*";


	// naver (SSL)
//	private String host = "smtp.naver.com";
//	private int port = 465;
//	private String username = "mamoru83@naver.com";
//	private String password = "ma131026mo*";


	// naver (TLS)
//	private String host = "smtp.naver.com";
//	private int port = 587;
//	private String username = "mamoru83@naver.com";
//	private String password = "ma131026mo*";


	// outlook (TSL)
//	private String host = "smtp-mail.outlook.com";
//	private int port = 587;
//	private String username = "fightingmamoru@outlook.com";
//	private String password = "ma131026mo*";


//	// nate (SSL)
//	private String host = "smtp.mail.nate.com";
//	private int port = 465;
//	private String username = "fightingmamoru@nate.com";
//	private String password = "ma131026mo*";


	// daum (SSL)
	private String host = "smtp.hanmail.net";
	private int port = 465;
	private String username = "ubi-kimjb";
	private String password = "kimjb132!";


	@Test
	public void test() throws MessagingException
	{
		Properties mailProperties = new Properties();

		// Default (TLS)
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.debug", "true");

		// SSL (added)
		mailProperties.put("mail.smtp.ssl.enable", "true");

		// SSL
//		mailProperties.put("mail.smtp.socketFactory.port", "465");
//		mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		mailProperties.put("mail.smtp.socketFactory.fallback", "false");
//		mailProperties.put("mail.smtps.ssl.checkserveridentity", "true");
//		mailProperties.put("mail.smtps.ssl.trust", "*");
//		mailProperties.put("mail.from.email", "fightingmamoru@gmail.com");

//		mailProperties.put("mail.smtps.ssl.checkserveridentity", "true");
//		mailProperties.put("mail.smtps.ssl.trust", "*");
//		mailProperties.put("mail.from.email", "mamoru83@naver.com");


		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		mailSender.setJavaMailProperties(mailProperties);

		mailSender.testConnection();

		mailSender.setDefaultEncoding("UTF-8");

		// todo : 메일발송 테스트
		SimpleMailMessage message = new SimpleMailMessage();

//		message.setTo("ubi-kimjb@daum.net");
//		message.setSubject("gmail에서 발송한 테스트 메일");
//		message.setText("gmail에서 발송한 테스트 메일의 본문입니다.");

//		message.setFrom("mamoru83@naver.com");
//		message.setTo("ubi-kimjb@daum.net");
//		message.setSubject("naver에서 발송한 테스트 메일");
//		message.setText("naver에서 발송한 테스트 메일의 본문입니다.");

//		message.setFrom("fightingmamoru@outlook.com");
//		message.setTo("ubi-kimjb@daum.net");
//		message.setSubject("outlook에서 발송한 테스트 메일");
//		message.setText("outlook에서 발송한 테스트 메일의 본문입니다.");

//		message.setFrom("fightingmamoru@nate.com");
//		message.setTo("ubi-kimjb@daum.net");
//		message.setSubject("nate에서 발송한 테스트 메일");
//		message.setText("nate에서 발송한 테스트 메일의 본문입니다.");

		message.setFrom("ubi-kimjb@daum.net");
		message.setTo("mamoru83@naver.com");
		message.setSubject("daum에서 발송한 테스트 메일");
		message.setText("daum에서 발송한 테스트 메일의 본문입니다.");

		mailSender.send(message);
	}
}