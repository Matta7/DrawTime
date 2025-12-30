cd ..
call mvn clean package
call jlink --module-path "%JAVA_HOME%\jmods" --add-modules java.base,java.desktop --output runtime
call jpackage --type exe --name DrawTime --app-version 1.12 --description DrawTime --icon src/main/resources/icon.ico --input target --main-jar DrawTime-1.12.jar --main-class run.Main --win-dir-chooser --win-menu --win-shortcut-prompt
@RD /S /Q "runtime"