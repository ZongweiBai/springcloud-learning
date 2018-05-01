#!/bin/bash
# chkconfig: 2345 10 90
# Description:  This shell script takes care of starting and stopping myservice
# XD created on Marcy. 26th, 2015
#
# Source function library
./etc/init.d/functions

#the service name
SNAME=myservice

#程序所在路径
WORKPATH=/home/work/myservice

#the full path and name of the daemon program
#Warning: The name of executable file must be identical with service name
PROG="$WORKPATH/$SNAME"
RETVAL=0

# start function
start() {
    #check the iccserver status first
    if [ -f /var/lock/subsys/$SNAME ]
    then
        echo "$SNAME is already started!"
        exit 0;
    else
        echo -n $"Starting $SNAME... "
	daemon $PROG
        RETVAL=$?
	echo
   	[ $RETVAL -eq 0 ] && touch /var/lock/subsys/$SNAME
	return $RETVAL
    fi
}

#stop function
stop() {
    echo "Stopping $SNAME ..."
    killproc $SNAME
    rm -rf /var/lock/subsys/$SNAME
}

case "$1" in
start)
  start
  ;;
stop)
  stop
  ;;
reload|restart)
  stop
  start
  ;;
status)
  status $SNAME
  RETVAL=$?
  ;;
*)
  echo $"Usage: $0 {start|stop|restart|status}"
  exit 1
esac

exit $RETVAL