clean:
	mvn clean

package:
	mvn  -Dfile.encoding=UTF-8 -DskipTests=true  package