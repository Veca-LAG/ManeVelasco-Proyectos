#include<stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <time.h>
#include <conio.h>
#define BLACK_T     "\x1b[30m"
#define ROJO_T     "\x1b[31m"
#define VERDE_T        "\x1b[32m"
#define AMARILLO_T        "\x1b[33m"
#define AZUL_T   "\x1b[34m"
#define MAGENTA_T  "\x1b[35m"
#define CYAN_T  "\x1b[36m"
#define BLANCO_T   "\x1b[37m"
#define NARANJA_T  "\x1B[38;2;255;128;0m"
#define CIEL_T   "\x1B[38;2;53;149;240m"
#define LIME_T  "\x1B[38;2;17;245;120m"
#define GRIS_T "\x1B[38;2;176;174;174m"
#define ROSA_T "\x1B[38;;2;255;151;203m"

//FONDO
#define BLACK_F   "\x1b[40m"
#define ROJO_F   "\x1b[41m"
#define VERDE_F  "\x1b[42m"
#define AMARILLO_F  "\x1b[43m"
#define AZUL_F   "\x1b[44m"
#define MAGENTA_F  "\x1b[45m"
#define CYAN_F  "\x1b[46m"
#define BLANCO_F   "\x1b[47m"
#define NARANJA_F  "\x1B[48;2;255;128;0m"
#define CIEL_F   "\x1B[48;2;53;149;240m"
#define LIME_F  "\x1B[48;2;17;245;120m"
#define GRIS_F "\x1B[48;2;176;174;174m"
#define ROSA_F "\x1B[48;;2;255;151;203m"

