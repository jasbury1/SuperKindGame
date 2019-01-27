rem Window version of run.sh
rem This is more primitive than the Unix version.
rem If you use sub-packages, you will want to add appropriate entries 
rem to the javac line.

set LIBS=%MATERIALS_DIR_305%/lib/testy.jar;%MATERIALS_DIR_305%/lib/spritely.jar

erase /s /q out
mkdir out
javac -Xlint:unchecked -Xmaxerrs 5 -sourcepath src  -cp %LIBS% -d out src\*.java src\*\*.java src\*\*\*.java
java -cp %LIBS%;out -ea Main %1 %2 %3 %4 %5 %6 %7 %8 %9
erase /s /q out
