package client.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTokenResponse {

    String access_token;
    String token_type;
    String refresh_token;
    int expires_in;
    String scope;

    public UserTokenResponse(String access_token, String token_type, String refresh_token, int expires_in, String scope) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.scope = scope;
    }
}
