package com.example.Sbb.Sbb.user.Service;

import com.example.Sbb.Sbb.user.Data.SiteUserDTO;

public interface UserService {
    public SiteUserDTO create(String username, String email, String password);
    public SiteUserDTO getUser(String username);
    public void modifyPassword(SiteUserDTO siteUserDTO, String passwordConfirm);

    public boolean isSamePassword(SiteUserDTO siteUserDTO, String beforePassword);
}