//AJUSTE
#define RESET_COLOR    "\x1b[0m"
#define bright  "\x1b[1m"
#define dim  "\x1b[2m"
#define cursive  "\x1b[3m"
#define underscore  "\x1b[4m"
#define blink  "\x1b[5m"
#define  reverse  "\x1b[7m"
#define hidden  "\x1b[8m"
void gotoxy(int x, int y) {
    HANDLE hcon;
    hcon = GetStdHandle(STD_OUTPUT_HANDLE);
    COORD dwPos;
    dwPos.X = x;
    dwPos.Y = y;
    SetConsoleCursorPosition(hcon, dwPos);
}
void diario(char *titulo);
void otradiario(char *titulo);
void cuenta(char concepto[][100], char codigo[][7], int numc,char tipo[][100]);
int contarDigitos(char concepto[][100],int numt);
/*void compararMatriz(char codigo[][7], int numc, int numt,int otront) {
	int i;
	
	
}*/
main(){
	int salir=1,rep=1,r,esp=0,nesp=0,ch,numt,numc,DH[99][99],dia,mes,ano;
	int otronc,otront,ont,contadorc=0,contadorf=0,tabc=0,tabf=0;
	float parcial,Tp[99][99];
	char titulo[100],cconcepto[99][100],tipo[99][100],concepto[100],codigo[99][7],codigod[99][7],cconceptod[99][100],fecha[10];
	int i,j,k,h,l,encontrado=0,resultado=0;
	gotoxy(50,14);printf("nombre del titulo ");getch();
	/*scanf("%s",&titulo);
	system("cls");*/
	strcpy(titulo, "oi");
	SetConsoleTitle(titulo);
	numc=0;numt=0;
	otronc=0;otront=0;
	ont=0;
	system("cls");
		
	time_t tiempo_actual;
	time(&tiempo_actual);
	struct tm *info_tiempo = localtime(&tiempo_actual);
	dia=info_tiempo->tm_mday;
	mes=info_tiempo->tm_mon+1;
	ano=info_tiempo->tm_year+1900;
	/*gotoxy(120,1); printf(bright "  %02d/%02d/%02d  "RESET_COLOR, dia, mes,ano);*/
	diario(titulo);
	//DIARIO
	do{
		gotoxy(60,0);printf("%d",esp);
	    if((2*esp)+5>=37){
	    	otradiario(titulo);
	    	ch=getch();
	    	if (ch == '\t') {
		        break; // Si se presiona Tab, sal del bucle
		    }else{
		    	system("cls");
		    	diario(titulo);
		    	esp=0;
			}
		    
		}
		//fecha
		gotoxy(0,5+(esp*2));printf(bright "%02d/%02d/%02d"RESET_COLOR, dia, mes,ano);
		
		//numero de cuenta
		gotoxy(11,5+(esp*2));printf("%d",numc+1);
		//codigo
		//n-n-n
		do{
			gotoxy(14,5+(esp*2));scanf("%s",&codigo[numc]);
			//cconcepto=cuenta(codigo);
			cuenta(cconcepto, codigo, numc,tipo);
			
				if (strcmp(cconcepto[numc], "nada") == 0) {
				    rep=0;
				    gotoxy(14,5+(esp*2));printf("      ");
				}else{
					rep=1;
				}
		}while(rep==0);
		rep=0;
		
		for (i = 0; i < numc; i++) {
	        encontrado = 0;
	
	        
	            if (strcmp(codigod[i], codigo[numc]) == 0) {
	                encontrado = 1; // Elemento encontrado en codigod
	                numt=numc;
	                break;
	            }
	        
	
	        if (!encontrado) {
	            strcpy(codigod[numt], codigo[i]);
	            
	            otront++;
	            numt=otront;
	        }
	    }
		
		/*for(i=0;i<=numc;i++){
		    if (strcmp(codigo[numc], codigo[i]) == 0) {
		        // Si las cadenas en las filas son iguales
		        numt=numc;
		    }
		    if(numt!=numc){
				otront++;
				numt=otront;
			}
		}*/
		
		//compararMatriz(codigo, numc, numt,otront);
		
		/*for (i = 0; i < numc; i++) {
	        encontrado = 0;
	        for (j = 0; j < numt; j++) {
	            if (strcmp(codigo[i], codigod[j]) == 0) {
	                encontrado = 1; // Indicar que se encontró un duplicado
	                break;
	            }
	        }
	        if (!encontrado) {
	            strcpy(codigod[numt], codigo[i]); // Copiar elemento único
	            numt++; // Incrementar el contador de elementos únicos
	             // Actualizar ont al nuevo numt
	        }
	    }*/
		/*resultado = compararMatriz(codigo, numc, otronc);
		if (resultado == 0) {
		    // Si las cadenas en las filas son iguales
		    numt = otronc;
		} else {
		    // Si las cadenas en las filas son diferentes
		    numt=ont;
		    numt++;
		    ont=numt;
		}*/
		
		gotoxy(100,5+(esp*2));printf("%d",numt);
		//asignacion de Activos, Pasivos y Capital social
		gotoxy(21,5+(esp*2));printf("%s",cconcepto[numc]);
		
		//CONCEPTO
		
		do{
			esp++;
			nesp++;
			fflush(stdin);
			gotoxy(21,5+(esp*2));scanf("%s",&concepto);
			gotoxy(90,5+(esp*2));printf("$");scanf("%f",&parcial);
			gotoxy(90,5+(esp*2));printf("$%.2f",parcial);
			Tp[numc][numt]=Tp[numc][numt]+parcial;
			fflush(stdin);
			gotoxy(90,5+(esp*2));ch=getch();
			if (ch == '\t') {
	            break; // Si se presiona Tab, sal del bucle
	        }
	        gotoxy(60,0);printf("%d",esp);
	        if((2*esp)+5>=37){
		    	otradiario(titulo);
		    	ch=getch();
		    	if (ch == '\t') {
			        break; // Si se presiona Tab, sal del bucle
			    }else{
			    	system("cls");
			    	diario(titulo);
			    	esp=0;
			    	nesp=0;
			    	gotoxy(100,5+(esp*2));printf("%d",numt);
			    	gotoxy(21,5+(esp*2));printf("%s",cconcepto[numc]);
			    	gotoxy(90,5+(esp*2));printf("%d",DH[numc][numt]);
				}
			}
		}while(1);
		//si es DEBER y HABER
		fflush(stdin);
		gotoxy(90,5+((esp-nesp)*2));scanf("%d",&DH[numc][numt]);
		if(DH[numc][numt]==1){
			gotoxy(113,5+((esp-nesp)*2));printf("$%.2f",Tp[numc][numt]);
		}else{
		if(DH[numc][numt]==2){
			gotoxy(136,5+((esp-nesp)*2));printf("$%.2f",Tp[numc][numt]);
			}
		}
		nesp=0;
		//getch();
		fflush(stdin);
		gotoxy(90,5+((esp-nesp)*2));ch=getch();
		if (ch == '\t') {
	        break; // Si se presiona Tab, sal del bucle
	    }
	    numc++;
	    esp++;
	    
	}while(1);
	esp=0;

	//compararMatriz(codigo, numc, otronc);
	//mayor
	system("cls");
	gotoxy(5,1);printf("HOJA DE MAYOR");
	gotoxy(54,1);printf(dim BLACK_T LIME_F"%s",titulo ,RESET_COLOR);
	gotoxy(100,1);printf(bright "%02d/%02d/%02d\n\n"RESET_COLOR, dia, mes,ano);
	
	for(i=0;i<numt;i++){
			cuenta(cconceptod, codigod, i,tipo);
		contadorc=contarDigitos(cconceptod,i);
		tabc=(70-contadorc)/2;
		for(j=0;j<43;j++){
			printf(" ");
		}
		for(j=0;j<tabc;j++){
			printf(" ");
		}
		printf("%s\n",cconceptod[i]);
		
		for(j=0;j<43;j++){
			printf(" ");
		}
		for(j=0;j<70;j++){
			printf("-");
		}
		printf("\n");
		
		for(j=0;j<43;j++){
			printf(" ");
		}
		for(j=0;j<15;j++){
			printf(" ");
		}
		printf("DEBE");
		for(j=0;j<15;j++){
			printf(" ");
		}
		printf("|");
		for(j=0;j<15;j++){
			printf(" ");
		}
		printf("HABER\n");
		
		for(j=0;j<43;j++){
			printf(" ");
		}
		for(j=0;j<70;j++){
			printf("-");
		}
		
		for(j=0;j<numc;j++){
			//if((strcmp(cconceptod[i], cconcepto[j]) == 0)) {
				if (DH[j][i]==1){
					printf("\n");
					for(k=0;k<43;k++){
						printf(" ");
					}
					printf("%d.) $%.2f",j+1,Tp[j][i]);
					
				}
			//}
		}
		printf("\n\n\n");
	}
	fflush(stdin);
	getch();
	
	
	
	gotoxy(0,29);
}

