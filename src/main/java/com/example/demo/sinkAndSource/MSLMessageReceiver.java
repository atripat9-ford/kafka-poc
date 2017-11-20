package com.example.demo.sinkAndSource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.example.demo.generated.avro.FPMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(SinkAndSourceChannels.class)
public class MSLMessageReceiver {
	
	@Autowired
	AvroSchemaMessageConverter converter;
	
	@Autowired
	ObjectMapper mapper;
	
	@StreamListener(SinkAndSourceChannels.INPUT_CHANNEL_1)
    public void listenToChannel1(Message<byte[]> message) throws IOException {
		System.out.println("Received messsage: " + message);
		
	
		byte[] payload = message.getPayload();

		
		FPMessage payloadMessage = null;
		try {
			payloadMessage = (FPMessage) converter.fromMessage(message, FPMessage.class);
			System.out.println(payloadMessage);
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		System.out.println("is able to convert message to Avro? " + (payloadMessage != null));
		String jsonMessage = "NULL Message";
		if(payloadMessage != null) {
			BaseFPEvent baseFPEvent = new BaseFPEvent(payloadMessage.getEventType().toString(),
					payloadMessage.getEventId().toString(),
					payloadMessage.getUserGuid().toString(),
					payloadMessage.getVin().toString(),
					payloadMessage.getOriginator().toString(),
					payloadMessage.getPublisher().toString(),
					payloadMessage.getOriginationTimestamp().toString()
					);
			try{
				jsonMessage =  mapper.writeValueAsString(baseFPEvent);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println("Received: " + jsonMessage);
	}
	
	
	
	
	
//	@StreamListener(SinkAndSourceChannels.INPUT_CHANNEL_1)
//    public void listenToChannel1(Message<byte[]> message) throws IOException {
//		System.out.println("Received messsage: " + message);
//		
//	
//		byte[] payload = message.getPayload();
//
//		KafkaUtils codec = new KafkaUtils();
//		AvroCar payloadCar = null;
//		try {
//			//payloadCar = codec.decode(payload, AvroCar.class);
//			payloadCar = (AvroCar) converter.fromMessage(message, AvroCar.class);
//			System.out.println(payloadCar);
//		}catch (Exception ex) {
//			
//			ex.printStackTrace();
//		}
//		
//		System.out.println("Could Car be converted back to Avro? " + (payloadCar != null));
//		String jsonCar = "NULL CAR";
//		if(payloadCar != null) {
//			Car car = new Car(payloadCar.getYear().toString(),
//					payloadCar.getMake().toString(),
//					payloadCar.getModel().toString(),
//					payloadCar.getEngine().toString());
//			try{
//				jsonCar =  mapper.writeValueAsString(car);
//			} catch(Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//		
//		System.out.println("Received: " + jsonCar);
//	}
	
//	@StreamListener(SinkAndSourceChannels.INPUT_CHANNEL_1)
//    public void listenToChannel1(Message<byte[]> message) throws IOException {
//		System.out.println("Received messsage: " + message);
//		
//	
//		byte[] payload = message.getPayload();
//
//		System.out.println("Received payload messsage: " + new String(payload));
//		
//		KafkaUtils codec = new KafkaUtils();
//		AvroCar payloadCar = null;
//		try {
//			payloadCar = codec.decode(payload, AvroCar.class); 
//		}catch (Exception ex) {
//			
//			ex.printStackTrace();
//		}
//		
//		System.out.println("Could Car be converted back to Avro? " + (payloadCar != null));
//		String jsonCar = "NULL CAR";
//		if(payloadCar != null) {
//			Car car = new Car(payloadCar.getYear(),
//					payloadCar.getMake(),
//					payloadCar.getModel(),
//					payloadCar.getEngine());
//			try{
//				jsonCar =  mapper.writeValueAsString(car);
//			} catch(Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//		
//		System.out.println("Received: " + jsonCar);
//	}
}
