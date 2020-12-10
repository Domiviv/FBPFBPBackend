package com.project.backend.fermeblanchepierre;

import com.project.backend.fermeblanchepierre.services.Password;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.fail;

public class PasswordTest {
    @Test
    void test() {
        //pass : admin et test
        String pwd = "test";
        Password p = new Password(pwd);
        p.firstHash();
        System.out.print(p.getHash());
        fail("Not yet implemented");

    }
}
