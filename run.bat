@echo off
echo ========================================
echo   PROGRAMACAO BACKEND - PROJETOS JAVA
echo ========================================
echo.
echo Escolha um projeto para executar:
echo.
echo 1. Sistema de Logistica de Entregas
echo 2. Sistema de Lista Telefonica
echo 3. CRUD-JDBC com PreparedStatement
echo 4. Sair
echo.
set /p choice="Digite sua opcao (1-4): "

if "%choice%"=="1" goto logistica
if "%choice%"=="2" goto lista
if "%choice%"=="3" goto crud
if "%choice%"=="4" goto sair
goto invalido

:logistica
echo.
echo Executando Sistema de Logistica de Entregas...
cd "Sistema de logística e entregas"
call mvn clean compile
call mvn exec:java
goto fim

:lista
echo.
echo Executando Sistema de Lista Telefonica...
cd "Lista telefônica"
call mvn clean compile
call mvn exec:java
goto fim

:crud
echo.
echo Executando CRUD-JDBC com PreparedStatement...
cd "CRUD - JDBC"
call mvn clean compile
call mvn exec:java -Dexec.mainClass="org.example.main.Main"
goto fim

:invalido
echo.
echo Opcao invalida! Tente novamente.
pause
goto inicio

:sair
echo.
echo Encerrando...
goto fim

:fim
echo.
echo Pressione qualquer tecla para continuar...
pause > nul
