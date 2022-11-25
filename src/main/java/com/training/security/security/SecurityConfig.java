package com.training.security.security;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Scanner;

@Configuration
@EnableEncryptableProperties
public class SecurityConfig {

    private static String PIPER = "IloveSecurityForLivingAndJoy";

    public static StringEncryptor customOp = jasyptStringEncryptorBean();

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8,
                                         new SecureRandom("denemedskhfksjfh4566tryry438437834ksjfhksjfhksjfh".getBytes()));

    }

    @Bean
    public StringEncryptor jasyptStringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(PIPER);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    public static StringEncryptor jasyptStringEncryptorBean() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(PIPER);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    public static void main2(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Dec String : ");
            String s = scanner.nextLine();
            String encrypt = jasyptStringEncryptorBean().decrypt(s);
            System.out.println(encrypt);
        } catch (Exception eParam) {
            throw new RuntimeException(eParam);
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enc String : ");
            String s = scanner.nextLine();
            String encrypt = jasyptStringEncryptorBean().encrypt(s);
            System.out.println(encrypt);
        } catch (Exception eParam) {
            throw new RuntimeException(eParam);
        }
    }

}
