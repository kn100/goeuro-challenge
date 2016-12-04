package busapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Interface for API responses.
 * Created by Kevin Norman on 04/12/16.
 */
public interface Response {
    default String jsonify() {
        Gson gson = new GsonBuilder().create();
        //System.out.println(gson.toJson(response));
        return gson.toJson(this);
    }
}
