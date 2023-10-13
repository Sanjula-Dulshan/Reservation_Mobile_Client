package com.example.onlineticketbooking.manager;

import android.util.Patterns;

import com.example.onlineticketbooking.models.register.RegisterRequestBody;
import com.example.onlineticketbooking.models.register.RegisterResponse;
import com.example.onlineticketbooking.models.register.RegisterService;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterManager {

    private static RegisterManager singleton;
    private final RegisterService registerService;

    public static RegisterManager getInstance() {
        if (singleton == null) {
            singleton = new RegisterManager();
        }
        return singleton;
    }

    private RegisterManager() {
        registerService = NetworkManager.getInstance().createService(RegisterService.class);
    }

    public String validateInputs(String nic, String name, String email, String password, String cpassword) {
        if (nic == null || nic.length() == 0)
            return "NIC is required";

        if (name == null || name.length() == 0)
            return "Name is required";

        if (email == null || email.length() == 0)
            return "Email is required";

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return "Email is not valid";

        if (password == null || password.length() == 0)
            return "Password is required";

        if (cpassword == null || cpassword.length() == 0)
            return "Confirm Password is required";

        if (!password.equals(cpassword))
            return "Password and Confirm Password should be same";

        return null;

    }

    public void register(String nic, String name, String email, String password, Runnable onSuccess, Consumer<String> onError) {

        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        RegisterRequestBody body = new RegisterRequestBody(nic, name, email, password);
        registerService.register(body).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();

                    System.out.print("registerResponse.getMessage() " + registerResponse.getMessage());

                    if ("User registered successfully.".equals(registerResponse.getMessage())) {
                        onSuccess.run();
                    } else {
                        onError.accept(registerResponse.getMessage());
                    }

                } else {
                    onError.accept("Sign up failed");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                onError.accept("Something went wrong");
            }
        });
    }
}
