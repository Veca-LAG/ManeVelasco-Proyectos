/*fecha de creaci�n 21 de junio 2023, 
�ltima fecha de modificaci�n 25 de junio
individual Mane
el programa har� primero un grafo que pedir� un numero de v�rtices y sus distancias entre ellos, y se utilizara el algoritmo de Dijkstra, pidiendo el v�rtice inicial para imprimir*/
#include<stdio.h>/*printf, scanf*/
#include <stdbool.h>/* bool true o false*/
#include <limits.h>/*INT_MAX numero infinito*/
#include <locale.h>/*setlocale*/
#define M 12/*el mayor n�mero de vertices*/

/*variables globales de entrada*/
	int vn,vma,x,y,i,rep=1;
	float matriz[M][M];
/*matriz[x][y]=w({u,v})	L[u]=vi	*/
/*una variable entera pedir� el n�mero de v�rtices que pueden ser entre 1 y 12, este siendo el l�mite por el momento y si no se cumple se repetir� el pedir el n�mero de v�rtices hasta que este sea igual o menor a 12 y mayor a 1 y se le pedir� con otra variable entera un peso para los vectores no adyacentes ejemplo -1 y que despu�s de agregar el peso de las aristas a una matriz flotante y este fuera igual a -1 este sera infinito */	
void grafo ()
{
	do/*hacer mientras el n�mero de v�rtices se encuentre entre 1 y el n�mero de v�rtices decidido*/	
{
	/*entrada*/
		/*pedir n�mero de v�rtices y el peso de las aristas y si estas no son adyacentes son infinitas*/ 
		printf("ingrese el n�mero de v�rtice entre 1 a %d= ",M);	scanf("%d",&vn);
		if(vn<=M && vn>=1)/* si mayor a M=12 y menor a 1 se repite*/
		{
			rep=0;	
			printf("\n\n ingrese la matriz de pesos del grafo para v�rtices no adyacentes, de ");	scanf("%d",&vma);
			printf("\n");
		
		/*agregar datos a matriz*/
			for(x=1;x<=vn;x++)
			{
				for(y=1;y<=vn;y++)
				{
					if(x==y || x<y) /*solo se pedir� de la diagonal principal para arriba para no tener repetidos*/
					{	printf("ingrese el peso del V%d al V%d  ",x,y);	scanf("%f",&matriz[x][y]);	}
					else
					{	matriz[x][y]=matriz[y][x];	}/*los que poseen los mismos v�rtices pero en diferente orden son iguales*/
				}		
			}
				
		/*impresi�n de matriz de pesos*/
			printf("\n\nla matriz de pesos ingresada\n");
			for(x=0; x<=vn;x++)
			{
				for(y=0; y<=vn;y++)
				{
					if(x==0 && y==0)
					{	printf(" \t");	} /*un espacio vac�o para ordenar la matriz de pesos*/
					
					else if(x==0 && y>=1)
					{	printf("V%d\t",y);	} /*el orden de los numero vectores de la matriz de pesos de manera vertical*/
					
					else if(y==0 && x>=1)
					{	printf("V%d\t",x);	} /*el orden de los numero vectores de la matriz de pesos de manera horizontal*/
						
					else if(matriz[x][y]==vma)
					{	printf("INF\t");	} /*v�rtices no adyacentes infinitos*/
						
					else
					{	printf("%.2f\t",matriz[x][y]);	} /*peso de aristas*/
						
				}	printf("\n");
			}
		}
		else /*error el n�mero de v�rtice es menor a uno o mayor al n�mero m�ximo predeterminado*/
		{
			printf("\nERROR NUMERO DE VERTICE MAYOR A 12 O MENOR A 1\n");
			rep=1;
		}
	}while(rep!=0); /*repetir por el error*/
}
	
/*pedir el v�rtice inicial y que este no sea menor a uno y mayor al n�mero de v�rtices ya ingresado, si no se repetir�, se ingresara dos nuevas variables otro vector flotante de distancia donde se almacenara al v�rtice menor y se guardara, gracias a la variable de bool que guarda mediante en falso y verdadero esto se repetir� hasta que x sea igual n�mero de vectores, as� habr�n pasado todos los vectores, usando el algoritmo de Dijkstra que mediante el v�rtice m�s cercano da el menor de los v�rtices*/
int vi;/*v�rtice inicial*/
void diska()
{	/*algoritmo de Dijkstra*/
	do/*hacer mientras el v�rtice inicial se encuentre entre 1 y el n�mero de v�rtices decidido*/
	{
	/*pedir v�rtice inicial*/
		printf("\n\nindique el v�rtice inicial entre los v�rtices 1 a %d: ",vn);		scanf("%d",&vi);
		if(vi<=vn && vi>=1) /*decisi�n si el v�rtice inicial se encuentre entre 1 y el n�mero de v�rtices decidido*/
		{
			rep=0;
			/*variables importantes para Dijkstra */
			float dis[M];/*distancia*/	bool vecino[M];/*si ya fue visitado es true o si no fue visitado false*/

			/*vecinos de v�rtice inicial y aun no visitados*/
			for(i=1;i<=vn;i++)
			{	dis[i]=INT_MAX;	vecino[i]=false;		}
			/*v�rtice inicial = cero*/
			dis[vi]=0;
			
			/*mientras con x y vn no sean iguales*/
			for (x=1;x<=vn;x++) 
			{
				/*m�nimo en las variables*/
		        float mdis=INT_MAX;
		        int mi=-1;
		        
		        /* distancia m�nima de v�rtice*/
		        for (y=1;y<=vn;y++) 
				{
		            if (!vecino[y] && dis[y] <= mdis) /*si vecino no ha sido visitado y la nueva cantidad menor al infinito*/
					{   mdis = dis[y];   mi = y;    }
		        }
		        
		        /*visitado y no cambiable*/
		        vecino[mi] = true;
		        
		        /*cambiar distancia por menor mediante formula*/
		        for (y=1;y<=vn;y++) 
				{
/*si vecino no ha sido visitado y el valor de la suma es menor hacer f�rmula para agregar distancia a v�rtice y el valor no sea infinito y no sea no adyacente*/
		            if (!vecino[y] && matriz[mi][y] != INT_MAX && dis[mi] != INT_MAX && dis[mi] + matriz[mi][y] < dis[y] &&matriz[mi][y] != vma && dis[mi] != vma ) 
					{	dis[y] = dis[mi] + matriz[mi][y];	}          
		        }
		        
		    }
		    
		    /*salida de las distancias de cada v�rtice que da con el v�rtice inicial*/
		    printf("Distancia m�s corta del v�rtice inicial V%d:\n", vi);
		    for (i=1; i<=vn; i++) {	printf("V%d\t", i);	}
		    printf("\n");
		    for (i=1; i<=vn; i++) {	printf("%.2f\t",dis[i]);	}
		}
		else/* error el v�rtice inicial es menor a 1 y mayor al n�mero de v�rtices decidido*/
		{
			printf("\nERROR VERTICE NO SE ENCUENTRA EN EL GRAFO, ESCOGER OTRO");
			rep=1;
		}
	}while(rep!=0);
}


main()
{	/*para imprimir con acento*/setlocale(LC_ALL, ""); 	grafo();	diska();	}

