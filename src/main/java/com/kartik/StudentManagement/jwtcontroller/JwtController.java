package com.kartik.StudentManagement.jwtcontroller;

import com.kartik.StudentManagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/gen_tok")
    public String GenerateToken(@RequestParam String username , @RequestParam String password){
        if ("kartik".equals(username) && "889".equals(password)){
            return jwtUtil.generateToken(username);
        }
        return "invalid credentials";
    }
    @GetMapping("/validate")
    public String validateToken(@RequestHeader(value = "Authorization",required = false)
                                     String authorizationHeader){
                   if (authorizationHeader!=null &&
                           authorizationHeader.startsWith("Bearer ")){
                       String token  = authorizationHeader.substring(7);
                        if (jwtUtil.validateToken(token)){
                            return"valid";
                        }
                        else {
                                return"invalid";
                        }
                   }
                   return "Authorization header missing";
    }

}