int contarDigitos(char concepto[][100],int numt) {
	int m,contadorc=0;
    for (m = 0; concepto[numt][m] != '\0'; m++) {
            contadorc++;
    }
    return contadorc;
}
void diario(char *titulo){
	int i,j;
	time_t tiempo_actual;
	time(&tiempo_actual);
	struct tm *info_tiempo = localtime(&tiempo_actual);
	gotoxy(5,1);printf("HOJA DE DIARIO");
		gotoxy(54,1);printf(dim BLACK_T LIME_F"%s",titulo ,RESET_COLOR);
		gotoxy(120,1); printf(ROJO_F bright "  %02d/%02d/%02d  "RESET_COLOR, info_tiempo->tm_mday,info_tiempo->tm_mon+1,info_tiempo->tm_year+1900);
		gotoxy(0,3);printf("FECHA");
		gotoxy(11,3);printf("NC");
		gotoxy(14,3);printf("CUENTA");
		gotoxy(21,3);printf("CONCEPTO");
		gotoxy(90,3);printf("PARCIAL");
		gotoxy(113,3);printf("[1]DEBE");
		gotoxy(136,3);printf("[2]HABER");
	for(i=0;i<18;i++){
		gotoxy(10,3+(i*2));printf("|");
		gotoxy(13,3+(i*2));printf("|");
		gotoxy(20,3+(i*2));printf("|");
		gotoxy(89,3+(i*2));printf("|");
		gotoxy(112,3+(i*2));printf("|");
		gotoxy(135,3+(i*2));printf("|");
		for(j=0;j<156;j++){
			gotoxy(0+j,4+(i*2));printf("-");
		}
	}
	
}
void otradiario(char *titulo){
	int i,j;
	time_t tiempo_actual;
	time(&tiempo_actual);
	struct tm *info_tiempo = localtime(&tiempo_actual);
	gotoxy(5,1);printf("HOJA DE DIARIO");
		gotoxy(54,1);printf(dim BLACK_T LIME_F"%s",titulo ,RESET_COLOR);
		gotoxy(120,1); printf(ROJO_F bright "  %02d/%02d/%02d  "RESET_COLOR, info_tiempo->tm_mday,info_tiempo->tm_mon+1,info_tiempo->tm_year+1900);
		gotoxy(0,3);printf("FECHA");
		gotoxy(11,3);printf("NC");
		gotoxy(14,3);printf("CUENTA");
		gotoxy(21,3);printf("CONCEPTO");
		gotoxy(90,3);printf("PARCIAL");
		gotoxy(113,3);printf("[1]DEBE");
		gotoxy(136,3);printf("[2]HABER");
	for(i=0;i<18;i++){
		gotoxy(10,3+(i*2));printf("|");
		gotoxy(13,3+(i*2));printf("|");
		gotoxy(20,3+(i*2));printf("|");
		gotoxy(89,3+(i*2));printf("|");
		gotoxy(112,3+(i*2));printf("|");
		gotoxy(135,3+(i*2));printf("|");
		for(j=0;j<156;j++){
			gotoxy(0+j,4+(i*2));printf("-");
		}
	}
	printf("\nOTRA HOJA[CUALQUIER TECLA]	 NO[TAB]");
	
}

