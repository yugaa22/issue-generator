#!/bin/bash
##====> for simple issuegen-0.1

APPDYNA_AGENT=""
APPDYNA_PROPERTIES=""
if [ ! -z "$APPDYNAMICS_AGENT_NODE_NAME" ] && [ ! -z "$APPDYNAMICS_AGENT_ACCOUNT_NAME" ] && [ ! -z "$APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY" ]; then
   APPDYNA_AGENT="-javaagent:/opt/appdynamic/javaagent.jar"
   APPDYNA_PROPERTIES="-Dappdynamics.controller.hostName=${APPDYNAMICS_CONTROLLER_HOST_NAME}   -Dappdynamics.agent.applicationName=${APPDYNAMICS_AGENT_APPLICATION_NAME} -Dappdynamics.agent.tierName=${APPDYNAMICS_AGENT_TIER_NAME} -Dappdynamics.agent.nodeName=${APPDYNAMICS_AGENT_NODE_NAME} -Dappdynamics.agent.accountName=${APPDYNAMICS_AGENT_ACCOUNT_NAME} -Dappdynamics.agent.accountAccessKey=${APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY}"
fi

DATADOG_AGENT=""
DATADOG_PROPERTIES=""
if [ ! -z "$DD_AGENT_HOST" ]; then
   DATADOG_AGENT="-javaagent:/opt/datadog/dd-java-agent.jar"
   DATADOG_PROPERTIES="-Ddd.agent.host=${DD_AGENT_HOST} -Ddd.service.name=${SERVICE_NAME} -Ddd.agent.port=8126"
fi

NEWRELIC_AGENT=""
NEWRELIC_PROPERTIES=""
if [ ! -z "$NEWRELIC_LICENSE" ]; then
    NEWRELIC_AGENT="-javaagent:/opt/newrelic/newrelic.jar"
    NEWRELIC_PROPERTIES="-Dnewrelic.config.license_key=${NEWRELIC_LICENSE} -Dnewrelic.config.app_name=${SERVICE_NAME}"
fi

(cd /opt;nohup java  -Dserver.port=8088 $APPDYNA_AGENT $APPDYNA_PROPERTIES $DATADOG_AGENT $DATADOG_PROPERTIES $NEWRELIC_AGENT $NEWRELIC_PROPERTIES -jar /opt/issuegen-0.1.jar > /var/log/issuegen-0.1.log 2>&1 &)
sudo chmod 775 /var/log/issuegen-0.1.log
sudo tail -f /var/log/issuegen-0.1.log &

sleep infinity
