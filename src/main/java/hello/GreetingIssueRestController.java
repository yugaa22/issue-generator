package hello;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@RestController
public class GreetingIssueRestController {
			
	@GetMapping("/logs")
	public ResponseEntity<String> logs(HttpServletRequest httpServletRequest, @RequestParam("type") String type) {
		generateLogs(type);
		return new ResponseEntity<>("Operation done successfully",HttpStatus.OK);
	}

	@GetMapping("/issue")
	public ResponseEntity<String> issueLogs(HttpServletRequest httpServletRequest, @RequestParam("type") String type){		
		generateIssues(type);
		return new ResponseEntity<>("Operation done successfully",HttpStatus.OK);
	}
	
	private void generateLogs(String type) {
        if("CRITICAL".equalsIgnoreCase(type)){
        	log.error("FATAL rest call response is empty!");
            return;
        }
        
        if ("DEBUG".equalsIgnoreCase(type)){
            log.debug("Constructing Apache XMLSignature object");
            return;
        }
        
        if ("ERROR".equalsIgnoreCase(type)){
           log.error("Assert : userName is missing.");
           return;
        }
        
        if ("WARN".equalsIgnoreCase(type)){
            log.warn("Response is empty string. No data returned. SessionInfoCode value might be expired.");
            return;
        }  
          
        log.info(IssueMessageUtils.getInfoMsg());
    }
	
	private void generateIssues(String type){
        
        if("OUT_OF_MEMORY".equalsIgnoreCase(type)) {
        	log.error("memory execeded", new OutOfMemoryError("GC overhead limit exceeded"));
        	return;
        }
        
        if("STACK_OVERFLOW_ERROR".equalsIgnoreCase(type)) {
        	log.error("stack overflow error occured", new StackOverflowError("Stack overflow occured"));
        	return;
        }
        
        if("FILE_NOT_FOUND".equalsIgnoreCase(type)) {
        	log.error("File not found", new FileNotFoundException("File not found"));
        	return;
        }
        
        if("ARRAY_INDEX_OUT_OF_BOUND".equalsIgnoreCase(type)) {
        	log.error("Array index out of range", new ArrayIndexOutOfBoundsException(6));
        	return;
        }
        
        if("NULL_POINTER".equalsIgnoreCase(type)) {
        	log.error("Null pointer exception occured", new NullPointerException());
        	return;
        }
        
        if("STRING_INDEX_OUT_OF_BOUND".equalsIgnoreCase(type)) {
        	log.error("String index out of range", new StringIndexOutOfBoundsException("String index out of range"));
        	return;
        }
        
        if("NO_CLASS_DEF_FOUND".equalsIgnoreCase(type)) {
        	log.error("No class def found", new NoClassDefFoundError("No class def found A"));
        	return;
        }
        
        if("NO_SUCH_METHOD_FOUND".equalsIgnoreCase(type)) {
        	log.error("No such method found", new NoSuchMethodError("Method not found"));
        	return;
        }
        
        if("NUMBER_FORMAT".equalsIgnoreCase(type)) {
        	log.error("Number format exception occured", new NumberFormatException("Number format exception"));
        	return;
        }
        
        if("ILLEGAL_ARGUMENT".equalsIgnoreCase(type)) {
        	log.error("Illegal argument exception occured", new IllegalArgumentException("Illegal argument exception"));
        	return;
        }
        
        if("CLASS_NOT_FOUND".equalsIgnoreCase(type)){
        	log.error("class not found in classpath", new ClassNotFoundException("class not found in classpath"));
        } 
    }
	
	@GetMapping("/warn")
	public ResponseEntity<String> warningLogs(HttpServletRequest httpServletRequest, @RequestParam("type") String issue){		
		generateWarning(issue);
		return new ResponseEntity<>("Operation done successfully",HttpStatus.OK);
	}
	
	private void generateWarning(String type) {
		
		if("GARBAGE_COLLECTION".equalsIgnoreCase(type)) {
			log.warn("Garbage collection takes too long");
			return;
		}
		
		if("DEPRECATED_API".equalsIgnoreCase(type)) {
			log.warn("API WarningLogs is deprecated. It will be removed in future versions, use generateWarning API instead");
			return;
		}
		
		if("VERSION".equalsIgnoreCase(type)) {
			log.warn("Future versions will require Java 13; your Java version does not meet this requirement");
		}
	}
	
//	OutOfMemoryError
//	StackOverflowError
//	ClassNotFoundException
//	FileNotFoundException
//	ArrayIndexOutOfBounds
//	NullPointerException
//	StringIndexOutOfBoundsException
//	NoClassDefFoundError
//	NoSuchMethodFoundError
//	NumberFormatException
//	IllegalArgumentException
}
