package com.dim.retrofitjsonlogindb.network;

import com.dim.retrofitjsonlogindb.model.LoginRequest;
import com.dim.retrofitjsonlogindb.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioServicio {
    @POST("autenticate/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
