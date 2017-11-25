call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startchrome
echo.
echo Cannot run runcrud.bat - APLICATION STOPPED
goto fail

:startchrome
start start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot go to required URL - APPLICATION STOPPED

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.