package com.spring.boot.rest.miscellaneous;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;

/**
 * A test class that shows how to deserialize a bean containing an Integer field.
 * 
 * -> https://github.com/FasterXML/jackson-module-mrbean/issues/19
 *
 * @version 0.1
 */
public class MrBeanTest {

	@Test
    public void testIntegerBeanModuleMrBean() throws Exception {
		
		final class IntegerBean implements Serializable {
			
			/** serialVersionUID */
            private static final long serialVersionUID = 1L;
            private Integer id;

            public IntegerBean() {
            }

            /** @return the id */
            @SuppressWarnings("unused")
            public Integer getId() {
                return id;
            }

            /** @param pId, the id to set */
            public void setId(final Integer pId) {
                id = pId;
            }
        }
		
		// Creation of a simple bean with one Integer Field
        final IntegerBean integerBean = new IntegerBean();
        integerBean.setId(60);

        // Mr Bean registration
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new MrBeanModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(Feature.ALLOW_COMMENTS, true);

        final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(integerBean);
        // System.out.println("Json as string : " + json);
        
        final TypeReference<HashMap<String, Object>> typeMap = new TypeReference<HashMap<String, Object>>() {};
        final Map<String, Object> data = mapper.readValue(json, typeMap);
        
        Assert.assertEquals(60, data.get("id"));
	}

}
