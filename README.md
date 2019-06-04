# SampleApp

This is a siple app that does the following
Allow a user to do the following 
1) Add Server (id, name, description)
2) Edit Server (name, description)
3) Delete Server (id)
4) Count Number of Servers
5) List Servers
Also
6) Provide help / usage info
7) Provide the Ability to Add Server from XML
2) Provide Filtering / Paging on List Servers

Some useful commands:
1)  To run
    <br /> Run to package: ```mvn clean package to build the project```
    <br />Then to run: ```mvn spring-boot:run```
2)  To run from end an back end seperatly you must configure angular to point to the spring-boot port (8080) for rest calls
    <br />```ng serve: --proxy-config proxy.conf.json```
3)  To run spring-boot in debug mode
    <br />```mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"```    
    
