package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Assignment {

    @Test
    public void testCode() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:/Users/allchand/eclipse-workspace/selenium/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("nav-hamburger-menu")).click();


        //2. scroll and click on the TV, Appliances and Electronics section
        // WebElement scrollAndClick = driver.findElement(By.xpath("//a[@data-ref-tag='nav_em_1_1_1_14']")); //by Xpath
        WebElement scrollAndClick = driver.findElement(By.cssSelector("a[data-menu-id='9']"));
        js.executeScript("arguments[0].scrollIntoView(true);",scrollAndClick);
        scrollAndClick.click();
        //Thread.sleep(3000);



        //3.click on Televisions
        WebElement clickTelevision = driver.findElement(By.linkText("Televisions"));
        clickTelevision.click();
        //Thread.sleep(3000);
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,3000)");

        //4. Filter the results by brand samsung
        WebElement clickBrand = driver.findElement(By.linkText("Samsung"));//driver.findElement(By.id()).findElement(By.xpath("//a[@href] = '/s?i=electronics&bbn=1389396031&rh=n%3A1389396031&dc&qid=1662704244&rnid=3837712031&ref=sr_nr_p_89_1&ds=v1%3AvG%2FT1UFMQ4V7UWJzoJJDbTcad6OoMT69FX3TxMAmCvs'"));
        clickBrand.click();
        //driver.findElement(By.xpath("//a[@id='s-result-sort-select_-8']")).click();

        WebElement dropDown = driver.findElement(By.id("s-result-sort-select"));
        Select dropDowns = new Select(dropDown);
        dropDowns.selectByValue("price-desc-rank");

        WebElement second = driver.findElement(By.cssSelector("div[data-index = '2']"));
        second.click();

        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        //switch to new tab
        driver.switchTo().window(newTb.get(1));

        String ati = driver.findElement(By.xpath("//div[@id='featurebullets_feature_div']/div/h1")).getAttribute("innerHTML");
        Assert.assertEquals(ati," About this item ");

        List<WebElement> productDescription = driver.findElements(By.xpath("//ul[@class='a-unordered-list a-vertical a-spacing-mini']"));
        for(WebElement product: productDescription){
            System.out.println(product.getText());
        }

        //Reporter.log(driver.findElement(By.xpath("//div[@id='featurebullets_feature_div']/div")).getAttribute("innerHTML"));
        driver.quit();
    }


}
