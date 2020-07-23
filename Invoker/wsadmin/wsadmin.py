##################################################################
# Script para automatizar administra��o do Websphere
# Autor: Nairon - Conta Capital
#################################################################

import datetime, java
from java.util import Properties
from java.io import FileInputStream

#################################################################
# Exibe linha de layout
#################################################################
def layout(): print '-'*50

#################################################################
# Retorna a data/hora atual formatada
#################################################################
def now():
	return datetime.datetime.now().strftime("%d/%m/%Y %H:%M:%S > ")

#################################################################
# Exibe o menu de op��es
#################################################################
def menu():
	layout()
	print """Administra��o Websphere - Op��es:
	1. Exibir apps
	2. Instalar app
	3. Desinstalar app
	4. Listar propriedades JVM
	5. Configurar propriedade JVM
	6. Remover propriedade JVM
	7. Listar usu�rios BD
	8. Adicionar usu�rio BD
	9. Remover usu�rio BD
	10. Listar datasources
	11. Criar datasource
	12. Remover datasource
	13. Testar datasource
	99. Executar script
	1337. Reinstalar app default
	0. Sair """
	layout()	
	option = input("Op��o: ")
	return option
	
#################################################################
# Exibe a exce��o do bloco "except"
# - mensagem: texto para preceder a exce��o
#################################################################	
def exception(mensagem):
	(kind, value) = sys.exc_info()[:2]
	(kind, value) = str(kind), str(value)
	print mensagem+" > "+kind+": "+value
	
#################################################################
# Verifica se uma aplica��o existe: 0 - n�o / 1 - sim
#################################################################    
def appExists(appName):
	found = 0
	installedApps = AdminApp.list().splitlines()
	if appName in installedApps:
		found = 1
	return found
	
#################################################################
# Instala uma aplica��o
# - appName: nome do app (alias)
# - appPath: caminho do EAR
#################################################################	
def install(appName, appPath):
	print now() + "Aguarde instala��o..."
	try:
		if appExists(appName) == 1:
			AdminApp.install(appPath, ['-update', '-appname', appName , '-MapWebModToVH', [['.*', '.*', 'default_host']], '-MapSharedLibForMod', [[ '.*', '.*', 'SICOOB_LIBS' ]] ])
		else:
			AdminApp.install(appPath, ['-appname', appName , '-MapWebModToVH', [['.*', '.*', 'default_host']], '-MapSharedLibForMod', [[ '.*', '.*', 'SICOOB_LIBS' ]] ])
		AdminConfig.save()
		print now() + "Instala��o conclu�da."
	except:
		exception("Erro (Verifique os par�metros informados)")

#################################################################
# Inicia uma aplica��o
# appName: nome do app (alias)
#################################################################	
def startApp(appName):
	print now() + "Aguarde inicializa��o..."
	try:
		appMgr = AdminControl.queryNames("type=ApplicationManager,node=nd1,process=server1,*" )
		AdminControl.invoke(appMgr, 'startApplication', appName)
		print now() + "App " + appName + " inicializado."
	except:
		exception("Erro (o app pode j� estar inicializado)")

#################################################################
# Retorna a lista de propriedades customizadas da JVM
#################################################################
def getJvmProps():
	return AdminConfig.getid("/Cell:cell01/Node:nd1/Server:server1/JavaProcessDef:/JavaVirtualMachine:/Property:/").splitlines()

#################################################################
# Lista as propriedades JVM
#################################################################	
def listCustomProps():
	jvmProps = getJvmProps()
	for jvmProp in jvmProps:
		name = AdminConfig.showAttribute(jvmProp, "name")
		value = AdminConfig.showAttribute(jvmProp, "value")
		print name + ": " + value

#################################################################
# Verifica se a propriedade JVM ja existe: 0 - n�o / 1 - sim
#################################################################
def propExists(propName):
	returnVal = 0
	jvmProps = getJvmProps()
	for jvmProp in jvmProps:
		name = AdminConfig.showAttribute(jvmProp, "name")
		if(propName == name):
			returnVal = 1
			break
	return returnVal

