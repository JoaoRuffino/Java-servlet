package Authenticator;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

public class Authenticator {
	//Chave secreta para assinatura de token - trocar localização
    private static final String SECRET_KEY = "SecretKey";

	public static String CreateToken(String email) {
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		String token = Jwts.builder()
				.setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 6000000)) // 100 minutos
                .signWith(signatureAlgorithm, SECRET_KEY)
                .compact();
		return token;
	}
	
	public static boolean ValidateToken(String token) {
		try {
			Claims claims = Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(token)
	                .getBody();
			if(!isTokenExpired(claims)) {
				return true;
			}else {
				return false;
			}
		}catch (SignatureException e) {
			return false;
        }
	}
	public static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
