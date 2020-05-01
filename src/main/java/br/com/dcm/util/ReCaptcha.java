package br.com.dcm.util;

import java.util.Calendar;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import br.com.dcm.Selenium;

public class ReCaptcha {

    private Selenium selenium;

    public ReCaptcha(Selenium selenium){
        this.selenium = selenium;
    }

    public void check(){
        this.selenium.clickInRecaptchaByXPath("span", "id", "recaptcha-anchor");
    }

    public Boolean waitResolve(Integer timeout){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, timeout);

        Boolean reCaptchaCheck = false;

        WebElement inputResult;

        while (calendar.getTime().after(Calendar.getInstance().getTime())) {
            inputResult = selenium.getElementByName("g-recaptcha-response");

            String valueRet = inputResult.getAttribute("value");
            if(valueRet != null && !valueRet.isEmpty()){
                System.out.println("ReCapthca resolvido");
                reCaptchaCheck = true;
                break;
            }
        }

        if(reCaptchaCheck){
            System.out.println("Removendo captcha");
            selenium.getElementByTagName("body").sendKeys(Keys.ESCAPE);
//            selenium.getElementByTagName("body").click();
        }

        return reCaptchaCheck;
    }
}
