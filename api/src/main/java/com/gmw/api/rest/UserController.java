package com.gmw.api.rest;

import com.gmw.api.rest.activity.user.*;
import com.gmw.api.rest.tos.EmailDTO;
import com.gmw.api.rest.tos.LoginDTO;
import com.gmw.api.rest.tos.RoleChangeDTO;
import com.gmw.api.rest.tos.TokenDTO;
import com.gmw.user.tos.ExistingUserTO;
import com.gmw.user.tos.NewUserTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UserController {

    private final PasswordEncoder encoder;

    @GetMapping("/getById/{id}")
    public ResponseEntity<ExistingUserTO> getUserBuId(@PathVariable Long id)
    {
        FindUserByIdActivity activity = new FindUserByIdActivity(id);
        return activity.execute();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> getUserByEmail(@RequestBody LoginDTO loginDTO) {
        LoginActivity activity = new LoginActivity(loginDTO, encoder);
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody NewUserTO user) {
        CreateUserActivity activity = new CreateUserActivity(user, encoder);
        return activity.execute();
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Void> forgotPassword(@RequestBody EmailDTO emailDTO) {
        ForgotPasswordActivity activity = new ForgotPasswordActivity(emailDTO, encoder);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                           @RequestBody ExistingUserTO existingUserTO) {
        UpdateUserActivity activity = new UpdateUserActivity(existingUserTO, token);
        return activity.execute();
    }

    @PutMapping("/promoteToAdmin")
    public ResponseEntity<Void> promoteToAdmin(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                               @RequestBody RoleChangeDTO roleChangeDTO) {
        PromoteToAdminActivity activity = new PromoteToAdminActivity(roleChangeDTO, token);
        return activity.execute();
    }

    @PutMapping("/demoteFromAdmin")
    public ResponseEntity<Void> demoteFromAdmin(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                @RequestBody RoleChangeDTO roleChangeDTO) {
        DemoteFromAdminActivity activity = new DemoteFromAdminActivity(roleChangeDTO, token);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                           @PathVariable Long id) {
        DeleteUserActivity activity = new DeleteUserActivity(id, token);
        return activity.execute();
    }
}
