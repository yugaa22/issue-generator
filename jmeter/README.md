1. Download jmeter if already not exists.
	http://jmeter.apache.org/download_jmeter.cgi

2. Clone the git repository where we have jmeter script file to trigger issuegen application to introduce messages
	git clone https://github.com/OpsMx/issue-generator.git

3. go to jmeter folder inside the cloned repository.

4. Change the BASE_SERVER_IP, BASE_PORT  to required baseapp ip and port in baseapp.jmx.

5. Change the CANARY_SERVER_IP, CANARY_PORT  to required canaryapp ip and port in canary_issuegen_api.jmx.

6. In baseapp.jmx we are only generating info logs.

7. In canary_issuegen_api.jmx we are generating info, warning, error and critical errors.

8. By default jmeter script will run for 30 mins, if you wish to run it for more time search for "ThreadGroup.duration" and change "1800" to required time in seconds.

9. In canary_issuegen_api.jmx we are generating warning, error and critical error in random time intervals.
  
  warning -->
	   *  warning message will print every 30s to 1min  
	   *  if you wish to change repeatation of warning message, you can change "ConstantTimer.delay" and "RandomTimer.range" which are in milliseconds under --> testname="warning timer".
	      where "ConstantTimer.delay" is minimum delay between warning message and "RandomTimer.range" is random delay range which get adds to "ConstantTimer.delay".
	       
   error   --> 
	   * Error message will print every 30s to 1.5min 
	   * if you wish to change repeatation of error message, you can change "ConstantTimer.delay" and "RandomTimer.range" which are in milliseconds under --> testname="execption timer".
	     where "ConstantTimer.delay" is minimum delay between warning message and "RandomTimer.range" is random delay range which get adds to "ConstantTimer.delay".

   critical  --> 
	    * Critical Error message will print every 30s to 2.5min 
	    * if you wish to change repeatation of critical error message, you can change "ConstantTimer.delay" and "RandomTimer.range" which are in milliseconds under --> testname="critical error timer".
	          where "ConstantTimer.delay" is minimum delay between warning message and "RandomTimer.range" is random delay range which get adds to "ConstantTimer.delay".


10. Please update the path of "./warnings.csv", "./critical.csv" and "./java_execption.csv" with absolute path.

11. Before starting the jmeter script, please look at the fluent configure to which index logs are pushing, currently it is pushing to "test_log" 
   
    currently baseapp and canaryapp are running on azure dev cluster and datadog ns.

12. To start jmeter got to jmeter extracted folder and execute
	./bin/jmeter.sh -n -t baseapp.jmx   ---> for baseapp
        ./bin/jmeter.sh -n -t canary_issuegen_api.jmx   ---> for canaryapp
