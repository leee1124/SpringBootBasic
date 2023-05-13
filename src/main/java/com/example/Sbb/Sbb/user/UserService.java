package com.example.Sbb.Sbb.user;

public interface UserService {
    public SiteUserDTO create(String username, String email, String password);
    public SiteUserDTO getUser(String username);
}