void cuenta(char concepto[][100], char codigo[][7], int numc,char tipo[][100]){
	strcpy(concepto[numc],"nada");
	//activo
		//circulante
	if (strcmp(codigo[numc], "1-1-1") == 0) {
	    strcpy(concepto[numc],"Caja");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-2") == 0) {
	    strcpy(concepto[numc],"Banco");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-3") == 0) {
	    strcpy(concepto[numc],"Inverciones temporales");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-4") == 0) {
	    strcpy(concepto[numc],"Mercancias");
	    strcpy(tipo[numc],"AP");
	}
	if (strcmp(codigo[numc], "1-1-5") == 0) {
	    strcpy(concepto[numc],"Clientes");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-6") == 0) {
	    strcpy(concepto[numc],"Deudores diversos");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-7") == 0) {
	    strcpy(concepto[numc],"Funcionarios y empleados");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-8") == 0) {
	    strcpy(concepto[numc],"Documentos por cobrar a corto plazo");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-9") == 0) {
	    strcpy(concepto[numc],"IVA Acreditable");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-10") == 0) {
	    strcpy(concepto[numc],"IVA Pendiente de Acreditar");
	    strcpy(tipo[numc],"AC");
	}
	if (strcmp(codigo[numc], "1-1-11") == 0) {
	    strcpy(concepto[numc],"Obras en proceso");
	    strcpy(tipo[numc],"AC");
	}
		//no circulante
	if (strcmp(codigo[numc], "1-2-1") == 0) {
	    strcpy(concepto[numc],"Terrenos");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-2") == 0) {
	    strcpy(concepto[numc],"Revaluacion de terrenos");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-3") == 0) {
	    strcpy(concepto[numc],"Edificios o construcciones");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-4") == 0) {
	    strcpy(concepto[numc],"(-)Depreciacion de Edificios");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-5") == 0) {
	    strcpy(concepto[numc],"Maquina y equipo");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-6") == 0) {
	    strcpy(concepto[numc],"(-)Depreciacion de maquina y equipo");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-7") == 0) {
	    strcpy(concepto[numc],"Mobiliario y equipo de oficina");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-8") == 0) {
	    strcpy(concepto[numc],"(-)Depreciacion de mobiliario y equipo de oficina");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-9") == 0) {
	    strcpy(concepto[numc],"Equipo de computo");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-10") == 0) {
	    strcpy(concepto[numc],"(-)Depreciacion de equipo de computo");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-11") == 0) {
	    strcpy(concepto[numc],"Equipo Periferico");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-12") == 0) {
	    strcpy(concepto[numc],"(-)Depreciacion de equipo periferico");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-13") == 0) {
	    strcpy(concepto[numc],"Equipo de reparto o entrega");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-14") == 0) {
	    strcpy(concepto[numc],"(-)Depreciaion de equipo de reparto o entrega");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-15") == 0) {
	    strcpy(concepto[numc],"Inversiones temporales de largo plazo");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-16") == 0) {
	    strcpy(concepto[numc],"Depositos en garantia");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-17") == 0) {
	    strcpy(concepto[numc],"Patentes");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-18") == 0) {
	    strcpy(concepto[numc],"Marcas");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-19") == 0) {
	    strcpy(concepto[numc],"Franquicias");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-20") == 0) {
	    strcpy(concepto[numc],"Acciones y valores");
	    strcpy(tipo[numc],"ANC");
	}
	if (strcmp(codigo[numc], "1-2-21") == 0) {
	    strcpy(concepto[numc],"Obligaciones o bonos");
	    strcpy(tipo[numc],"ANC");
	}
		//diferidos
	if (strcmp(codigo[numc], "1-3-1") == 0) {
	    strcpy(concepto[numc],"Gastos de constitucion");
	    strcpy(tipo[numc],"AD");
	}
	if (strcmp(codigo[numc], "1-3-2") == 0) {
	    strcpy(concepto[numc],"Gastos de instalacion");
	    strcpy(tipo[numc],"AD");
	}
	if (strcmp(codigo[numc], "1-3-3") == 0) {
	    strcpy(concepto[numc],"Papeleria y utiles en economia de escala");
	    strcpy(tipo[numc],"AD");
	}
	if (strcmp(codigo[numc], "1-3-4") == 0) {
	    strcpy(concepto[numc],"Promocion y publicidad");
	    strcpy(tipo[numc],"AD");
	}
	if (strcmp(codigo[numc], "1-3-5") == 0) {
	    strcpy(concepto[numc],"Primos de seguros pagados por anticipado");
	    strcpy(tipo[numc],"AD");
	}
	if (strcmp(codigo[numc], "1-3-6") == 0) {
	    strcpy(concepto[numc],"Anticipos a Proovedores");
	    strcpy(tipo[numc],"AD");
	}
	if (strcmp(codigo[numc], "1-3-7") == 0) {
	    strcpy(concepto[numc],"Rentas pagadas por anticipado");
	    strcpy(tipo[numc],"AD");
	}
	if (strcmp(codigo[numc], "1-3-8") == 0) {
	    strcpy(concepto[numc],"Interes pagado por anticipado");
	    strcpy(tipo[numc],"AD");
	}
	
	//pasivo
		//circulante
	if (strcmp(codigo[numc], "2-1-1") == 0) {
	    strcpy(concepto[numc],"Proveedores");
	    strcpy(tipo[numc],"PC");
	}
	if (strcmp(codigo[numc], "2-1-2") == 0) {
	    strcpy(concepto[numc],"Acreedores Diversos");
	    strcpy(tipo[numc],"PC");
	}
	if (strcmp(codigo[numc], "2-1-3") == 0) {
	    strcpy(concepto[numc],"Documentos por pagar a corto plazo");
	    strcpy(tipo[numc],"PC");
	}
	if (strcmp(codigo[numc], "2-1-4") == 0) {
	    strcpy(concepto[numc],"IVA trasladado");
	    strcpy(tipo[numc],"PC");
	}
	if (strcmp(codigo[numc], "2-1-5") == 0) {
	    strcpy(concepto[numc],"IVA pr trasladar");
	    strcpy(tipo[numc],"PC");
	}
	if (strcmp(codigo[numc], "2-1-6") == 0) {
	    strcpy(concepto[numc],"Impuesto pendientes de pago");
	    strcpy(tipo[numc],"PC");
	}
	if (strcmp(codigo[numc], "2-1-7") == 0) {
	    strcpy(concepto[numc],"Retenciones a tercero(impuestos retenenidos)");
	    strcpy(tipo[numc],"PC");
	}
	if (strcmp(codigo[numc], "2-1-8") == 0) {
	    strcpy(concepto[numc],"Anticipo de clientes");
	    strcpy(tipo[numc],"PC");
	}
		//no circulante
	if (strcmp(codigo[numc], "2-2-1") == 0) {
	    strcpy(concepto[numc],"Provisiones a largo plazo");
	    strcpy(tipo[numc],"PNC");
	}
	if (strcmp(codigo[numc], "2-2-2") == 0) {
	    strcpy(concepto[numc],"Dedas a largo plazo");
	    strcpy(tipo[numc],"PNC");
	}
	if (strcmp(codigo[numc], "2-2-3") == 0) {
	    strcpy(concepto[numc], "Deudas con entidades del grupo empresarial y asociados a largo plazo");
	    strcpy(tipo[numc],"PNC");
	}
	if (strcmp(codigo[numc], "2-2-4") == 0) {
	    strcpy(concepto[numc],"Periodificaciones a largo plazo");
	    strcpy(tipo[numc],"PNC");
	}
	
		//diferente
	if (strcmp(codigo[numc], "2-3-1") == 0) {
	    strcpy(concepto[numc],"Ingresos por cobrar");
	    strcpy(tipo[numc],"PD");
	}
	if (strcmp(codigo[numc], "2-3-2") == 0) {
	    strcpy(concepto[numc],"Rentas por cobrar");
	    strcpy(tipo[numc],"PD");
	}
	if (strcmp(codigo[numc], "2-3-3") == 0) {
	    strcpy(concepto[numc],"Interes acumulados");
	    strcpy(tipo[numc],"PD");
	}
	if (strcmp(codigo[numc], "2-3-4") == 0) {
	    strcpy(concepto[numc],"Seguros pagados por adelantado");
	    strcpy(tipo[numc],"PD");
	}
	
	//capital social	
	if (strcmp(codigo[numc], "3-0-0") == 0) {
	    // El código tiene la estructura "3-0-0"
	    strcpy(concepto[numc],"Capital social");
	    strcpy(tipo[numc],"PD");
	}
}

