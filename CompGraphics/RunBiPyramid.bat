@echo off

javac -cp ./jogl-all.jar;./gluegen-rt.jar;./ BiPyramid.java

java -cp jogl-all.jar;gluegen-rt.jar;. BiPyramid

PAUSE
