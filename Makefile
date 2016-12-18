all: compile run clean

compile:
	cd ds
	javac -cp . *.java
	cd ..
	javac -cp . *.java

run:
	java -cp . Tester

clean:
	cd ds && rm *.class && cd ..
	rm *.class
