package edu.mum.wap.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



public class CarPoolingMarshaller {
	public static String getJsonFromObject(Object object) throws JsonGenerationException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String jsonInString = null;

		try {
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			System.out.println(jsonInString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

}
