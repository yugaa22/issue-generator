#!/bin/bash
##====> for simple issuegen-0.1
(cd /opt;nohup java  -Dserver.port=8088 -jar /opt/issuegen-0.1.jar > /var/log/issuegen-0.1.log 2>&1 &)
sudo chmod 775 /var/log/issuegen-0.1.log
sudo tail -f /var/log/issuegen-0.1.log &

sleep infinity
