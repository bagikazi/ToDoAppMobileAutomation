package todoappMobileautomation;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.net.MalformedURLException;
import java.net.URL;

public class Setup {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;


    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Nexus 5");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("appActivity","com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity");
        caps.setCapability("appPackage","com.example.android.architecture.blueprints.todomvp.mock");
        caps.setCapability("noReset","true");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
        System.out.println("Uygulama basarili bir sekilde ayaga kalti.");
    }

    @Test
    public void createNewTaskTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task")).click();
        Thread.sleep(5000);
        String value = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView")).getText();
        System.out.println("Elementin texti = " + value);
        Assert.assertTrue(value.equals("New TO-DO"),"Create new task failed!");
        Thread.sleep(5000);
    }

    @Test
    public void setTitleTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title")).click();
        WebElement title = driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title"));
        title.click();
        title.sendKeys("title example");
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_edit_task_done")).click();
        Thread.sleep(5000);
        String value = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout/android.widget.TextView")).getText();
        System.out.println("Elementin texti = " + value);//yazdığımız taskı aldık
        Assert.assertTrue(value.equals("title example"),"Set title failed!");
        Thread.sleep(5000);


    }
    @Test
    public void emptyTitle() throws InterruptedException {

        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title")).click();
        WebElement title = driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title"));
        title.click();
        title.sendKeys("");
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_edit_task_done")).click();
        Thread.sleep(5000);
        String value = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView")).getText();
        System.out.println("Elementin texti = " + value);
        Assert.assertTrue(value.equals("New TO-DO"),"Empty Title failed!");//ana sayfaya dönmediğini anlamak için
        Thread.sleep(5000);


    }
    @Test
    public void markComplete () throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title")).click();
        WebElement title = driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title"));
        title.click();
        title.sendKeys("title example");
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_edit_task_done")).click();
        Thread.sleep(5000);//task ekledik buraya kadar başka task olmadığını düşünüyoruz
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.CheckBox")).click();
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/menu_filter")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")).click();
        String value = driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/filteringLabel")).getText();
        System.out.println("Elementin texti = " + value);
        Assert.assertTrue(value.equals("Completed TO-DOs"),"Mark Complete failed!");//completed sayfasında görebilmek için teyit ediyorum
        Thread.sleep(5000);

    }
    @Test
    public void unmarkComplete () throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title")).click();
        WebElement title = driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title"));
        title.click();
        title.sendKeys("title example");
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_edit_task_done")).click();
        Thread.sleep(5000);//task ekledik buraya kadar başka task olmadığını düşünüyoruz
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.CheckBox")).click();
        //mark yaptık şimdi sıra unmark
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/complete")).click();//unmark
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/menu_filter")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")).click();
        String value = driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/noTasksMain")).getText();
        System.out.println("Elementin texti = " + value);
        Assert.assertTrue(value.equals("You have no completed TO-DOs!"),"Unmark Complete failed!");//completed sayfasında görmediğimizi teyit ediyorum
        Thread.sleep(5000);




    }
    @Test
    public void deleteTask () throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title")).click();
        WebElement title = driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title"));
        title.click();
        title.sendKeys("title example");
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/fab_edit_task_done")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/menu_delete")).click();
        Thread.sleep(5000);
        String value = driver.findElement(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/noTasksMain")).getText();
        System.out.println("Elementin texti = " + value);
        Assert.assertTrue(value.equals("You have no TO-DOs!"),"Delete Task failed!");//Ana sayfada task görmediğimizi teyit ediyorum
        Thread.sleep(5000);


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