#################################################################
# Cria uma propriedade JVM
# - name: o nome da propriedade
# - value: o valor da propriedade
# - description: a descri��o da propriedade (opcional)
#################################################################
def createCustomProp(name, value, description):
	if (name == "" or name == None):
		print "Nome n�o informado!"
		return
   
	if (value == "" or value == None) :
		print "Valor n�o informado!"
		return
   
	jvmId = AdminConfig.getid("/Cell:cell01/Node:nd1/Server:server1/JavaProcessDef:/JavaVirtualMachine:/" )
   
	if propExists(name) == 0:
		attrib = ""
		if(name != "" and name != None):
			attrib += '[' + "name " + '"' + name + '"' + ']'
	   
		if(value != "" and value != None):
			attrib += '[' + "value " + '"' + value + '"' + ']'
	   
		if(description != "" and description != None):
			attrib += '[' + "description " + '"' + description + '"' + ']'
	   
		attrib =  "[" + attrib + "]"
		print attrib
		print "Criando a propriedade..."
		AdminConfig.create('Property', jvmId, attrib)
		AdminConfig.save()
	else:
		modifyCustomProp(name, value, description)

#################################################################
# Modifica uma propriedade JVM
# - name: o nome da propriedade
# - value: o valor da propriedade
# - description: a descri��o da propriedade (opcional)
#################################################################
def modifyCustomProp(name, value, description):
	if (name == "" or name == None):
		print "Nome n�o informado!"
		return
   
	if (value == "" or value == None) :
		print "Valor n�o informado!"
		return
   
	jvmProps = getJvmProps()
	found=0
	for jvmProp in jvmProps:
		existingName = AdminConfig.showAttribute(jvmProp, "name")
		if(existingName==name):
			found = 1
			break
			
	if found == 1:
		attrib = ""
		if(name != "" and name != None):
			attrib += '[' + "name " + '"' + name + '"' + ']'
	   
		if(value != "" and value != None):
			attrib += '[' + "value " + '"' + value + '"' + ']'
	   
		if(description != "" and description != None):
			attrib += '[' + "description " + '"' + description + '"' + ']'
	   
		attrib =  "[" + attrib + "]"
		print attrib
	   	print "Modificando propriedade [" + existingName + "]..."
		AdminConfig.modify(jvmProp, attrib)
		AdminConfig.save()
	else:
		print "Nenhuma modifica��o feita pois n�o foi encontrada a propriedade."

#################################################################
# Remove uma propriedade JVM
# - name: o nome da propriedade
#################################################################
def removeCustomProp(propName):
        found = 0
        jvmProps = getJvmProps()
        for jvmProp in jvmProps:
	        name = AdminConfig.showAttribute(jvmProp, "name")
	        if(propName == name):
	                value = AdminConfig.showAttribute(jvmProp, "value")
	                print "Propriedade ["+name+":"+value+"] encontrada!"
	                found = 1
	                break
	                
        if found == 1:
            print "Removendo propriedade..."
            AdminConfig.remove(jvmProp)
            AdminConfig.save()
        else:
            print "Propriedade ["+nome+"] n�o foi localizada."

#################################################################
# Retorna o status (Running/Stopped) de uma aplica��o
#################################################################
def getAppStatus(appName):
	objectName = AdminControl.completeObjectName('type=Application,name=' + appName + ',*')
	if objectName == "":
		appStatus = 'Running'
	else:
		appStatus = 'Stopped'
	return appStatus

#################################################################	
# Listar usu�rios BD
#################################################################
def listBDUsers():
	jaasAuthDataList = AdminConfig.list("JAASAuthData").splitlines() 
	for jaasAuthId in jaasAuthDataList:
		alias = AdminConfig.showAttribute(jaasAuthId, "alias")
		print alias

#################################################################
# Cria um usu�rio BD
# - name: o nome 
# - password: a senha
#################################################################
def createBDUser(name, password):
	alias = "nd1/"+name
	alias_attr = ["alias" , alias]
	desc_attr = ["description" , ""]
	userid_attr = ["userId" , name]
	password_attr = ["password" , password]
	attrs = [alias_attr, desc_attr, userid_attr, password_attr]
	sec = AdminConfig.getid('/Cell:cell01/Security:/')
	try:
		authdata = AdminConfig.create('JAASAuthData', sec, attrs)
		AdminConfig.save()
		print "Criado novo usu�rio com alias: "+ alias
	except:
		exception("Erro ao criar usu�rio BD")
		
#################################################################
# Remove um usu�rio BD
# alias: alias do usuario
#################################################################		
def removeBDUser(alias):
	found = 0
	jaasAuthDataList = AdminConfig.list("JAASAuthData").splitlines() 
	for jaasAuthId in jaasAuthDataList:
		aliasWas = AdminConfig.showAttribute(jaasAuthId, "alias")
		if cmp(aliasWas,alias) == 0:
			print "Removendo usu�rio..."
			try:
				AdminConfig.remove(jaasAuthId)		
				AdminConfig.save()
			except:
				exception("Erro ao remover usu�rio BD")
			found = 1
			break
	if found == 0:
            print "Usu�rio ["+alias+"] n�o foi localizado."
            
