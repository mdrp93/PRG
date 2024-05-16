@echo off
rem Comprobamos si estamos en Windows
if "%OS%"=="Windows_NT" (
    start cmd /k "java -jar R05_TRIBUNAL.jar %1"
) else (
    x-terminal-emulator -e "java -jar R05_TRIBUNAL.jar $1"
)


rem Este script primero verifica si está en un sistema Windows o no.
rem Si está en Windows, usa start cmd /k para abrir la consola de Windows y ejecutar el comando Java.
rem Si no está en Windows, asume que está en Linux y usa x-terminal-emulator -e para abrir la terminal y ejecutar el comando Java