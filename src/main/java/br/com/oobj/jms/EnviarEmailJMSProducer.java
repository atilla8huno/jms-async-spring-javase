package br.com.oobj.jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

public class EnviarEmailJMSProducer extends JmsGatewaySupport {
	
	private Destination destination;

	/**
	 * Spring injetará esse cara... 
	 * 
	 * @param destination
	 */
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void enviarEmail(String corpoDoEmail, String enviarPara, String assuntoDoEmail) {
		List<String> emails = new ArrayList<String>();
		emails.add(enviarPara);

		enviarEmail(corpoDoEmail, emails, assuntoDoEmail);
	}

	public void enviarEmail(final String corpoDoEmail, final List<String> emails, final String assuntoDoEmail) {
		getJmsTemplate().setReceiveTimeout(JmsTemplate.RECEIVE_TIMEOUT_NO_WAIT);
		getJmsTemplate().setDeliveryMode(DeliveryMode.PERSISTENT);

		// cria um MapMessage
		MessageCreator messageCreator = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setObject("enviarPara", emails);
				message.setString("corpoDoEmail", corpoDoEmail);
				message.setString("assuntoDoEmail", assuntoDoEmail);

				return message;
			}
		};

		getJmsTemplate().send(destination, messageCreator);
	}
}
