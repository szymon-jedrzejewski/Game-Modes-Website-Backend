package com.gmw.coverters;

import com.gmw.model.User;
import com.gmw.user.enums.RoleEnum;
import com.gmw.user.tos.ExistingUserTO;
import com.gmw.user.tos.NewUserTO;

public class UserConverter implements
        ModelConverter<ExistingUserTO, User>,
        TOConverter<NewUserTO, User> {

    @Override
    public ExistingUserTO convertToTO(User user) {
        return ExistingUserTO
                .builder()
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .password(user.getPassword())
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    @Override
    public User convertToModel(NewUserTO newUserTO) {
        User user = new User();
        user.setAvatar(newUserTO.getAvatar());
        user.setRole(RoleEnum.USER.toString());
        user.setName(newUserTO.getName());
        user.setEmail(newUserTO.getEmail());
        user.setPassword(newUserTO.getPassword());
        return user;
    }
}
