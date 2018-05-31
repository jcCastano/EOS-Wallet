package model.errors;

import com.google.gson.JsonObject;

/**
 * Created by jc on 5/31/18.
 */
public class ErrorResponse {

    int code;
    String message;
    JsonObject error;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getError() {
        return error;
    }
}
