package com.wizeline.selenium.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random; 


public class DataUtils {

    /**
     * Creates a random string whose length is from 1 up to 100 characters.
     * Characters will be chosen from the set of all characters.
     * @return the random string
     */
    public static String getRandomString(){

        Random random = new Random();   
        return RandomStringUtils.random(1+random.nextInt(100));
        
    }

    /**
     * Creates a random string whose length is the number of characters specified.
     * Characters will be chosen from the set of all characters.
     * @param count the length of random string to create
     * @return the random string
     */
    public static String getRandomString(int count){

        return RandomStringUtils.random(count);
        
    }

    /**
     * Creates a random string whose length is from 1 up to 100 characters.
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z).
     * @param count the length of random string to create
     * @return the random string
     */
    public static String getRandomAlphabeticString(){

        Random random = new Random();   
        return RandomStringUtils.randomAlphabetic(1+random.nextInt(100));
        
    }

     /**
     * Creates a random string whose length is the number of characters specified.
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z).
     * @param count the length of random string to create
     * @return the random string
     */
    public static String getRandomAlphabeticString(int count){
         
        return RandomStringUtils.randomAlphabetic(count);
        
    }

    /**
     * Creates a random string whose length is from 1 up to 100 characters.
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z) and the digits 0-9.
     * @return the random string
     */
    public static String getRandomAlphaNumericString(){

        Random random = new Random();   
        return RandomStringUtils.randomAlphanumeric(1+random.nextInt(100));
        
    }

    /**
     * Creates a random string whose length is the number of characters specified.
     * Characters will be chosen from the set of Latin alphabetic characters (a-z, A-Z) and the digits 0-9.
     * @param count the length of random string to create
     * @return the random string
     */
    public static String getRandomAlphaNumericString(int count){

        return RandomStringUtils.randomAlphanumeric(count);
        
    }

    /**
     * Creates a random string whose length is from 1 up to 100 characters.
     * Characters will be chosen from the set of numeric characters.
     * @return the random string
     */
    public static String getRandomNumericString(){

        Random random = new Random();   
        return RandomStringUtils.randomNumeric(random.nextInt(100));
        
    }

    /**
     * Creates a random string whose length is the number of characters specified.
     * Characters will be chosen from the set of numeric characters.
     * @param count the length of random string to create
     * @return the random string
     */
    public static String getRandomNumericString(int count){
 
        return RandomStringUtils.randomNumeric(count);
        
    }

    /**
     * Creates a random string following an email format.
     * @return the random email
     */
    public static String getRandomEmail(){
  
        return RandomStringUtils.randomAlphanumeric(10)+"@"+RandomStringUtils.randomAlphanumeric(10)+".com";
        
    }

    /**
     * Creates a random Password whose length is 12 characters.
     * @return the random password
     */
    public static String getRandomPassword(){
  
        Random random = new Random();   
        return RandomStringUtils.random(random.nextInt(12));
        
    }

    /**
     * Creates a random phone number with 9 digits.
     * @return the randonm phone number
     */
    public static String getRandomPhoneNumber(){
  
        return getRandomNumericString(9);
        
    }

}
