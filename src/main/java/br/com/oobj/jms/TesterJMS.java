package br.com.oobj.jms;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TesterJMS {
	
	private static ApplicationContext context;
	
	static {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static void main(String[] args) {
		EnviarEmailJMSProducer produtor = (EnviarEmailJMSProducer) context.getBean("enviarEmailJMSProducer");
		
		String mensagem = JOptionPane.showInputDialog("Informe a mensagem a ser enviada por e-mail:");
		
		for (int i = 0; i < 50; i++) {
			produtor.enviarEmail(mensagem, "atilla8huno@gmail.com", "Contato via Portal");
		}
	}
}
