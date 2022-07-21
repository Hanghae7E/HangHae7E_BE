function find_idle_profile()
{
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/api/running-port)

    if [ "${RESPONSE_CODE}" -ge 400 ]
    then
        CURRENT_PROFILE=test2
    else
        CURRENT_PROFILE=$(curl -s http://localhost/api/running-port)
    fi

    if [ "${CURRENT_PROFILE}" == test1 ]
    then
      IDLE_PROFILE=tese2
    else
      IDLE_PROFILE=test1
    fi

    echo "${IDLE_PROFILE}"
}

function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ "${IDLE_PROFILE}" == test1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}
