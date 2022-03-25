package hello;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	
    static PropertiesUtil proUtil = new PropertiesUtil(); 
    static Properties properties = proUtil.getInstance();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "delay", defaultValue = "0") Integer delay) {

        String query;
        StringBuilder sb = new StringBuilder();
        String query2;
        StringBuilder sb2 = new StringBuilder();

        ClassLoader cl = GreetingController.class.getClassLoader();
        Path path = Paths.get(cl.getResource("testpage.html").getFile());
        if(delay > 0) {
        	Random r = new Random();
        	delay = r.nextInt((delay - delay/2) + 1) + delay/2;
            
            try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				logger.error("issuegen.agent.util.method.MPayBusiness - Sleep failed", e);
			}
        }
        
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
        	while ((query = br.readLine()) != null)
                sb.append(query);
            br.close();
            
        } catch (IOException e) {
        	logger.error("issuegen.agent.util.method.MPayBusiness - error found", e);
		}
        
        String watchdog = properties.getProperty("app.dog.image");
        Path path2 = Paths.get(cl.getResource(watchdog).getFile());
        try (BufferedReader br = Files.newBufferedReader(path2, StandardCharsets.UTF_8)){
        	while ((query2 = br.readLine()) != null)
                sb2.append(query2);
            br.close();
            
        } catch (IOException e) {
        	logger.error("issuegen.agent.util.method.MPayBusiness - error found", e);
		}

        return sb.length() > 0 ? sb.toString().replace("##dogimage##", sb2.toString()) : " No page found";
    }

    @RequestMapping("/barkingcats")
    public ResponseEntity barkingCatsCount(){
        try {
            calculateBarkingCats();
        } catch (Exception ex){
            logger.error("issuegen.agent.util.method.MPayBusiness - error found while counting barking cats {}",ex.getMessage(),ex);
        }
        return new ResponseEntity("No Barking cats!", HttpStatus.BAD_REQUEST);
    }

    public int calculateBarkingCats(){
        throw new UnsupportedOperationException("Cats can not bark");
    }

    @RequestMapping("/issues")
    public ResponseEntity generateIssues(HttpServletRequest httpServletRequest, @RequestParam("issue") String issue){
    	HttpStatus httpStatus = generateIssues(issue);
        return new ResponseEntity("Operation done successfully", httpStatus);
    }

    private HttpStatus generateIssues(String issue){
        if("CRITICAL".equals(issue)){
            logger.error("issuegen.agent.util.method.MPayBusiness - FATAL rest call response is empty!");
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else if ("ERROR".equals(issue)){
           logger.error("issuegen.agent.util.method.MPayBusiness - Assert : userName is missing.");
           return HttpStatus.INTERNAL_SERVER_ERROR;
        } else if ("WARN".equals(issue)){
            logger.warn("issuegen.agent.util.method.MPayBusiness - Response is empty string. No data returned. SessionInfoCode value might be expired.");
            return HttpStatus.OK;
        } else if ("DEBUG".equals(issue)){
            logger.debug(IssueMessageUtils.getDebugMsg());
            return HttpStatus.OK;
        }else {
            logger.info(IssueMessageUtils.getInfoMsg());
            return HttpStatus.OK;
        }
    }


    @RequestMapping("/dogcount")
    public String dogCount() {
        logger.info("issuegen.agent.util.method.MPayBusiness - pagerServices start");
        logger.info("issuegen.agent.util.method.MPayBusiness - pagerServices end");
        return "{ \"dogCount\": 20 }";
    }

    @RequestMapping("/catcount")
    public String catCount() {
    	logger.info("issuegen.agent.util.method.MPayBusiness - analyticServices start");
        logger.info("issuegen.agent.util.method.MPayBusiness - analyticServices end");
        return "{ \"catCount\": 16 }";
    }
}
