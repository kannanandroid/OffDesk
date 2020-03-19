package com.ifazig.optdesk.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ifazig.optdesk.utils.CustomProgressDialog;
import com.ifazig.optdesk.utils.MyApplication;


import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiConfiguration {

    public String ProdOrDev = "";

    private static ApiConfiguration ourInstance = new ApiConfiguration();
    Retrofit mRetrofit;

    public static ApiConfiguration getInstance() {
        if (ourInstance == null) {
            synchronized (ApiConfiguration.class) {
                if (ourInstance == null)
                    ourInstance = new ApiConfiguration();
            }
        }
        ourInstance.config();
        return ourInstance;
    }


    private ApiConfiguration() {
    }

    private void config() {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        String BASE_URL = Urls.BASE_URL_STAGING;

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRequestHeader(""))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private OkHttpClient getRequestHeader(final String token) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuild = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                //.addInterceptor(new ChuckInterceptor(MyApplication.context))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Accept", "application/json")
                                .build();

                        if (!CheckInternetConnection()) {
                            CustomProgressDialog.getInstance().dismiss();
                            throw new NoConnectivityException();
                        } else {
                            Response response = chain.proceed(newRequest);
                            BufferedSource source = response.body().source();
                            source.request(Long.MAX_VALUE); // Buffer the entire body.
                            Buffer buffer = source.buffer();
                            String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));
                            try {

                                JSONObject obj = new JSONObject(responseBodyString);
//                                String cartCount = obj.getString("cart_quantity");
                                //SessionManager.User.getInstance().setCartCount(cartCount);

//                                Log.d("My App", String.valueOf(obj.getJSONObject("system"))+"");

                            } catch (Exception e) {
                                e.printStackTrace();
                            } catch (Throwable t) {
                                t.printStackTrace();
//                                Log.e("My App", "Could not parse malformed JSON: \"" +  "\"");
                            }


                            return response;
                        }
                    }
                })
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        okHttpClientBuild.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("", "").build();
                request = request.newBuilder().url(url).build();

                if (!CheckInternetConnection()) {
                    CustomProgressDialog.getInstance().dismiss();
                    throw new NoConnectivityException();
                } else {
                    Response response = chain.proceed(request);
                    return response;
                }
            }
        });
        OkHttpClient okHttpClient = okHttpClientBuild.build();
        return okHttpClient;
    }

    public Retrofit getApiBuilder() {

        return mRetrofit;
    }

    public class NoConnectivityException extends IOException {
        @Override
        public String getMessage() {
            return "";
        }
    }

    private boolean CheckInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}