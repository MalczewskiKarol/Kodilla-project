call runcrud.bat
if "%ERRORLEVEL%" == "0" goto wait30sec
echo.
echo Cannot run runcrud.bat - APLICATION STOPPED
goto fail

:wait30sec
sleep 30
if "%ERRORLEVEL%" == "0" goto startchrome
echo.
echo Cannot wait 30 sec - APPLICATION STOPPED
goto fail

:startchrome
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot go to required URL - APPLICATION STOPPED

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.