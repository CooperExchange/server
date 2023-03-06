package com.cooperex.cex.dao;

import com.cooperex.cex.model.AccountSetting;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountSettingDAOTest {

    @Autowired
    AccountSettingDAO accountSettingDAO = new AccountSettingDAO();

    @Autowired
    AccountSetting accountSetting = new AccountSetting("KangHyuk.Lee@Cooper.edu", "Chris", "Lee", "Chrislee8684", "Cooper Union");

    @Test
    public void testUpdateAccountInfoById(){
        String result = accountSettingDAO.updateAccountInfoById("1", accountSetting);

        Assert.assertEquals(result, "User info has been updated");
    }

    @Test
    public void testResetAccountById(){
        String result = accountSettingDAO.resetAccountById("1");

        Assert.assertEquals(result, "called resetAccountById");
    }
}
