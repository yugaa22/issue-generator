#!/bin/bash

#Purpose: This script is called inside a Dockerfile during 'docker build' to take care of package installation

# If there are more RUN commands in Dockerfile, put them all here and
# call this script with one single RUN command. So it will just create one Docker layer

#Testing only
ls -lR /opt/scripts

#Install packages
apt-get update && apt-get install stress-ng -y

#COPY dockerrun.sh /usr/local/bin/dockerrun.sh
#RUN chmod +x /usr/local/bin/dockerrun.sh

echo "PWD: $PWD"
mv -v /opt/scripts/init-all.sh /usr/local/bin/init-all.sh
chmod +x /usr/local/bin/init-all.sh
chmod +x /usr/local/bin/run-app.sh

chmod +x /opt/scripts/init.d/*.sh
