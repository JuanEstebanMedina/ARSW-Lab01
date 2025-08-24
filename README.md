# ARSW-Lab01
PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH

Escuela Colombiana de Ingeniería Julio Garavito
Arquitecturas de Software - ARSW
Laboratorio Número 1

Members:
Juan Esteban Medina Rivas
María Paula Sánchez Macías

Part I - Introducción a Hilos en Java

start(): Esta funcion inicia un nuevo hilo que de manera independiente se ejecuta en paralelo. Este método utiliza paralelismo.
run(): Al llamar esta función, el proceso se ejecuta en el hilo actual. Este método utiliza ejecución secuencial.

Salida con start()
Se inician los tres hilos y se imprimen lo números de manera simultanea
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/1.%20PartI-start().png)

Salida con run()
Los números se muestran con total orden ya que los hilos se inician cuando el otro hilo acaba, no al tiempo.
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/2.%20PartI-run().png)


Part II - Ejercicio Black List Search

Al refactorizar, creamos una nueva Clase en la carpeta Threads que es la clase que contiene el ciclo de vida de nuestro Hilo.
Hicimos sobrecarga de métodos en la clase HostBlackListsValidator, poniendo un nuevo método CheckHost que recibe la dirección IP a revisar y el número de hilos a crear, y busca en la lista negra de las IP's al dividirse por segmentos en partes iguales para que cada hilo haga su respectiva búsqueda.

PartII.I:

¿Cómo se podría modificar la implementación para minimizar el número de consultas en estos casos?
Ideamos que una posible solución podría ser que haya una variable que compartan todos los hilos para poder consultar cuando una IP ya se ha catalogada como no confiable

¿qué elemento nuevo traería esto al problema?
Habría que sincronizar los hilos (sYnchronized), tocaría verificar constantemente el estado de la variable, habrían varios hilos modificando el contador al mismo tiempo


Part III - Evaluación de Desempeño
IP: 202.24.34.55

--> Un solo hilo.
visualvm
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/3.%20PartIII-1Thread.png)
Resultado
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/3.1%20PartIII-1ThreadResult.png)

--> Tantos hilos como núcleos de procesamiento
a Main se le agregan estas líneas de codigo
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/4.0%20PartIII-Thread%3DCoresCodigo.png)
visualvm
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/4.%20PartIII-Thread%3DCores.png)
Resultado: 16 procesadores
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/4.1%20PartIII-Thread%3DCoresResult1.png)

-->Tantos hilos como el doble de núcleos de procesamiento.
visualvm
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/5.%20PartIII-NumberCores.png)

-->50 hilos.
visualvm
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/6.%20PartIII-50Threads.png)

-->100 hilos
visualvm
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/7.%20PartIII-100Threads.png)
Cómo cada vez que crecía la cantidad de hilos se disminuía considerablemente el tiempo, al llegar a los 100 hilos el proceso se ejcutaba en un segundo o menos impidiendo a visualvm proporcionar información en las gráficas

Gráfica tiempo de solución vs. número de hilos
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/8.%20PartIII-TimevsThreads.png)
teniedo en cuenta:
* 1 hilo:  3MIN 25 SEG
* 16 hilos:  57SEG
* 32 hilos: 3SEG
* 50 hilos: 2SEG
* 100 hilos: 1SEG O MENOS

Se puede evidenciar que para este ejercicio la paralelización termina siendo muy efectiva, donde despues de los 32 hilos la grafica se equilibraba y depues de ahí no vale la pena poner tantos hilos a funcionar sabiendo que los beneficios son prácticamente los mismos