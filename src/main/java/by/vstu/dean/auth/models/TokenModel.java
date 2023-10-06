package by.vstu.dean.auth.models;

import com.google.gson.annotations.SerializedName;

public class TokenModel {


    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;

    public TokenModel(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenModel() {
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String toString() {
        return "TokenModel(accessToken=" + this.getAccessToken() + ", refreshToken=" + this.getRefreshToken() + ")";
    }
}
