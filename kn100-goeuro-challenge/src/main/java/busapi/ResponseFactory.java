package busapi;

/**
 * Defines the vernacular of the API, allowing creation of the various response types.
 * Created by Kevin Norman on 04/12/16.
 */
class ResponseFactory {

    Response makeSuccessfulResponse(int dep_sid, int arr_sid, boolean direct_bus_route) {
        return new SuccessfulResponse(dep_sid, arr_sid, direct_bus_route);
    }

    Response makeErrorResponse(String error) {
        return new ErrorResponse(error);
    }

}
