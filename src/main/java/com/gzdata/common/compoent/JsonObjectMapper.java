package com.gzdata.common.compoent;
import java.io.IOException;  

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;  
import com.fasterxml.jackson.core.JsonProcessingException;  
import com.fasterxml.jackson.databind.JsonSerializer;  
import com.fasterxml.jackson.databind.ObjectMapper;  
import com.fasterxml.jackson.databind.SerializerProvider;  
  
/**
 * 统一转换null对象为空字符串 
 * 
 *
 * @author 张兵帅
 *
 * @version 
 *
 * @since 2018年1月3日
 */
@Component
public class JsonObjectMapper extends ObjectMapper {  
    private static final long serialVersionUID = 1L;  
  
    public JsonObjectMapper() {  
        super();  
        // 空值处理为空串  
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {  
            @Override  
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {  
                jg.writeString("");  
            }  
        });  
    }  
}