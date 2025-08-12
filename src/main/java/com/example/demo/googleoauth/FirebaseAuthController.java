package com.example.demo.googleoauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class FirebaseAuthController {

    private final FirebaseAuthService firebaseAuthService;

    @PostMapping("/firebase-login")
    public ResponseEntity<?> loginWithFirebase(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");
        return ResponseEntity.ok(firebaseAuthService.authenticateFirebaseToken(idToken));
    }
}