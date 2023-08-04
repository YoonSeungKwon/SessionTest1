package yoon.test.sessionTest1.dto.reponse;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseMessage {

    private HttpStatus code;

    private String message;

    private Object data;

    public ResponseMessage(){
        this.code = HttpStatus.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }

}
