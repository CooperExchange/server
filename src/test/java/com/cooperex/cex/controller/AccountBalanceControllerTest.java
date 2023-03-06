package com.cooperex.cex.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountBalanceControllerTest {

    @Autowired
    private AccountSettingController accountSettingController;

    @Test
    public void contextLoads() throws Exception{
        assertThat(accountSettingController).isNotNull();
    }
}
