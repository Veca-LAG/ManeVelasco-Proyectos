#include "USO.c"
void entrada() {
	int i;
	gotoxy(29, 1);	printf("%c",218);
	for (i = 0; i < 38; i++) {
        printf("%c", 196);
    }
    printf("%c",191);
    gotoxy(29, 3);	printf("%c",192);
    for (i = 0; i < 38; i++) {
        printf("%c", 196);
    }
    printf("%c",217);
    gotoxy(29, 2);	printf("%c M%ctodo de ordenamiento de la burbuja %c",179,130,179);
}
void rejilla(int tamano,int xx,int yy) {
    int i;
    
    gotoxy(xx,yy);printf(RESET_COLOR"%c", 218);
    for(i=0;i<tamano;i++){    gotoxy(1+xx+(i*5),yy);printf("%c%c%c%c%c", 196, 196, 196, 196, 194);	}
	gotoxy(xx+(i*5),yy);printf("%c", 191);
	
    for(i=0;i<=tamano;i++){
    	gotoxy(xx+(i*5),yy+1);printf("%c",179);
	}
	
	gotoxy(xx,13);printf("%c", 192);
	for(i=0;i<tamano;i++){    gotoxy(1+xx+(i*5),yy+2);printf("%c%c%c%c%c", 196, 196, 196, 196, 193);	}
	gotoxy(xx+(i*5),yy+2);printf("%c", 217,RESET_COLOR);
}
void bubbleSort(int arr[], int n,int xx,int yy);
void printArray(int arr[], int size);
int main(){
	srand(time(NULL));
	int tamano,i,num[20],xx,yy;
	tamano= sizeof(num) / sizeof(num[0]);
	
	cuadra();entrada();
	do{
		gotoxy(25,5);printf("Ingrese la cantidad de elementos (m%cximo 19): ",160);
	    scanf("%d", &tamano);
	    if(tamano>19){    gotoxy(12,5);printf("                                                       ");		}
   	}while(tamano>19);
    gotoxy(32,7);printf(VERDE_F"VERDE=comprobando "ROJO_F" ROJO=cambiando");
	xx=5;	yy=11;
    rejilla(tamano,xx,yy);
    while(i<tamano){
		num[i]=rand()%90+10;
		Sleep(100);
    	gotoxy(7+(i*5),12);printf("%d",num[i]);
    	i++;
	}
	xx=7;	yy=12;
	bubbleSort(num, tamano,xx,yy);
	 gotoxy(119, 29);
}
void bubbleSort(int num[], int tamano,int xx,int yy) {
    int i, j,temp;
    for (i = 0; i < tamano - 1; i++) {
        for (j = 0; j < tamano - i - 1; j++) {
        	sleep(1);
        	gotoxy(xx+(j*5),yy);printf(VERDE_T"%d",num[j]);
            gotoxy(xx+((1+j)*5),yy);printf(VERDE_T"%d",num[j+1]);
            sleep(1);
            if (num[j] > num[j + 1]) {
                /*animacion*/
                gotoxy(xx+(j*5),yy);printf(ROJO_T"%d",num[j]);/*rojo*/
                gotoxy(xx+((1+j)*5),yy);printf("%d",num[j+1]);/*rojo*/
                Sleep(100);
                
                gotoxy(xx+(j*5),yy);printf("  ");/*borra */
                gotoxy(xx+(j*5),yy-1);printf("%d",num[j]);/*1 arriba*/
                gotoxy(5+xx+(j*5),yy);printf("  ");/*borra */
                gotoxy(5+xx+(j*5),yy+1);printf("%d",num[j+1]);/*1 abajo*/
                Sleep(100);
                
                gotoxy(xx+(j*5),yy-1);printf(RESET_COLOR"%c%c",196,196);/*borra 1 arriba*/
                gotoxy(xx+(j*5),yy-2);printf(ROJO_T"%d",num[j]);/*2 arriba*/
                gotoxy(5+xx+(j*5),yy+1);printf(RESET_COLOR"%c%c",196,196);/*borra 1 abajo*/
                gotoxy(5+xx+(j*5),yy+2);printf(ROJO_T"%d",num[j+1]);/*2 abajo*/
                Sleep(100);
                
                gotoxy(xx+(j*5),yy-2);printf("  ");/*borra 1 arriba*/
                gotoxy(xx+(j*5),yy-3);printf("%d",num[j]);/*2 izquierda*/
                gotoxy(5+xx+(j*5),yy+2);printf("  ");
                gotoxy(5+xx+(j*5),yy+3);printf("%d",num[j+1]);/*1 derecha*/
                Sleep(100);
                
                gotoxy(xx+(j*5),yy-3);printf("  ");/*borra 1 arriba*/
                gotoxy(2+xx+(j*5),yy-3);printf("%d",num[j]);/*2 izquierda*/
                gotoxy(5+xx+(j*5),yy+3);printf("  ");
                gotoxy(3+xx+(j*5),yy+3);printf("%d",num[j+1]);/*1 derecha*/
                Sleep(100);
                
                gotoxy(2+xx+(j*5),yy-3);printf("  ");/*borra 1 izquierda*/
                gotoxy(5+xx+(j*5),yy-3);printf("%d",num[j]);/*2 izquierda*/
                gotoxy(3+xx+(j*5),yy+3);printf("  ");
                gotoxy(xx+(j*5),yy+3);printf("%d",num[j+1]);/*2 derecha*/
                Sleep(100);
                
                gotoxy(5+xx+(j*5),yy-3);printf("  ");/*borra 1 izquierda*/
                gotoxy(5+xx+(j*5),yy-2);printf("%d",num[j]);/*2 abajo*/
                gotoxy(xx+(j*5),yy+3);printf("  ");
                gotoxy(xx+(j*5),yy+2);printf("%d",num[j+1]);/*2 arriba*/
                Sleep(100);
                
                gotoxy(5+xx+(j*5),yy-2);printf("  ");/*borra 1 izquierda*/
                gotoxy(5+xx+(j*5),yy-1);printf("%d",num[j]);/*2 abajo*/
                gotoxy(xx+(j*5),yy+2);printf("  ");
                gotoxy(xx+(j*5),yy+1);printf("%d",num[j+1]);/*2 arriba*/
                Sleep(100);
                
                gotoxy(xx+((1+j)*5),yy-1);printf(RESET_COLOR"%c%c",196,196);/*borra 1 abajo*/
                gotoxy(xx+((1+j)*5),yy);printf("%d",num[j]);
                gotoxy(xx+(j*5),yy+1);printf("%c%c",196,196);
                gotoxy(xx+(j*5),yy);printf("%d",num[j+1]);
                
                /* Intercambiar arr[j] y arr[j+1]*/
                temp = num[j];
                num[j] = num[j + 1];
                num[j + 1] = temp;
            }
            gotoxy(xx+(j*5),yy);printf(RESET_COLOR"%d",num[j]);
            gotoxy(xx+((1+j)*5),yy);printf(RESET_COLOR"%d",num[j+1]);
            Sleep(500);
            
        }
    }
}
