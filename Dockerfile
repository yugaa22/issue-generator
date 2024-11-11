FROM opsmx11/java:14.04-openjdk-8-jdk

# Create image with the below command
# docker build --force-rm -t issuegen:<tag> .
# Run the container with the below command
# docker run -it --rm -v /var/run/docker.sock:/var/run/docker.sock issuegen:<tag> bash
# During Docker image Development testing. In container, test scripts from /tmp/gitopsmx
# docker run -it --rm -v $PWD:/tmp/gitopsmx -v /var/run/docker.sock:/var/run/docker.sock issuegen:<tag> bash

# LABEL about the custom image
LABEL maintainer="ninja@opsmx.io"
LABEL version="v1.1"
LABEL description="Issue-Generator app test Blue/Green and Canary deployments"

ENV server_port=8088
ENV SERVER_PORT=8088
ENV PAGE_COLOR=green
ENV IMG_VER=v1

#WORKDIR ./

# Default shell for the RUN instruction is ["/bin/sh", "-c"], switch to bash
SHELL ["/bin/bash", "-c"] 

# --- Content of original issuegen Dockerfile ---
COPY target/issuegen-0.1.jar /opt/issuegen-0.1.jar

###### for newrelic-agent only
COPY newrelic/* /opt/newrelic/
COPY datadog  /opt/datadog/
COPY appdynamic /opt/appdynamic/
##for promothues-agent
#COPY prometheus/jmx_prometheus_javaagent-0.1.0.jar /opt/jmx_prometheus_javaagent-0.1.0.jar
#COPY prometheus/tomcat.yml /opt/tomcat.yml
#for DD-agent
#COPY tomcat.yaml /etc/dd-agent/conf.d/tomcat.yaml
#COPY install-dd.sh install-dd.sh
#RUN DD_API_KEY=<KEY> bash install-dd.sh

# ---

# COPY [^n]*    # All files that don't start with 'n'
# COPY n[^o]*   # All files that start with 'n', but not 'no'
# COPY no[^d]*  # All files that start with 'no', but not 'nod'

# Copy scripts/ directory to /opt/
# RUN mkdir -p /opt/scripts #Not required. Copy will create the directory
COPY run-app.sh /usr/local/bin/run-app.sh
COPY scripts/ /opt/scripts
COPY run-in-imgbuild.sh /tmp/

# Docker creates layer for each RUN commands; Instead having multiple RUN command, putting the commands 
# in a script and calling it in a RUN command creates just a single Docker layer
RUN bash /tmp/run-in-imgbuild.sh

#RUN apt-get update && apt-get install stress-ng -y

# Expose the Ports of the application 
#EXPOSE 80 443
EXPOSE 8088

#ENTRYPOINT bash

#CMD [init-all.sh && run-app.sh]
CMD init-all.sh; run-app.sh

