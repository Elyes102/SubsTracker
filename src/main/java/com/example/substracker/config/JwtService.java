package com.example.substracker.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;


@Service
public class JwtService {


    private static final String JWT_SECRET = "87 d4 06 bc b5 ee 27 e7 6d 13 21 36 95 90 6c 1e \n" +
            "b8 20 4e c0 89 a9 a2 52 fe 7d 79 d2 91 a2 c2 2a \n" +
            "d8 2a 40 0f 29 c8 2e d8 33 be b7 a4 3f 0a ed 83 \n" +
            "ab e6 e6 33 b1 0a 89 ca b3 2b ac 70 54 05 ae c7 \n" +
            "f2 02 24 d3 5b a2 3d ba ba 7c 9b ff fa 1c 7e 52 \n" +
            "30 29 90 0b 8d 51 69 f5 93 98 6e 09 4d c6 83 d5 \n" +
            "69 0b ef fb 08 dc 26 18 69 28 76 e0 e1 83 91 aa \n" +
            "38 60 5c 0f 5a bc 9e 20 f5 6d f3 a5 2f 70 07 6e \n" +
            "2b fe 37 12 0f 4b 77 1b d0 dd 74 33 00 75 91 91 \n" +
            "af 91 32 ec 81 5a 9d 09 32 8c 3f 12 5d bb dc 48 \n" +
            "0e 9d c4 03 7c 57 1e f9 5e fd ea 4e 70 97 07 26 \n" +
            "bc 92 0e 1f ab 99 ee 99 f4 85 89 9a 62 c2 43 a8 \n" +
            "c0 54 e4 27 2d ab 1c 79 25 f9 e1 9e 12 62 37 c0 \n" +
            "65 51 36 c0 ae 35 02 a0 cf 4e 68 bd b6 d3 0a 17 \n" +
            "20 de 0c a1 be 6b 6b e0 58 6b 25 06 8e 7f de a0 \n" +
            "a1 b3 3e 58 4c 03 59 a5 8b 97 fb 0b 67 f7 f3 3b \n" +
            "08 97 04 65 16 66 71 31 16 5b fc ec 8d 84 1e 10 \n" +
            "ca 0f de 96 8d 14 4d 4e 98 52 85 73 c6 30 d7 93 \n" +
            "c2 cc f4 0b 70 ed d8 84 35 2e 5a b8 90 13 ca da \n" +
            "4e 24 27 f5 c8 d6 b5 3f 9f 33 d1 0e 91 5f 13 6e \n" +
            "2f 04 35 40 eb be 6f fb 0b 0c bf f6 5b 1c 78 c7 \n" +
            "66 9f 55 bc cc d8 5f ec 29 6d 8d 4a 60 9c b0 9a \n" +
            "99 33 7a 01 e4 91 31 fb 89 0e 14 7d 4e e2 f2 6f \n" +
            "cf 1f f7 93 fe cf 54 9d 3b 6e 5b ad 44 38 23 35 \n" +
            "21 ea 6f a8 c2 68 ca 93 69 61 87 4c 95 09 f4 03 \n" +
            "a7 c7 3e 71 65 ae c8 67 16 bd 77 67 0c e6 89 45 \n" +
            "70 4f 02 1c 7c df d5 92 65 a4 1e f1 46 e2 93 90 \n" +
            "8d 33 e5 35 87 91 a9 bc 30 6f 56 6e 47 77 20 3b \n" +
            "13 59 ca b8 ea cf e1 06 1e a8 50 54 a8 63 4d c2 \n" +
            "0c 5a 3c 81 5a 80 ad 64 14 01 01 08 eb ef ba 2b \n" +
            "5f a9 a1 b4 f4 ee 8c 80 84 9a a8 e9 82 f5 7e 1d \n" +
            "d7 2d e0 68 d9 82 af 83 b7 84 82 42 0b 45 28 7e ";

    public String extractUsername(String token) {
        return null;
    }

   private Claims extractClaimsJWT(String token) {
            return Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
