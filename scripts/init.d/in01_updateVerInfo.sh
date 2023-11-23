#!/bin/bash
#Purpose: This script will replace the environment variables in a webpage file of the application
#The webpage is used to differentiate deployment versions beween every rollout
#sed -i 's/Master/'\"$imgname-$BUILD_NUMBER\"'/g' ${WORKSPACE}/src/main/resources/static/issuegenerator.html

#/src/main/resources/static/issuegenerator.html

sname=$(basename $BASH_SOURCE)
sdir=$(cd `dirname $BASH_SOURCE` && pwd)

echo "[Script: $sdir/$sname] Updating webpage content to include App_Ver, Color, and HostName"

#Container HostName
export CHOSTNAME=`cat /etc/hostname`
envsubst 

cat test.txt 
echo ---
envsubst  < test.txt > test-updated.txt
echo ---
mv -v test-updated.txt test.txt
cat test.txt

#Update the html file to differentiate deployment version
jar -xf issuegen-0.1.jar BOOT-INF/classes/static/issuegenerator.html
jar -uf issuegen-0.1.jar BOOT-INF/classes/static/issuegenerator.html
