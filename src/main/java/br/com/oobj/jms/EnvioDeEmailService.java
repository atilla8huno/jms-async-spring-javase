package br.com.oobj.jms;

import org.springframework.stereotype.Service;

@Service
public class EnvioDeEmailService {

	@SuppressWarnings("static-access")
	public void enviarEmail(String corpoEmail, String[] destinatarios, String assunto) {
		StringBuilder log = new StringBuilder();
		
		log.append("Enviando email '")
				.append(assunto)
				.append("' para o(s) destinatário(s): \n\n");
				
		for (String destino : destinatarios) {
			log.append(destino + ", ");
		}
	
		log.append("\n\ncom o seguinte conteúdo: \n\n")
				.append(corpoEmail + "\n\n")
				.append("==========================\n\n")
				.append("Essa operação levará 5 segundos para ser concluída...\n");
		
		System.out.println(log.toString());
		
		try {
			System.out.println("Dormindo a thread " + Thread.currentThread().getName());
			Thread.currentThread().sleep(5 * 1000);
			System.out.println("Acordando a thread " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
		}
	}
}
