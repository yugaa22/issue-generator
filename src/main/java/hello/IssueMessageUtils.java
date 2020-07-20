package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IssueMessageUtils {

	private IssueMessageUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	private static List<String> infoMessages;
	
	private static List<String> debugMessages;

	public static String getInfoMsg() {

		if (infoMessages == null) {
			infoMessages = new ArrayList<>();
			infoMessages.add("specdrive.sso.agent.SSOAgentFilter - project name is =/My_Dashboard");
			infoMessages.add("specdrive.dsg.managed.beans.MPayBusiness - TOP UP PAY CARD payable=20");
			infoMessages.add("specdrive.bus.managed.beans.MPayBusiness - Post Paid Services payable=486.42");
			infoMessages.add("specdrive.bus.managed.beans.MPayBusiness - Recharge payable=100-1-999");
			infoMessages.add("specdrive.bus.managed.beans.MPayBusiness - TOP UP PAY CARD payable=20-1-999");
			infoMessages.add("specdrive.bus.managed.beans.MPayBusiness - TOP UP PAY CARD payable=50-1-999");
			infoMessages.add("specdrive.bus.ApplicationContextEventListener - context intializing");
			infoMessages.add("specdrive.application.config.AppConfig - APPConfig is trying to load app config from path: xxxxxxxxxx");
			infoMessages.add("specdrive.application.config.AppConfig - APPConfig initialized successfully");
			infoMessages.add("specdrive.agent.util.SSOAgentConfigs - SSOConfig is trying to load app config from path: xxxxxxxxxx");
			infoMessages.add("specdrive.agent.util.SSOAgentConfigs - SSOConfig initialized successfully");
			infoMessages.add("specdrive.SSOAgentHttpSessionListener - session created");
			infoMessages.add("specdrive.sso.agent.SSOAgentFilter - project name is =/BillPay");
			infoMessages.add("specdrive.agent.saml.SAML2SSOManager - <?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			infoMessages.add("specdrive.myid.servlet.ForwardingServlet - user info successfully loaded");
			infoMessages.add("specdrive.sso.agent.SSOAgentFilter - project name is =/logAudit");
			infoMessages.add("specdrive.m.beans.CarRenewalManagedBean - ***loading Audit page**");
			infoMessages.add("specdrive.util.SessionIdGeneratorBase - Creation of SecureRandom instance for session ID generation took [350,333] milliseconds.");
			infoMessages.add("specdrive.managed.beans.JourneyPlannerMB - json : {\"listIcon\":\"\",\"showDirectionPanel\":false,\"showMyPlacesTab\":false,\"showNolTab\":false,\"applicationId\":1,\"mapSize\":0,\"locations\":[]}");
			infoMessages.add("specdrive.service.MicroAppResponse : pagerServices size: 28");
			infoMessages.add("specdrive.service.MicroAppResponse  : categoryServices size: 26");
			infoMessages.add("specdrive.service.MicroAppResponse  : categoryOneServices size: 1250");
			infoMessages.add("specdrive.AuthenticationInterceptor  : authorizationValid: start");
		}

		return getRandomMsg(infoMessages);

	}
	
	
	public static String getDebugMsg() {

		if (debugMessages == null) {
			debugMessages = new ArrayList<>();
			debugMessages.add("org.opensaml.xml.XMLConfigurator - Loading configuration from XML Document");
			debugMessages.add("org.opensaml.xml.XMLConfigurator - Schema validating configuration Document");
			debugMessages.add("org.opensaml.xml.XMLConfigurator - Configuration document validated");
			debugMessages.add("org.opensaml.xml.XMLConfigurator - Preparing to load ObjectProviders");
			debugMessages.add("org.opensaml.xml.XMLConfigurator - Initializing object provider");
			
		}

		return getRandomMsg(debugMessages);

	}

	private static String getRandomMsg(List<String> list) { 
		return list.get(new Random().nextInt(list.size())); 
	}
}
