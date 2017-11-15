package com.example.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericContainer;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.integration.codec.Codec;

public class KafkaUtils implements Codec{
	
	public static final String MIME_TYPE = "binary/avro";
	//public static final String MIME_TYPE = "application/avro";
	//public static final String MIME_TYPE = "application/json";
	
	@Override
	public void encode(Object object, OutputStream outputStream) throws IOException {

		Schema schema = getSchema(object);
		DatumWriter writer = getDatumWriter(object.getClass(),schema);
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
		writer.write(object,encoder);
		encoder.flush();	
	}

	@Override
	public byte[] encode(Object object) throws IOException {
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//encode(object,baos);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder =
            EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        
        Schema schema = getSchema(object);
        
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        datumWriter.write((GenericRecord) object, binaryEncoder);

        binaryEncoder.flush();
        byteArrayOutputStream.close();

        return byteArrayOutputStream.toByteArray();
        
		//return baos.toByteArray();
	}

	@Override
	public <T> T decode(InputStream inputStream, Class<T> type) throws IOException {
		return decode(IOUtils.toByteArray(inputStream),type);
	}

	@Override
	public <T> T decode(byte[] bytes, Class<T> type) throws IOException {
		Schema schema = null;
		try {
			schema = getSchema(type.newInstance());
		} catch (Exception ex) {ex.printStackTrace();}
				
		DatumReader reader = getDatumReader(type,schema);
//		DataFileStream<GenericRecord> dataFileReader = new DataFileStream<GenericRecord>(new ByteArrayInputStream(bytes), reader);
//		return (T) dataFileReader.next();
        Decoder decoder = DecoderFactory.get().binaryDecoder(bytes,null);
		return (T) reader.read(null,decoder);
		
	}
	

		
	private DatumReader getDatumReader(Class<?> type, Schema writer){
		DatumReader reader = null;
		if(SpecificRecord.class.isAssignableFrom(type)){
			reader = new SpecificDatumReader<>(writer);
		}
		return reader;
		}
	
		
	//public static final String USER_SCHEMA =	"{ \"namespace\" : \"com.example.demo.generated.avro\", \"type\" : \"record\", \"name\" : \"AvroCar\", \"fields\" : [{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"year\", \"type\":\"int\", \"default\":0},{\"name\":\"make\", \"type\":\"string\"},{\"name\":\"model\",\"type\":\"string\"},{\"name\":\"engine\",\"type\":\"string\"}]}";
	
	private Schema  getSchema(Object payload){
		Schema schema = null;
		if(GenericContainer.class.isAssignableFrom(payload.getClass())) {
			schema = ((GenericContainer) payload).getSchema();
		}
		//Schema.Parser parser = new Schema.Parser();
        //Schema schema = parser.parse(USER_SCHEMA);
        
		return schema;
	}
	
	private DatumWriter<?> getDatumWriter(Class<?> type, Schema schema){
		DatumWriter<?> writer = null;
		if(SpecificRecord.class.isAssignableFrom(type)){
			writer = new SpecificDatumWriter<>(schema);
		}
		return writer;
	}

}
