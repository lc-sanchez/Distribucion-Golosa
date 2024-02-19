# Distribucion Golosa

El objetivo de este trabajo universitario es implementar un algoritmo goloso para una variante del problema de facility location.

Tenemos un conjunto C de clientes que debemos atender, cada uno geolocalizado con su latitud y longitud. También se tiene un conjunto D de puntos donde podemos ubicar centros de distribución para los clientes.
Estos puntos también están geolocalizados con su latitud y longitud. Finalmente, tenemos una cantidad k máxima de centros de distribución que podemos abrir. Estos k centros deben ubicarse en k puntos del conjunto D.

Cada cliente será atentido desde el centro de distribución más cercano, y el costo de atenderlo es igual a la distancia en línea recta entre cliente y su centro de distribución.
El problema consiste en determinar qué subconjunto de k puntos de D se deben selecionar para abrir centros de distribución, de modo tal que el costo total sea el menor posible. 

Implementamos una aplicación que contiene la siguiente funcionalidad:
* Leer los datos de latitud y longitud de clientes desde un archivo (el formato de archivo txt).
* Leer los datos de latitud y longitud de los posibles centros de distribución desde un archivo (el formato del archivo txt).
* Resuelve el problema de determinar qué centros de distribución abrir por medio de un algoritmo goloso.
* Muestra al usuario los centros de distribución que el algoritmo propone abrir, y el costo total de esta solución.

Como objetivos adicionales, decidimos contemplar los siguientes elementos:
* Mostrar los clientes y los posibles centros de distibución sobre un mapa.
* Escribir la solución, es decir, los centros de distribución para abrir en un archivo.
* Mostrar como estadística de la solución, la distancia promedio de cada centro de distribución que lo compone, con los clientes.

Informe de proyecto para la comisión [click aca](https://drive.google.com/file/d/1l8Js1ls_4w2vLZ4fAR4_Y9SFnl9zvG_H/view?usp=drive_link)
