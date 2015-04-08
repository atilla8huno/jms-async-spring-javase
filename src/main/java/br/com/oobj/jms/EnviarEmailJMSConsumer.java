package br.com.oobj.jms;


import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("all")
public class EnviarEmailJMSConsumer implements MessageListener {

	@Autowired
	private EnvioDeEmailService envioDeEmailService;
	
	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;
		
		try {
			List<String> listaDeEmails = (ArrayList<String>) mapMessage.getObject("enviarPara");
			String corpoDoEmail = mapMessage.getString("corpoDoEmail");
			String assunto = mapMessage.getString("assuntoDoEmail");
			
			String[] enviaPara = listaDeEmails.toArray(new String[]{});

			envioDeEmailService.enviarEmail(corpoDoEmail, enviaPara, assunto);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
