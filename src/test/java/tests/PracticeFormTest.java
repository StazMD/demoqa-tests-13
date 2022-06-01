package tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void submitFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue("Stanislav");
        $("#lastName").setValue("Scurtu");
        $("#userEmail").setValue("stanislav.scurtu@gmail.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1987");
        $(".react-datepicker__day--004").click();
        $("#subjectsInput").sendKeys("Physics");
        $("#subjectsInput").pressEnter();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/image.png"));
        $("#currentAddress").setValue("Puskin street 145"); //
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();
        $("#submit").scrollTo().click();

        $(".modal-content").shouldHave(text("Thanks for submitting the form"),
                text("Stanislav Scurtu"),
                text("stanislav.scurtu@gmail.com"),
                text("Male"),
                text("1234567890"),
                text("04 February,1987"),
                text("Physics"),
                text("Reading, Music"),
                text("image.png"),
                text("Puskin street 145"),
                text("NCR Noida"));
    }
}
