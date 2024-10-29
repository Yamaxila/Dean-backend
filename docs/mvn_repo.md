## Загрузка в репозиторий

# ВРЕМЕННО УСТАРЕЛО

```
mvn clean package dokka:javadocJar source:jar deploy:deploy-file -Durl=http://dean-host-url/repo -DrepositoryId=reposirory -Dsources=target/Dean-backend-${project.version}-sources.jar -Djavadoc=target/Dean-backend-${project.version}-javadoc.jar -Dfile=target/Dean-backend-${project.version}.jar.original -DpomFile=pom.xml -DskipTests=true
```

### Подключение к другим проектам

```xml
<!--user.home/.m2/settings.xml]:-->
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <profiles>
    <profile>
      <id>vstu</id>
      <repositories>
        <repository>
          <id>repository</id>
          <name>VSTU</name>
          <url>http://dean-host-url/repo</url>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </releases>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>
</settings>


<!--[project.dir/pom.xml]:-->
<repositories>
  <repository>
    <id>repository</id>
    <url>http://dean-host-url/repo</url>
    <name>VSTU</name>
  </repository>
</repositories>
<!--...-->
<dependencies>
  <dependency>
    <groupId>by.vstu</groupId>
    <artifactId>Dean-backend</artifactId>
    <version>${dean.version}</version>
  </dependency>
</dependencies>

```
