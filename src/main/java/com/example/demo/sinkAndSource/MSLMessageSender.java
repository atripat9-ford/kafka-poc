package com.example.demo.sinkAndSource;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.example.demo.generated.avro.FPMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(SinkAndSourceChannels.class)
public class MSLMessageSender {

	@Autowired
	private SinkAndSourceChannels output;
	
	@Autowired
	AvroSchemaMessageConverter converter;
	
	@Autowired
	ObjectMapper mapper;
	
    public void sendMessage(BaseFPEvent baseFpEvent, String contentType) {
    	
    	Message<?> message = buildAvroMessage(baseFpEvent, contentType);    	
        System.out.println("Sender Car converted to Avro Message: " + message);
        
        byte[] payload = (byte[]) message.getPayload();
        System.out.println(" Sender payload sent: "+payload);
        
        output.outputChannel1().send(message); 
        output.outputChannel2().send(message); 
   }

   
    
    private Message<?> buildAvroMessage(BaseFPEvent baseFPEvent, String contentType) {
    	FPMessage fpMessage = FPMessage.newBuilder()
				.setEventType(baseFPEvent.getEventType())
				.setEventId(baseFPEvent.getEventId())
				.setUserGuid(baseFPEvent.getUserGuid())
				.setOriginator(baseFPEvent.getOriginator())
				.setPublisher(baseFPEvent.getPublisher())
				.setVin(baseFPEvent.getVin())
				.setOriginationTimestamp(baseFPEvent.getOriginationTimestamp())
				.build();
    	Map<String,Object> map = new HashMap<>();
    	map.put(MessageHeaders.CONTENT_TYPE, contentType);
    	return converter.toMessage(fpMessage, new MessageHeaders(map));
    }
    
    
    
//    public void sendCar(Car car, String contentType) {
//    	
//    	Message<?> message = buildAvroMessage(car, contentType);    	
//        System.out.println("Sender Car converted to Avro Message: " + message);
//        
//        byte[] payload = (byte[]) message.getPayload();
//        System.out.println(" Sender payload sent: "+payload);
//        
//        output.outputChannel1().send(message); 
//        output.outputChannel2().send(message); 
//   }
//    private Message<?> buildAvroMessage(Car car, String contentType) {
//    	AvroCar avroCar = AvroCar.newBuilder()
//				.setId(car.getId())
//				.setYear(car.getYear())
//				.setMake(car.getMake())
//				.setModel(car.getModel())
//				.setEngine(car.getEngine())
//				.build();
//    	Map<String,Object> map = new HashMap<>();
//    	map.put(MessageHeaders.CONTENT_TYPE, contentType);
//    	return converter.toMessage(avroCar, new MessageHeaders(map));
//    }
//   private Message<?> buildAvroMessage(Car car, String contentType) {
//	   AvroCar avroCar = AvroCar.newBuilder()
//    							.setId(car.getId())
//    							.setYear(car.getYear())
//    							.setMake(car.getMake())
//    							.setModel(car.getModel())
//    							.setEngine(car.getEngine())
//    							.build();
//    	byte[] payload = null;
//    	
//    	try {
////    		payload = new KafkaUtils().encode(avroCar);    		
//    		payload = new KafkaUtils().encode(avroCar);    		
//    		
////    		AvroCar decodedCar = new KafkaUtils().decode(payload, AvroCar.class);
////    		System.out.println("======== Decoded ID ======== "+decodedCar.getMake());
// 		
//    	} catch (Exception ex) {
//    		ex.printStackTrace();
//    	}
//    	//Map<String, Object> headers = new HashMap();
//    	//headers.put(MessageHeaders.CONTENT_TYPE, contentType);
//    	
//    	//return new GenericMessageConverter().toMessage(payload, new MessageHeaders(headers)) ;
//    	
//    	return MessageBuilder.withPayload(payload)
//    			.setHeader(MessageHeaders.CONTENT_TYPE, contentType)
//    			.build();  	
//    	
//    }
}
