package com.cooperex.cex.model;

import com.cooperex.cex.controller.AccountBalanceControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertSame;

public class AccountSettingTest {

    //test instantiation and getters
    @Test
    public void testAccountSetting(){
        String email = "Kanghyuk.lee@cooper.edu";
        String firstName = "Chris";
        String lastName = "Lee";
        String username = "chrislee8684";
        String password = "cooperUnion";

        AccountSetting accountSetting = new AccountSetting(email, firstName, lastName, username, password);

        assertSame(email, accountSetting.getEmail());
        assertSame(firstName, accountSetting.getFirstName());
        assertSame(lastName, accountSetting.getLastName());
        assertSame(username, accountSetting.getUsername());
        assertSame(password, accountSetting.getPassword());
    }


}
