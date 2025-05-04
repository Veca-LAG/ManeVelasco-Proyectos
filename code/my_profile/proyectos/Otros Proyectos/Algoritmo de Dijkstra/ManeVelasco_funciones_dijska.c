/*fecha de creación 21 de junio 2023, 
última fecha de modificación 25 de junio
individual Mane
el programa hará primero un grafo que pedirá un numero de vértices y sus distancias entre ellos, y se utilizara el algoritmo de Dijkstra, pidiendo el vértice inicial para imprimir*/
#include<stdio.h>/*printf, scanf*/
#include <stdbool.h>/* bool true o false*/
#include <limits.h>/*INT_MAX numero infinito*/
#include <locale.h>/*setlocale*/
#define M 12/*el mayor número de vertices*/

/*variables globales de entrada*/
	int vn,vma,x,y,i,rep=1;
	float matriz[M][M];
/*matriz[x][y]=w({u,v})	L[u]=vi	*/
/*una variable entera pedirá el número de vértices que pueden ser entre 1 y 12, este siendo el límite por el momento y si no se cumple se repetirá el pedir el número de vértices hasta que este sea igual o menor a 12 y mayor a 1 y se le pedirá con otra variable entera un peso para los vectores no adyacentes ejemplo -1 y que después de agregar el peso de las aristas a una matriz flotante y este fuera igual a -1 este sera infinito */	
void grafo ()
{
	do/*hacer mientras el número de vértices se encuentre entre 1 y el número de vértices decidido*/	
{
	/*entrada*/
		/*pedir número de vértices y el peso de las aristas y si estas no son adyacentes son infinitas*/ 
		printf("ingrese el número de vértice entre 1 a %d= ",M);	scanf("%d",&vn);
		if(vn<=M && vn>=1)/* si mayor a M=12 y menor a 1 se repite*/
		{
			rep=0;	
			printf("\n\n ingrese la matriz de pesos del grafo para vértices no adyacentes, de ");	scanf("%d",&vma);
			printf("\n");
		
		/*agregar datos a matriz*/
			for(x=1;x<=vn;x++)
			{
				for(y=1;y<=vn;y++)
				{
					if(x==y || x<y) /*solo se pedirá de la diagonal principal para arriba para no tener repetidos*/
					{	printf("ingrese el peso del V%d al V%d  ",x,y);	scanf("%f",&matriz[x][y]);	}
					else
					{	matriz[x][y]=matriz[y][x];	}/*los que poseen los mismos vértices pero en diferente orden son iguales*/
				}		
			}
				
		/*impresión de matriz de pesos*/
			printf("\n\nla matriz de pesos ingresada\n");
			for(x=0; x<=vn;x++)
			{
				for(y=0; y<=vn;y++)
				{
					if(x==0 && y==0)
					{	printf(" \t");	} /*un espacio vacío para ordenar la matriz de pesos*/
					
					else if(x==0 && y>=1)
					{	printf("V%d\t",y);	} /*el orden de los numero vectores de la matriz de pesos de manera vertical*/
					
					else if(y==0 && x>=1)
					{	printf("V%d\t",x);	} /*el orden de los numero vectores de la matriz de pesos de manera horizontal*/
						
					else if(matriz[x][y]==vma)
					{	printf("INF\t");	} /*vértices no adyacentes infinitos*/
						
					else
					{	printf("%.2f\t",matriz[x][y]);	} /*peso de aristas*/
						
				}	printf("\n");
			}
		}
		else /*error el número de vértice es menor a uno o mayor al número máximo predeterminado*/
		{
			printf("\nERROR NUMERO DE VERTICE MAYOR A 12 O MENOR A 1\n");
			rep=1;
		}
	}while(rep!=0); /*repetir por el error*/
}
	
/*pedir el vértice inicial y que este no sea menor a uno y mayor al número de vértices ya ingresado, si no se repetirá, se ingresara dos nuevas variables otro vector flotante de distancia donde se almacenara al vértice menor y se guardara, gracias a la variable de bool que guarda mediante en falso y verdadero esto se repetirá hasta que x sea igual número de vectores, así habrán pasado todos los vectores, usando el algoritmo de Dijkstra que mediante el vértice más cercano da el menor de los vértices*/
int vi;/*vértice inicial*/
void diska()
{	/*algoritmo de Dijkstra*/
	do/*hacer mientras el vértice inicial se encuentre entre 1 y el número de vértices decidido*/
	{
	/*pedir vértice inicial*/
		printf("\n\nindique el vértice inicial entre los vértices 1 a %d: ",vn);		scanf("%d",&vi);
		if(vi<=vn && vi>=1) /*decisión si el vértice inicial se encuentre entre 1 y el número de vértices decidido*/
		{
			rep=0;
			/*variables importantes para Dijkstra */
			float dis[M];/*distancia*/	bool vecino[M];/*si ya fue visitado es true o si no fue visitado false*/

			/*vecinos de vértice inicial y aun no visitados*/
			for(i=1;i<=vn;i++)
			{	dis[i]=INT_MAX;	vecino[i]=false;		}
			/*vértice inicial = cero*/
			dis[vi]=0;
			
			/*mientras con x y vn no sean iguales*/
			for (x=1;x<=vn;x++) 
			{
				/*mínimo en las variables*/
		        float mdis=INT_MAX;
		        int mi=-1;
		        
		        /* distancia mínima de vértice*/
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
/*si vecino no ha sido visitado y el valor de la suma es menor hacer fórmula para agregar distancia a vértice y el valor no sea infinito y no sea no adyacente*/
		            if (!vecino[y] && matriz[mi][y] != INT_MAX && dis[mi] != INT_MAX && dis[mi] + matriz[mi][y] < dis[y] &&matriz[mi][y] != vma && dis[mi] != vma ) 
					{	dis[y] = dis[mi] + matriz[mi][y];	}          
		        }
		        
		    }
		    
		    /*salida de las distancias de cada vértice que da con el vértice inicial*/
		    printf("Distancia más corta del vértice inicial V%d:\n", vi);
		    for (i=1; i<=vn; i++) {	printf("V%d\t", i);	}
		    printf("\n");
		    for (i=1; i<=vn; i++) {	printf("%.2f\t",dis[i]);	}
		}
		else/* error el vértice inicial es menor a 1 y mayor al número de vértices decidido*/
		{
			printf("\nERROR VERTICE NO SE ENCUENTRA EN EL GRAFO, ESCOGER OTRO");
			rep=1;
		}
	}while(rep!=0);
}


main()
{	/*para imprimir con acento*/setlocale(LC_ALL, ""); 	grafo();	diska();	}