#################################################################
# Listar datasources           
#################################################################
def listDatasources():
	dsList = AdminConfig.list("DataSource").splitlines() 
	print "Nome | JNDI | Base de dados | Servidor | Porta | Usu�rio"
	for dsId in dsList:
		name = AdminConfig.showAttribute(dsId, "name")
		jndi = AdminConfig.showAttribute(dsId, "jndiName")
		text = str(name) + " | " + str(jndi) + " | " 
		auth = AdminConfig.showAttribute(dsId, "authDataAlias")
		propertySet = AdminConfig.showAttribute(dsId, "propertySet")
		propertyList = AdminConfig.list('J2EEResourceProperty', propertySet).splitlines()
		props = ["databaseName", "serverName", "portNumber"]
		for property in propertyList:
			propertyName = AdminConfig.showAttribute(property, 'name')
			if propertyName in props:
				propertyValue = AdminConfig.showAttribute(property, 'value')
				text += str(propertyValue) + " | "
		text += str(auth)
		print text

#################################################################
# Criar datasource
# - bd: 1 - SQL Server / 2 - DB2
# - ds: nome do datasource
# - jndi: nome JNDI
# - database: nome da base
# - server: nome do servidor
# - port: porta
# - user: alias do usu�rio
#################################################################
def createDatasource(bd, ds, jndi, database, server, port, user):
	scope = "Cell=cell01,Node=nd1,Server=server1"
	print "Criando datasource "+ds
	try:
		if bd == "1":
			AdminJDBC.createDataSourceAtScope(scope, "Microsoft SQL Server JDBC Driver (XA)", ds, jndi,
			"com.ibm.websphere.rsadapter.MicrosoftSQLServerDataStoreHelper", database,
			[
				['xaRecoveryAuthAlias', user],  
				['componentManagedAuthenticationAlias', user],  
				['containerManagedPersistence', 'true'],
				['description', '']
			],
			[['serverName', server], ['portNumber', port]])
			print "Datasource criado"
		elif bd == "2":
			AdminJDBC.createDataSourceAtScope(scope, "DB2 Universal JDBC Driver Provider (XA)", ds, jndi,
			"com.ibm.websphere.rsadapter.DB2UniversalDataStoreHelper", database,
			[
				['xaRecoveryAuthAlias', user],  
				['componentManagedAuthenticationAlias', user],  
				['containerManagedPersistence', 'true'],
				['description', '']
			],
			[['serverName', server], ['portNumber', port], ['driverType', 4]])
			print "Datasource criado"
		else:
			print "BD inv�lido."
	except:
		exception("Erro ao criar datasource")

#################################################################		
# Remove um datasource
# - ds: nome do datasource		
#################################################################
def removeDatasource(ds):
	dsid = AdminConfig.getid('/DataSource:'+ ds +'/')
	if len(dsid) > 0:
		print "Removendo datasource "+dsid
		AdminConfig.remove(dsid)
		AdminConfig.save()
		print "OK"
	else:
		print "Datasource n�o encontrado"

#################################################################		
# Testa a conex�o com um datasource
# - ds: nome do datasource		
#################################################################
def testDatasource(ds):
	print "Recuperando conex�o "+ds
	dsid = AdminConfig.getid('/DataSource:'+ ds +'/')
	if len(dsid) > 0:
		print dsid
		print AdminControl.testConnection(dsid)
	else:
		print "Datasource n�o encontrado"	
		
#################################################################    
# Executa o script
#################################################################
def executarScript():
	print "--- Execu��o de script ---"
	print "Criando usu�rios BD"
	usuarios = properties.getProperty("USUARIOS").split(",")
	for name in usuarios:
		password = properties.getProperty(name)
		print "USR: " + str(name) + " | PWD: " + str(password)
		createBDUser(name, password)
	print "Criando datasources"
	datasources = properties.getProperty("DATASOURCES").split(",")
	for ds in datasources:
		configDS = properties.getProperty(ds).split(",")
		bd = configDS[0]
		jndi = configDS[1]
		database = configDS[2]
		server = configDS[3]
		port = configDS[4]
		user = "nd1/" + configDS[5]
		print "BD: "+ bd + " | DS: " + str(ds) + " | JNDI: " + str(jndi) + " | DB: " + database + " | Server: " + server + " | Port: " + port + " | User: "+user
		createDatasource(bd, ds, jndi, database, server, port, user)

