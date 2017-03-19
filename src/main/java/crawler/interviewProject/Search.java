package crawler.interviewProject;

import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Search {

	public static WebDriver driver;

	public static final String url = "http://muenchen.bringmeister.de/obst-gemuse.html";

	public static Writer writer;

	private static Response response;
	public static StopWatch pageLoad;

	public static void XpathSearch_first60Prod() throws InterruptedException {

		driver = new FirefoxDriver();
		pageLoad = new StopWatch();
		pageLoad.start();
		System.out.println(pageLoad.getTime());
		driver.get(url);
		driver.findElement(By.cssSelector("#zip_code")).sendKeys("80796");
		driver.findElement(By.cssSelector("button.bm_button:nth-child(4)"))
				.click();

		String path = "//div[@class='bm_image-wrapper']/a[@role='link']/img";
		// driver.findElement(By.cssSelector("div.bm_image-wrapper > a[role=link] > img"));
		List<WebElement> productImages_60 = driver.findElements(By.xpath(path));
		for (WebElement imageFromList : productImages_60) {
			String ImageUrl = imageFromList.getAttribute("src");
		}
	}

	public static void XpathSearch_first120Prod() throws InterruptedException,
			FileNotFoundException {

		driver.findElement(
				By.xpath("//div[@id='endless-loader-container']/div[@id='endless-loader']"))
				.click();
		// driver.findElement(By.cssSelector("#endless-loader"));
		Thread.sleep(1000);
		String path2 = "//div[@class='bm_image-wrapper']/a[@role='link']/img";
		// driver.findElement(By.cssSelector("div.bm_image-wrapper > a[role=link] > img"));
		List<WebElement> productImages_120 = driver.findElements(By
				.xpath(path2));
		int counter = 1;

		for (WebElement imageFromList_2 : productImages_120) {
			String ImageUrl = imageFromList_2.getAttribute("src");
			JsonObject response = Json.createObjectBuilder()
					.add("ImagePosition : ", counter)
					.add("ImageURL : ", ImageUrl).build();
			System.out.println(response.toString());
			counter++;
		}
		pageLoad.stop();
		System.out.println(pageLoad.getTime() / 1000 + " seconds");
		System.out.println("First " + productImages_120.size()
				+ " image urls of products available.");
	}

	public static void main(String[] args) throws InterruptedException,
			FileNotFoundException {

		XpathSearch_first60Prod();

		XpathSearch_first120Prod();

	}
}
