package com.rfid.bri.rfidbri;
import com.intermec.datacollection.rfid.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class RfidBriApplication {

	private static long savedTimeStamp;

	private static String savedAntenna;

	private static String savedTagData;

	private static AmqpTemplate queueSender;

	private static Map<String, Long> savedRecords = new HashMap<>();

	public RfidBriApplication(AmqpTemplate queueSender) {
		this.queueSender = queueSender;
	}

	public static void main(String[] args) throws BasicReaderException {
		SpringApplication.run(RfidBriApplication.class, args);

		BasicBRIReader reader = executeBri();
		BasicReaderEventListener listener = RfidBriApplication::handleEvent;
		reader.addReaderEventListener(listener);
	}

	private static BasicBRIReader executeBri() throws BasicReaderException {
		BasicBRIReader reader = new BasicBRIReader();
		reader.open("tcp://10.0.0.154");
		reader.execute("PING");
		reader.execute("ATTRIB ANTS=1,2");
		reader.execute("ATTRIB FIELDSTRENGTH=20DB,20DB,20DB,20DB");
		reader.execute("ATTRIB SELTRIES=1");
		reader.execute("ATTRIB ANTTRIES=3");
		reader.execute("ATTRIB IDTRIES=1");
		reader.execute("ATTRIB SCHEDULEOPT=0");
		reader.execute("ATTRIB INITTRIES=1");
		reader.execute("ATTRIB INITIALQ=4");
		reader.execute("ATTRIB SESSION=2");
		reader.execute("ATTRIB UNSELTRIES=1");
		reader.execute("READ ANT RSSI TIME TAGID REPORT=EVENTALL");
		return reader;
	}

	public static void handleEvent(BasicReaderEvent event) {

		String[] parts = event.getTagDataString().split(" ");
		long timestamp = Long.parseLong(parts[2]);
		String antenna = parts[0];
		String tagData = parts[3];
		String key = antenna + "-" + tagData;

		System.out.println("[LOG] Leitura realizada pela antena: " + antenna + " da TAG: " + tagData);
//		System.out.println(!savedRecords.containsKey(key));
//		System.out.println(savedRecords);
//		System.out.println(key);

		// Só adiciono na fila do RabbitMQ as tags que não foram lidas nos últimos 5 segundos ou que foram lidas em antenas diferentes
		if (!savedRecords.containsKey(key) || (timestamp - savedRecords.get(key)) > 5000) {

			if(savedRecords.containsKey(key) && (timestamp - savedRecords.get(key)) > 5000){
				// Limpa os dados da tag que não foram lidos nos últimos 5 segundos
				savedRecords.remove(key);
			}

			// Inserindo antena + tag + timestamp no map
			savedRecords.put(key, timestamp);

			System.out.println("[LOG] Enviando para a fila do RabbitMQ: " + antenna + " da TAG: " + tagData);
//			savedTimeStamp = timestamp;
//			savedAntenna = antenna;
//			savedTagData = tagData;
			send(tagData, antenna);
		}

	}

	public static void send(String tagData, String antenna){
//		System.out.println("Adicionada a fila TAGID: " + tagData + " Antena: " + antenna);

		String messageBody = tagData + " " + antenna;

		MessageProperties messageProperties = new MessageProperties();
		Message message = new Message(messageBody.getBytes(), messageProperties);

		queueSender.convertAndSend("direct-exchange", "rfid-routing-key", messageBody);
	}

}
