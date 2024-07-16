package sn.ads.couturepro.Security.Interfaces;

import sn.ads.couturepro.Security.Dao.JwtAuthentificationResponse;
import sn.ads.couturepro.Security.Dao.RefreshTokenRequest;
import sn.ads.couturepro.Security.Dao.SignInRequest;

public interface IAuthentificationService {

    JwtAuthentificationResponse signin(SignInRequest signInRequest);

    JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
