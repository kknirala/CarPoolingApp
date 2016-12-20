package edu.mum.wap.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class CarPoolingUnmarshaller {

	public static ObjectMapper getObjectFromJsonString(String jsonData) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}
}
