@echo off

SET OPTION=%1
SET NOME_APP_PADRAO=cca-backoffice
echo ---
echo Recuperando informacoes do workspace
SET CAMINHO_BAT=%~dp0
echo BAT: %CAMINHO_BAT%
for %%B in (%CAMINHO_BAT%.) do set CAMINHO_INVOKER=%%~dpB
echo Invoker: %CAMINHO_INVOKER%
for %%B in (%CAMINHO_INVOKER%.) do set CAMINHO_CCA=%%~dpB
echo ContaCapitalRenovacao: %CAMINHO_CCA%
SET CAMINHO_EAR_PADRAO=%CAMINHO_CCA%EAR\cca-ear-backoffice\target\
echo Target EAR Backoffice: %CAMINHO_EAR_PADRAO%
echo Nome app padrao: %NOME_APP_PADRAO%
SET CAMINHO_SCRIPT=%CAMINHO_BAT%config.properties
echo Script: %CAMINHO_SCRIPT%
echo ---

for /R "%CAMINHO_EAR_PADRAO%" %%E in (*.ear) do set ARQUIVO_EAR=%%~nxE
if defined ARQUIVO_EAR ( 
	echo Conectando no Websphere [passando %ARQUIVO_EAR%]...
	"C:/Program Files/IBM/WebSphere/AppServer/bin/wsadmin.bat" -username wasadmin -password wasadmin -lang jython -f wsadmin.py %CAMINHO_SCRIPT% %CAMINHO_EAR_PADRAO%%ARQUIVO_EAR% %NOME_APP_PADRAO% %OPTION%
) else (
	echo Conectando no Websphere
	"C:/Program Files/IBM/WebSphere/AppServer/bin/wsadmin.bat" -username wasadmin -password wasadmin -lang jython -f wsadmin.py %CAMINHO_SCRIPT%
)