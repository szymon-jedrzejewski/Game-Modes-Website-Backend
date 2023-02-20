package com.gmw.api.rest;

import com.gmw.api.rest.activity.user.*;
import com.gmw.api.rest.activity.user.tos.RoleChangeDTO;
import com.gmw.api.rest.activity.user.tos.UserDTO;
import com.gmw.user.tos.ExistingUserTO;
import com.gmw.user.tos.NewUserTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUser/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        ObtainUserByEmailActivity activity = new ObtainUserByEmailActivity(email);
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody NewUserTO user) {
        CreateUserActivity activity = new CreateUserActivity(user);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody ExistingUserTO existingUserTO) {
        UpdateUserActivity activity = new UpdateUserActivity(existingUserTO);
        return activity.execute();
    }

    @PutMapping("/promoteToAdmin")
    public ResponseEntity<Void> promoteToAdmin(@RequestBody RoleChangeDTO roleChangeDTO) {
        PromoteToAdminActivity activity = new PromoteToAdminActivity(roleChangeDTO);
        return activity.execute();
    }

    @PutMapping("/demoteFromAdmin")
    public ResponseEntity<Void> demoteFromAdmin(@RequestBody RoleChangeDTO roleChangeDTO) {
        DemoteFromAdminActivity activity = new DemoteFromAdminActivity(roleChangeDTO);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        DeleteUserActivity activity = new DeleteUserActivity(id);
        return activity.execute();
    }
}
