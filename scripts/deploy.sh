# 환경 변수 갱신
source /home/ec2-user/.bash_profile
ABSPATH=$(readlink -f "$0")
ABSDIR=$(dirname $ABSPATH)
source "${ABSDIR}"/profile.sh


REPOSITORY=/home/ec2-user/huddleUp

echo "> build 파일 복사"
cp $REPOSITORY/build/libs/*SNAPSHOT.jar $REPOSITORY/

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

chmod +x $JAR_NAME

IDLE_PROFILE=$(find_idle_profile)
echo "> 새 애플리케이션을 실행합니다."
nohup java -jar \
          -Dspring.profiles.active="$IDLE_PROFILE" \
          -Dspring.config.location=classpath:/application.yml,/home/ec2-user/application-oauth.yml,/home/ec2-user/application-s3.yml \
           "$JAR_NAME" > /home/ec2-user/huddleUp/"$IDLE_PROFILE"/nohup.out 2>&1 &
