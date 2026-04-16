# Calculadora em Java

## Introdução

Esta é uma recriação da calculadora padrão do Windows feito em Java, usando o JavaFX para criar o front end da mesma. Projeto tem algumas alterações a serem feitas porém irei mexer conforme o tempo com o mesmo.

## Como Usar

### Maven

1. No Prompt de Comando/PowerShell/Bash, no diretório que contém o pom.xml, digite:
`mvn clean compile`

2. Após a mensagem BUILD SUCCESS, digite:
`mvn exec:java -Dexec.mainClass="gui.AppLauncher"`

## JavaC

1. No Prompt de Comando/PowerShell/Bash, no diretório do projeto, digite:
`javac --module-path "Y:\Bruxarias do Java\JavaLibs\javafx-sdk-21.0.10\lib" --add-modules javafx.controls,javafx.fxml -d target/classes src/main/java/calculadora/*.java src/main/java/gui/*.java`

2. Após compilar, basta digitar:
`java --module-path "Y:\Bruxarias do Java\JavaLibs\javafx-sdk-21.0.10\lib" --add-modules javafx.controls,javafx.fxml -cp target/classes gui.AppLauncher`

<img width="461" height="676" alt="image" src="https://github.com/user-attachments/assets/9cbe1f2f-229d-4ec5-a860-d9b2ffdd4c35" />
