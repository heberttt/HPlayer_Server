package com.Hebert.HPlayer.login;

import java.util.Optional;

public interface HUserRepository{

    Optional<HUserDO> findByEmail(String email);

    void createUser(HUserDO userDO);

}
