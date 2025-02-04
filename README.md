# Технологии
* Java 17.0.2

# Инструкция по запуску:

*Скачать Main.java в любую удобную папку
*Открыть командную строку и перейти в директорию с скачанным кодом.
*Компилируем код с использованием нужной кодировки (в моем случае это UTF-8)
javac -encoding utf-8 Main.java
*Создаем jar 
jar cfe SortUtil.jar Main Main.class

*Файлы, которые подаются в программу должны находиться в текущей папке, либо к каждому из них должен быть указан полный путь.
*Выходные файлы по умолчанию перезаписываются, но можно задать режим добавления при помощи аргумента "-a".
*Префикс имени выходных файлов задается аргументом "-p" через пробел: 
"-p sample-"
*Путь выходных файлов задается при помощи аргумент "-o" через пробел: 
"-o /some/path"
*Краткую и полную статистику о проделанной фильтрации можно узнать через аргументы "-s" и "-f" соответственно.
*Все аргументы должны задаваться через пробел.

*Пример запуска:
java -jar SortUtil.jar -s -a -p sample- in1.txt in2.txt
