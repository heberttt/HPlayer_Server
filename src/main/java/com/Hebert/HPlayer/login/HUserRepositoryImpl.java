package com.Hebert.HPlayer.login;

import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;


@Repository
public class HUserRepositoryImpl implements HUserRepository{

    private final JdbcClient jdbcClient;

    public HUserRepositoryImpl(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<HUserDO> findByEmail(String email) {
        return jdbcClient.sql("SELECT * FROM users WHERE email=:email")
                .param("email", email)
                .query(HUserDO.class)
                .optional();
    }

    @Override
    public void createUser(HUserDO userDO) {
        var updated = jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString()))
                .update();

                Assert.state(updated == 1, "Failed to create run " + run.title());
    }
    
}
