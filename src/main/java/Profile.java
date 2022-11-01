import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Profile {
    private Random generator = new Random();
    private WebDriver driver;
    List<String> listUrl = new ArrayList<>();
    List<String> listId = new ArrayList<>();
    List<WebElement> elements;
    public Profile() {
        driver = loadProfile();
        login();
    }

    private WebDriver loadProfile() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    private void login() {
        driver.get("https://code.ptit.edu.vn/");
        // dien tai khoan vao day
        driver.findElement(By.id("login__user")).sendKeys("B20DCCN236");
        driver.findElement(By.id("login__pw")).sendKeys("hiep0312");
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/form/button")).sendKeys(Keys.ENTER);

        elements = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/div/div[3]/table/tbody/tr"));

        for (WebElement w : elements) {
            if (!w.getAttribute("class").equals("bg--10th")) {
                listId.add(w.findElement(By.tagName("a")).getText());
                listUrl.add(w.findElement(By.tagName("a")).getAttribute("href"));
            }
        }
    }

    public void submit() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println(tabs);
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            try {
                System.out.println(listUrl.size());
                for (int i = 0; i < listUrl.size(); i++) {
                    driver.get(listUrl.get(i));
                    WebElement tmp = driver.findElement(By.className("fileInput__value"));
                    Actions actions = new Actions(driver);
                    actions.moveToElement(tmp);
                    actions.perform();
                    //folder chua code
                    try {
                        driver.findElement(By.xpath("//*[@id=\"fileInput\"]")).sendKeys("D:\\SourceCode\\Java\\Java_Code_PTIT\\" + listId.get(i) + ".java");
                        driver.findElement(By.className("submit__pad__btn")).sendKeys(Keys.ENTER);
                        System.out.println(listUrl.get(i));
                        System.out.println("D:\\SourceCode\\Java\\Java_Code_PTIT\\" + listId.get(i) + ".java");

                        // thoi gian delay giua cac lan submit
                        Thread.sleep((generator.nextInt(3) + 3) * 60000);
                    }
                    catch (Exception e) {

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        driver.close();
    }


}
