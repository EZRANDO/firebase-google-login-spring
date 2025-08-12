package com.example.demo.googleoauth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseAuthService {

    public Map<String, Object> authenticateFirebaseToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();
            String name = (String) decodedToken.getClaims().get("name");

            // DB에 사용자 저장 or JWT 발급 처리
            // 예: 우리 서버 JWT 발급하여 클라이언트에 전달

            Map<String, Object> result = new HashMap<>();
            result.put("uid", uid);
            result.put("email", email);
            result.put("name", name);

            return result;

        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Firebase 토큰 검증 실패", e);
        }
    }
}

