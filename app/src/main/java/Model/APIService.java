package Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    //Obtener lista de medios de pagos
    @GET("popular")
    Call<Movies> getPopular(@Query("api_key") String key);

    //Obtener lista de medios de pagos
    @GET("top_rated")
    Call<Movies> getRated(@Query("api_key") String key);
}
