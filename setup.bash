#!/bin/bash 
export HOST=$1

echo "Setting hostname"
ssh root@$HOST hostnamectl set-hostname $HOST
echo "Restarting Network"
ssh root@$HOST /etc/init.d/network restart 
echo "Checking hostname"
ssh root@$HOST hostname

echo "test"



