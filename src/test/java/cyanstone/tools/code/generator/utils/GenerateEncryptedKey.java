package cyanstone.tools.code.generator.utils;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

public class GenerateEncryptedKey {
    @Test
    public void generateKey() {
        String salt = "swswelsgdslf";
        String pwd = "123";
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        System.out.println(textEncryptor.encrypt(pwd));
    }

}
