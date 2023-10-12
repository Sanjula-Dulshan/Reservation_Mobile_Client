package com.example.onlineticketbooking.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Patterns;

import com.example.onlineticketbooking.models.profile.ProfileRequestBody;
import com.example.onlineticketbooking.models.profile.ProfileResponse;
import com.example.onlineticketbooking.models.profile.ProfileService;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileManager {
    private static ProfileManager profileManager;
    private final ProfileService profileService;
    private final String loginStateFile = "loginstate";


    public static ProfileManager getInstance() {
        if (profileManager == null) {
            profileManager = new ProfileManager();
        }
        return profileManager;
    }

    private ProfileManager() {
        profileService = NetworkManager.getInstance().createService(ProfileService.class);
    }

    public String validateInputs(String nic, String name, String email) {
        if (nic == null || nic.length() == 0)
            return "NIC is required";

        if (name == null || name.length() == 0)
            return "Name is required";

        if (email == null || email.length() == 0)
            return "Email is required";

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return "Email is not valid";


        return null;

    }

    public void updateProfile(String nic, String name, String email, String password, Runnable onSuccess, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        ProfileRequestBody body = new ProfileRequestBody(nic, name, email, password);
        profileService.updateProfile(nic, body).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {

                    Context context = ContextManager.getInstance().getApplicationContext();
                    SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();

                    editor.putString("name", name);
                    editor.putString("email", email);
                    editor.apply();

                    onSuccess.run();
                } else {
                    onError.accept("Update failed");
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                onError.accept("Something went wrong");
            }
        });
    }


    public void deactivateProfile(String nic, Runnable onSuccess, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }


        profileService.activeDeactivate(nic).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {

                    Context context = ContextManager.getInstance().getApplicationContext();
                    SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();

                    onSuccess.run();
                } else {
                    onError.accept("Deactivate failed");
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                onError.accept("Something went wrong");
            }
        });
    }


}