def reinstalar():
	layout()
	print "-- Reinstala��o de app --"
	print now() + "Aguarde reinstala��o..."
	
	if appExists(appNameDefault):
		try:
			AdminApp.uninstall(appNameDefault)
			AdminConfig.save()
		except:
			exception("Erro ao desinstalar.")			
	
	try:
		install(appNameDefault, appPathDefault)
		startApp(appNameDefault)
		print now() + "Reinstala��o conclu�da."
	except:
		exception("Erro ao instalar.")
		
#################################################################    
# Ciclo principal
#################################################################
def main():
	
	if len(sys.argv) > 3 and sys.argv[3] != "":
		if sys.argv[3] == "AutoRun":
			reinstalar()
			return
		
	while 1:	
		try:
			option = -1
			option = menu()
		except Exception as e:
			exception("Op��o inv�lida: " + e)
		if option == 1:
			layout()
			print "-- Lista de apps --"
			print AdminApp.list()
		elif option == 2:
			layout()
			print "-- Instala��o de app --"
			if len(sys.argv) < 2:
				default = "n"
			else:
				print "Valores padr�es:"
				print "Caminho do EAR: " + appPathDefault
				print "Nome do app: " + appNameDefault
				default = raw_input("Utilizar valores padr�es? (s/n): ")
			if default == "s":
				install(appNameDefault, appPathDefault)
				startApp(appNameDefault)
			else:
				appPath = raw_input("Caminho do EAR: ")
				appName = raw_input("Nome do app: ")
				install(appName, appPath)
				startApp(appName)
		elif option == 3:
			layout()
			print "-- Desinstala��o de app --"
			appName = raw_input("Nome do app: ")
			print now() + "Aguarde desinstala��o..."
			try:
				AdminApp.uninstall(appName)
				AdminConfig.save()
				print now() + "Desinstala��o conclu�da."
			except:
				exception("Erro ao desinstalar")
		elif option == 4:
			print "-- Lista de propriedades JVM --"
			listCustomProps()
		elif option == 5:
			print "-- Configura��o de propriedade JVM --"
			name = raw_input("Nome: ")
			value = raw_input("Valor: ")
			description = raw_input("Descri��o (opcional): ")
			createCustomProp(name, value, description)
		elif option == 6:
			print "-- Remo��o de propriedade JVM --"
			name = raw_input("Nome: ")
			removeCustomProp(name)
		elif option == 7:
			print "-- Lista de usu�rios BD --"
			listBDUsers()
		elif option == 8:
			print "-- Configura��o de usu�rio BD --"
			name = raw_input("Usu�rio: ")
			password = raw_input("Senha: ")
			createBDUser(name, password)
		elif option == 9:
			print "-- Remo��o de usu�rio BD --"
			alias = raw_input("Alias: ")
			removeBDUser(alias)
		elif option == 10:
			print "-- Lista de datasources --"
			listDatasources()
		elif option == 11:
			print "-- Cria��o de datasource --"
			bd = raw_input("Tipo de Banco (1 - SQL Server / 2 - DB2): ")
			ds = raw_input("Datasource: ")
			jndi = raw_input("JNDI: ")
			database = raw_input("Base de dados: ")
			server = raw_input("Servidor: ")
			port = raw_input("Porta: ")
			user = raw_input("Alias autentica��o: ")
			createDatasource(bd, ds, jndi, database, server, port, user)
			testDatasource(ds)
		elif option == 12:
			print "-- Remo��o de datasource --"
			ds = raw_input("Datasource: ")
			removeDatasource(ds)
		elif option == 13:
			print "-- Teste de datasource --"
			ds = raw_input("Datasource: ")
			testDatasource(ds)
		elif option == 99:
			executarScript()
		elif option == "1337" or option == 1337:
		    reinstalar()
		elif option == 0:
			print "Encerrando programa..."
			break
		else:
			print "Op��o inv�lida. " + option

#################################################################
# Verifica��o de valores padr�o
#################################################################
if len(sys.argv) > 1:
	appPathDefault = sys.argv[1].replace("\t","\\t").replace("\r", "\\r")
	appNameDefault = sys.argv[2]
	print "Valores padr�es armazenados."

properties = Properties();
properties.load(FileInputStream(sys.argv[0].replace("\t","\\t").replace("\r", "\\r")))

main()