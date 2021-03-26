package RetrofitPackage;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {
    //Data interpretation as Json
    //get the values of the API as JSon dynamically
    @GET("v6/193107db7f89a28b59d6f590/latest/{currency}")
    Call<JsonObject> getCurrency(@Path("currency")String currency);
}
