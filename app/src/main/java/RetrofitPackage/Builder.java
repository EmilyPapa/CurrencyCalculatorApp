package RetrofitPackage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Builder {
    //Builder will be accessed every time we make API calls
    //Converts Json objects to Java Objects with GsonConverterFactory
    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance (){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://v6.exchangerate-api.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
