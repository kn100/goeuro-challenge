package busapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Wraps error text in JSON tags for easier consumption.
 * Created by Kevin Norman on 04/12/16.
 */
class ErrorResponse implements Response {
    private String error;

    ErrorResponse(String error) {
        this.error = error;
    }
}
