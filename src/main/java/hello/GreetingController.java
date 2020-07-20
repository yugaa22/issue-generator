package hello;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class GreetingController {

    static PropertiesUtil proUtil = new PropertiesUtil();
    static Properties properties = proUtil.getInstance();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        String query;
        StringBuffer sb = new StringBuffer();
        String query2;
        StringBuffer sb2 = new StringBuffer();

        ClassLoader cl = GreetingController.class.getClassLoader();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getResource("testpage.html").openStream()));
            while ((query = br.readLine()) != null)
                sb.append(query);
            br.close();

            String watchdog = properties.getProperty("app.dog.image");
            log.info("checking ******************* {}", watchdog);

            BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getResource(watchdog).openStream()));
            while ((query2 = br2.readLine()) != null)
                sb2.append(query2);
            br2.close();

        } catch (Exception e) {
            log.error("error found", e);
        }
        return sb.length() > 0 ? sb.toString().replace("##dogimage##", sb2.toString()) : " No page found";
    }

    @RequestMapping("/barkingcats")
    public ResponseEntity barkingCatsCount(){
        try {
            calculateBarkingCats();
        } catch (Exception ex){
            log.error("error found while counting barking cats {}",ex.getMessage(),ex);
        }
        return new ResponseEntity("No Barking cats!", HttpStatus.BAD_REQUEST);
    }

    public int calculateBarkingCats(){
        throw new UnsupportedOperationException("Cats can not bark");
    }

    @RequestMapping("/issues")
    public ResponseEntity generateIssues(HttpServletRequest httpServletRequest, @RequestParam("issue") String issue){
            generateIssues(issue);
            return new ResponseEntity("Operation done successfully",HttpStatus.OK);
    }

    private void generateIssues(String issue){
        if("CRITICAL".equals(issue)){
            log.error("FATAL rest call response is empty!");
        } else if ("ERROR".equals(issue)){
           log.error("Assert : userName is missing.");
        } else if ("WARN".equals(issue)){
            log.warn("Response is empty string. No data returned. SessionInfoCode value might be expired.");
        } else if ("DEBUG".equals(issue)){
            log.debug("Constructing Apache XMLSignature object");
        }else {
            log.info(IssueMessageUtils.getInfoMsg());
        }
    }


    @RequestMapping("/dogcount")
    public String dogCount() {
        log.info("begin dog count :::::::::::::");
        return "{ \"dogCount\": 20 }";
    }

    @RequestMapping("/catcount")
    public String catCount() {
        log.info("begin cat count :::::::::::::");
        return "{ \"catCount\": 16 }";
    }
}
