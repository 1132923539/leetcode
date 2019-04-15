#!/bin/bash

anynowtime="date +'%Y-%m-%d %H:%M:%S'"
NOW="echo [\`$anynowtime\`][PID:$$]"
workspace=$1
servicename=$2

##### 可在脚本开始运行时调用，打印当时的时间戳及PID。
function job_start
{
    sourceFile=`ls -t ${workspace}/${servicename}/*.jar |head -1`
    echo "Deploy file is ${sourceFile}"
    systemctl stop ${servicename}
    sleep 10s
    cp ${sourceFile} ${workspace}/${servicename}.jar


    cat << EOF > /etc/systemd/system/${servicename}.service
[Unit]
Description=${servicename}
Requires=network-online.target
After=network-online.target

[Service]
EnvironmentFile=-/usr/bin/java
Environment=GOMAXPROCS=2
Restart=on-failure
ExecStart=/usr/bin/java -server -Dspring.profiles.active=qcloud -Xms512m -Xmx1024m -jar /data/work/${servicename}.jar
ExecReload=/bin/kill -HUP \$MAINPID
KillSignal=SIGTERM

[Install]
WantedBy=multi-user.target
EOF

    systemctl daemon-reload
    systemctl enable ${servicename}
    systemctl start ${servicename}
    echo "`eval $NOW` job_start"
}

##### 可在脚本执行成功的逻辑分支处调用，打印当时的时间戳及PID。
function job_success
{
    MSG="$*"
    echo "`eval $NOW` job_success:[$MSG]"
    exit 0
}

##### 可在脚本执行失败的逻辑分支处调用，打印当时的时间戳及PID。
function job_fail
{
    MSG="$*"
    echo "`eval $NOW` job_fail:[$MSG]"
    exit 1
}

job_start

###### 可在此处开始编写您的脚本逻辑代码
###### 作业平台中执行脚本成功和失败的标准只取决于脚本最后一条执行语句的返回值
###### 如果返回值为0，则认为此脚本执行成功，如果非0，则认为脚本执行失败
