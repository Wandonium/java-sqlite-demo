package com.hillarywando.sqlitedemo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;;

/**
 *
 * @author Hillary Wando
 */
@Builder
@Getter @Setter 
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Border {
    
    @NonNull private Integer id;
    private String server_id;
    private int server_port;
    private String database_name;
    private String user_name;
    private String password;
    private Timestamp date_updated;

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return mapper.writerWithDefaultPrettyPrinter()
                  .writeValueAsString(this);
        } catch (JsonProcessingException e1) {
          return "Border{" + '}';
        }
    }    
}
