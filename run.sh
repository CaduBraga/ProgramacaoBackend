#!/bin/bash

echo "========================================"
echo "   PROGRAMACAO BACKEND - PROJETOS JAVA"
echo "========================================"
echo
echo "Escolha um projeto para executar:"
echo
echo "1. Sistema de Logistica de Entregas"
echo "2. Sistema de Lista Telefonica"
echo "3. CRUD-JDBC com PreparedStatement"
echo "4. Sair"
echo
read -p "Digite sua opcao (1-4): " choice

case $choice in
    1)
        echo
        echo "Executando Sistema de Logistica de Entregas..."
        cd "Sistema de logística e entregas"
        mvn clean compile
        mvn exec:java
        ;;
    2)
        echo
        echo "Executando Sistema de Lista Telefonica..."
        cd "Lista telefônica"
        mvn clean compile
        mvn exec:java
        ;;
    3)
        echo
        echo "Executando CRUD-JDBC com PreparedStatement..."
        cd "CRUD - JDBC"
        mvn clean compile
        mvn exec:java -Dexec.mainClass="org.example.main.Main"
        ;;
    4)
        echo
        echo "Encerrando..."
        exit 0
        ;;
    *)
        echo
        echo "Opcao invalida! Tente novamente."
        exit 1
        ;;
esac

echo
echo "Pressione Enter para continuar..."
read
