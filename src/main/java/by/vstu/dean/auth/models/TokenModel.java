package by.vstu.dean.auth.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel {


    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;

    public String toString() {
        return "TokenModel(accessToken=" + this.getAccessToken() + ", refreshToken=" + this.getRefreshToken() + ")";
    }
}
