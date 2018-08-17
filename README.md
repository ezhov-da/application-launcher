# application-launcher-desktop-app

Приложение реализует плагиновую функциональность и позволяет запускать приложения нажатием горячих клавиш.

Сборка: **mvn clean package** 

**Модули:**
1. application-gui - основной модуль, который реализует функциональность
1. application-test - тестовое приложения для демонстрации возможности

**Запуск приложения:**
1. Соберите проект
1. Создайте папку
1. Положите в папку jar файлы из:
    1. application-gui\target\application-gui.jar
    1. application-test\target\application-test-1.0-SNAPSHOT.jar
1. Положите в папку папку "plugins"
1. Создать .bat файл со следующим содержимым:  
    @echo off  
    cd /d %~dp0   
    start "run" "%JAVA_HOME%\bin\javaw" -Xmx768m -cp "application-gui.jar;application-test-1.0-SNAPSHOT.jar" "ru.ezhov.application.launcher.ApplicationLauncher"

    
**Для реализации своих плагинов необходимо:**
1. унаследовать свой класс от **com.tulskiy.keymaster.common.HotKeyListener**
1. зарегистрировать плагин в файле plugins\create.xml
1. указать путь к своему jar файлу в созданном .bat файле для запуска около application-gui.jar, разделив знаком ;
