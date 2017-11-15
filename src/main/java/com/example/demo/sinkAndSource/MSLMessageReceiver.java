package com.example.demo.sinkAndSource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.example.demo.generated.avro.AvroCar;
import com.example.demo.util.KafkaUtils;
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

		KafkaUtils codec = new KafkaUtils();
		AvroCar payloadCar = null;
		try {
			//payloadCar = codec.decode(payload, AvroCar.class);
			payloadCar = (AvroCar) converter.fromMessage(message, AvroCar.class);
			System.out.println(payloadCar);
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		System.out.println("Could Car be converted back to Avro? " + (payloadCar != null));
		String jsonCar = "NULL CAR";
		if(payloadCar != null) {
			Car car = new Car(payloadCar.getYear().toString(),
					payloadCar.getMake().toString(),
					payloadCar.getModel().toString(),
					payloadCar.getEngine().toString());
			try{
				jsonCar =  mapper.writeValueAsString(car);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println("Received: " + jsonCar);
	}
	
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
