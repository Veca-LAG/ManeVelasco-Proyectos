#include<stdio.h>
#include <string.h>
#include<windows.h>
#include<time.h>
#include<graphics.h>
void gotoxy(int x, int y) {
    HANDLE hcon;
    hcon = GetStdHandle(STD_OUTPUT_HANDLE);
    COORD dwPos;
    dwPos.X = x;
    dwPos.Y = y;
    SetConsoleCursorPosition(hcon, dwPos);
}
void trancision();
void vaciarCadena(char cadena[]) {
    cadena[0] = '\0';
}
void borrar(char A[100],char B[100],int maxa,int maxb,int maxc){
	system("cls");
	gotoxy(0,0);printf("%s + %s",A, B);
	gotoxy(0,1);printf("CINT1");
	gotoxy(maxa+5,1);printf("CINT2");
	gotoxy(maxa+maxb+10,1);printf("CINT3");
	gotoxy(maxa+maxc+maxb+15,1);printf("ESTADO");
}
void menu(){
	int i,y;
	gotoxy(45,5);
	printf("%c",201);
	for(i=0;i<=18;i++){
		printf("%c",205);
	}
	printf("%c",187);
	gotoxy(45,6);printf("%c MAQUINA DE TURING %c",186,186);
	gotoxy(45,7);
	printf("%c",200);
	for(i=0;i<=18;i++){
		printf("%c",205);
	}
	printf("%c",188);
	gotoxy(55,10);printf("MENU");
	gotoxy(45,12);printf("1) Informacion");
	gotoxy(45,13);printf("2) Cadena");
	gotoxy(45,15);printf("opcion.... ");
}
void bienvenida(){
	int i,y;
	gotoxy(45,9);
	printf("%c",201);
	for(i=0;i<=18;i++){
		printf("%c",205);
	}
	printf("%c",187);
	gotoxy(45,10);printf("%c MAQUINA DE TURING %c",186,186);
	gotoxy(45,11);
	printf("%c",200);
	for(i=0;i<=18;i++){
		printf("%c",205);
	}
	printf("%c",188);
	
	gotoxy(70,20);printf("PRESIONA ENTER PARA SEGUIR...");
}
void infor();
int main(){
	char A[100],B[100],C[100],estado[10];
	int i,j,k,l,maxa,maxb,maxc,x,y,op;
	int longi,seguir,F1,F2;
	bienvenida();
	getchar();
	do{
		system("cls");
		menu();
		scanf("%d",&op);
		system("cls");
		switch(op){
			case 1:
				infor();
				fflush(stdin);
				gotoxy(70,4);printf("PRESIONA UNA TECLA PARA SEGUIR...");
				getchar();
				trancision();
				getch();
			closegraph();
				getchar(); 
				system("cls");
				break;
			case 2:
				k=0;j=0;i=0;F1=0;F2=0;seguir=1;
				fflush(stdin);
				gotoxy(0,0);gets(A);
				maxa= strlen(A);
				gotoxy(maxa+1,0);printf("+ ");
				fflush(stdin);
				gets(B);	
				maxb= strlen(B);
				
				vaciarCadena(C);
				gotoxy(0,1);printf("CINT1");
				gotoxy(maxa+5,1);printf("CINT2");
				gotoxy(maxa+maxb+10,1);printf("CINT3");
				if(maxa>=maxb){
					maxc=maxa+1;
				}else{
					maxc=maxb+1;
				}
				gotoxy(maxa+maxc+maxb+15,1);printf("ESTADO");
				//INICIO QO
				seguir=1;
				x=0; y=2;
				strcpy(estado, "B0");
				gotoxy(x,y);printf("%c%s%c",207,A,207);
				gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
				gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
				gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
				gotoxy(i+x+1,y+1);printf("%c",248);
				gotoxy(j+x+maxa+6,y+1);printf("%c",248);
				gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
				y=y+2;
				if(y>=26){
					gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
					getchar();
					borrar(A,B,maxa,maxb,maxc);
					y=2;
				}
				//b1
				C[k]='X';
				k++;
				C[k]='\0';
				strcpy(estado, "B1");
				gotoxy(x,y);printf("%c%s%c",207,A,207);
				gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
				gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
				gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
				gotoxy(i+x+1,y+1);printf("%c",248);
				gotoxy(j+x+maxa+6,y+1);printf("%c",248);
				gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
				y=y+2;
				if(y>=26){
					gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
					getchar();
					borrar(A,B,maxa,maxb,maxc);
					y=2;
				}
				getchar();
				//repetir
				do{
					if(A[i]=='1'&&B[j]=='1'){
						i++; j++;
						C[k]='X';
						k++;
						C[k]='\0';
					}else{
						if(A[i]=='1'&&B[j]=='0'){
							i++; j++;
							C[k]='X';
							k++;
							C[k]='\0';
						}else{
							if(A[i]=='0'&&B[j]=='1'){
								i++; j++;
								C[k]='X';
								k++;
								C[k]='\0';
							}else{
								if(A[i]=='0'&&B[j]=='0'){
									i++; j++;
									C[k]='X';
									k++;
									C[k]='\0';
								}else{
									if(A[i]=='1'&&B[j]=='\0'){
										i++;
										C[k]='X';
										k++;
										C[k]='\0';
									}else{
										if(A[i]=='\0'&&B[j]=='1'){
											j++;
											C[k]='X';
											k++;
											C[k]='\0';
										}else{
											if(A[i]=='0'&&B[j]=='\0'){
												i++;
												C[k]='X';
												k++;
												C[k]='\0';
											}else{
												if(A[i]=='\0'&&B[j]=='0'){
													j++;
													C[k]='X';
													k++;
													C[k]='\0';
												}else{
													if(A[i]=='\0'&&B[j]=='\0'){
														break;
													}else{
														seguir=0;
														break;	
													}	
												}	
											}	
										}	
									}
								}	
							}	
						}
					}
					strcpy(estado, "B1");
					gotoxy(x,y);printf("%c%s%c",207,A,207);
					gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
					gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
					gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
					gotoxy(i+x+1,y+1);printf("%c",248);
					gotoxy(j+x+maxa+6,y+1);printf("%c",248);
					gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
					y=y+2;
					if(y>=26){
						gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
						getchar();
						borrar(A,B,maxa,maxb,maxc);
						y=2;
					}
					getchar();
				}while(1);
				if(seguir!=1){
					gotoxy(10,29);printf("CADENA ROTA ");
				}else{
					gotoxy(10,29);printf("CADENA ");
					i--;j--;k--;
				strcpy(estado, "q0");
				gotoxy(x,y);printf("%c%s%c",207,A,207);
				gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
				gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
				gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
				gotoxy(i+x+1,y+1);printf("%c",248);
				gotoxy(j+x+maxa+6,y+1);printf("%c",248);
				gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
				y=y+2;
				if(y>=24){
					gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
					getchar();
					borrar(A,B,maxa,maxb,maxc);
					y=2;
				}
					do{
						//1+1 
						if(A[i]=='1'&&B[j]=='1'){
							//1+1 +b
							if(C[k]=='X'){
								C[k]='0';
								k--;
								strcpy(estado, "q1");
								gotoxy(x,y);printf("%c%s%c",207,A,207);
								gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
								gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
								gotoxy(i+x+1,y+1);printf("%c",248);
								gotoxy(j+x+maxa+6,y+1);printf("%c",248);
								gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
								y=y+2;
								if(y>=24){
									gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
									getchar();
									borrar(A,B,maxa,maxb,maxc);
									y=2;
								}
								i--;
								j--;
								C[k]='1';
								strcpy(estado, "q0");
							}else{
								//1+1 +1
								if(C[k]=='1'){
									C[k]='1';
									k--;
									strcpy(estado, "q1");
									gotoxy(x,y);printf("%c%s%c",207,A,207);
									gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
									gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
									gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
									gotoxy(i+x+1,y+1);printf("%c",248);
									gotoxy(j+x+maxa+6,y+1);printf("%c",248);
									gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
									y=y+2;
									if(y>=24){
										gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
										getchar();
										borrar(A,B,maxa,maxb,maxc);
										y=2;
									}
									i--;
									j--;
									C[k]='1';
									strcpy(estado, "q0");
								}
							}	
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
							gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
						}
						//1+0 
						if(A[i]=='1'&&B[j]=='0'){
							//1+0 +b
							if(C[k]=='X'){
								C[k]='1';
								k--;
								i--;
								j--;
								strcpy(estado, "q0");
							}else{
								//1+0 +1
								if(C[k]=='1'){
									C[k]='0';
									k--;
									strcpy(estado, "q1");
									gotoxy(x,y);printf("%c%s%c",207,A,207);
									gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
									gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
									gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
									gotoxy(i+x+1,y+1);printf("%c",248);
									gotoxy(j+x+maxa+6,y+1);printf("%c",248);
									gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
									y=y+2;
									if(y>=24){
										gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
										getchar();
										borrar(A,B,maxa,maxb,maxc);
										y=2;
									}
									i--;
									j--;
									C[k]='1';
									strcpy(estado, "q0");
								}
							}	
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
						}
						//0+1 
						if(A[i]=='0'&&B[j]=='1'){
							//0+1 +b
							if(C[k]=='X'){
								C[k]='1';
								k--;
								i--;
								j--;
								strcpy(estado, "q0");
							}else{
								//0+1 +1
								if(C[k]=='1'){
									C[k]='0';
									k--;
									strcpy(estado, "q1");
									gotoxy(x,y);printf("%c%s%c",207,A,207);
									gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
									gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
									gotoxy(i+x+1,y+1);printf("%c",248);
									gotoxy(j+x+maxa+6,y+1);printf("%c",248);
									gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
									y=y+2;
									if(y>=24){
										gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
										getchar();
										borrar(A,B,maxa,maxb,maxc);
										y=2;
									}
									i--;
									j--;
									C[k]='1';
									strcpy(estado, "q0");
								}
							}	
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
						}
						//0+0 
						if(A[i]=='0'&&B[j]=='0'){
							//0+0 +b
							if(C[k]=='X'){
								C[k]='0';
								k--;
								i--;
								j--;
								strcpy(estado, "q0");
							}else{
								//0+0 +1
								if(C[k]=='1'){
									C[k]='1';
									k--;
									strcpy(estado, "q1");
									gotoxy(x,y);printf("%c%s%c",207,A,207);
									gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
									gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
									gotoxy(i+x+1,y+1);printf("%c",248);
									gotoxy(j+x+maxa+6,y+1);printf("%c",248);
									gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
									y=y+2;
									if(y>=24){
										gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
										getchar();
										borrar(A,B,maxa,maxb,maxc);
										y=2;
									}
									i--;
									j--;
									strcpy(estado, "q0");
								}
							}	
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
						}
						//1+b 
						if(A[i]=='1'&&j<=-1){
							//1+b +b
							if(C[k]=='X'){
								C[k]='1';
								k--;
								i--;
								strcpy(estado, "q0");
							}else{
								//1+b +1
								if(C[k]=='1'){
									C[k]='0';
									k--;
									strcpy(estado, "q1");
									gotoxy(x,y);printf("%c%s%c",207,A,207);
									gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
									gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
									gotoxy(i+x+1,y+1);printf("%c",248);
									gotoxy(j+x+maxa+6,y+1);printf("%c",248);
									gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
									y=y+2;
									if(y>=24){
										gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
										getchar();
										borrar(A,B,maxa,maxb,maxc);
										y=2;
									}
									i--;
									C[k]='1';
									strcpy(estado, "q0");
								}
							}	
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
						}
						//b+1 
						if(B[j]=='1'&&i<=-1){
							//b+1 +b
							if(C[k]=='X'){
								C[k]='1';
								k--;
								j--;
								strcpy(estado, "q0");
							}else{
								//b+1 +1
								if(C[k]=='1'){
									C[k]='0';
									k--;
									strcpy(estado, "q1");
									gotoxy(x,y);printf("%c%s%c",207,A,207);
									gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
									gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
									gotoxy(i+x+1,y+1);printf("%c",248);
									gotoxy(j+x+maxa+6,y+1);printf("%c",248);
									gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
									y=y+2;
									if(y>=24){
										gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
										getchar();
										borrar(A,B,maxa,maxb,maxc);
										y=2;
									}
									j--;
									C[k]='1';
									strcpy(estado, "q0");
									
								}
							}	
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
						}
						//0+b 
						if(A[i]=='0'&&j<=-1){
							//0+b +b
							if(C[k]=='X'){
								C[k]='0';
								k--;
								i--;
								strcpy(estado, "q0");
							}else{
								//0+b +1
								if(C[k]=='1'){
									C[k]='1';
									k--;
									i--;
									strcpy(estado, "q0");
								}
							}	
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
						}
						//b+0 
						if(B[j]=='0'&&i<=-1){
							//b+0 +b
							if(C[k]=='X'){
								C[k]='0';
								k--;
								j--;
								strcpy(estado, "q0");
							}else{
								//b+0 +1
								if(C[k]=='1'){
									C[k]='1';
									k--;
									j--;
									strcpy(estado, "q0");
								}
							}	
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
						}
						//b+b 
						if(j<=-1&&i<=-1){
							//b+b +b
							if(C[k]=='X'){
								C[k]=207;
							}else{
								//b+b +1
								if(C[k]=='1'){
									C[k]='1';
									k--;
									
								}
							}	
							strcpy(estado, "q2");
							gotoxy(x,y);printf("%c%s%c",207,A,207);
							gotoxy(x+5+maxa,y);printf("%c%s%c",207,B,207);
							gotoxy(x+10+maxa+maxb,y);printf("%c%s%c",207,C,207);
								gotoxy(x+16+maxa+maxb+maxc,y);printf("%s",estado);
							gotoxy(i+x+1,y+1);printf("%c",248);
							gotoxy(j+x+maxa+6,y+1);printf("%c",248);
							gotoxy(k+x+maxa+maxb+11,y+1);printf("%c",248);
							y=y+2;
							if(y>=24){
								gotoxy(70,29);printf("PRESIONA UNA TECLA PARA SEGUIR...");
								getchar();
								borrar(A,B,maxa,maxb,maxc);
								y=2;
							}
							break;
						}
					}while(1);
				}
				if(C[k]=207){
					C[k]=32;
				}
				gotoxy(maxb+4+maxa,0);
				printf("= %s",C);
				getchar();
				break;
		}
	}while(1);
	
	gotoxy(0,28);
}


void infor(){
	int i,y;
	system("cls");
	gotoxy(45,1);
	printf("%c",201);
	for(i=0;i<=18;i++){
		printf("%c",205);
	}
	printf("%c",187);
	gotoxy(45,2);printf("%c MAQUINA DE TURING %c",186,186);
	gotoxy(45,3);
	printf("%c",200);
	for(i=0;i<=18;i++){
		printf("%c",205);
	}
	printf("%c",188);
	gotoxy(10,4);printf("A={0,1}");
	gotoxy(10,5);printf("Q={B0,B1,qo,q1,q2");
	gotoxy(10,6);printf("L={%c,0,1}",207);
	gotoxy(10,7);printf("I={B}");
	gotoxy(10,8);printf("F={q2}");
	gotoxy(20,9);printf("					TRANCICIONES\n\n");
	//B0
	printf("	%c(B0,(1,1,%c)->(B1,(1,1,X),(S,S,R))			%c(B0,(0,0,%c)->(B1,(0,0,X),(S,S,R))\n",208,207,208,207);
	printf("	%c(B0,(1,0,%c)->(B1,(1,0,X),(S,S,R))			%c(B0,(0,1,%c)->(B1,(0,1,X),(S,S,R))\n",208,207,208,207);
	printf("	%c(B0,(1,%c,%c)->(B1,(1,%c,X),(S,S,R))			%c(B0,(0,%c,%c)->(B1,(0,%c,X),(S,S,R))\n",208,207,207,208,207,207);
	printf("	%c(B0,(%c,1,%c)->(B1,(%c,1,X),(S,S,R))			%c(B0,(%c,0,%c)->(B1,(%c,0,X),(S,S,R))\n",208,207,207,208,207,207);
	//B1
	printf("	%c(B1,(1,1,%c)->(B1,(1,1,X),(R,R,R))			%c(B1,(0,0,%c)->(B1,(0,0,X),(R,R,R))\n",208,207,208,207);
	printf("	%c(B1,(1,0,%c)->(B1,(1,0,X),(R,R,R))			%c(B1,(0,1,%c)->(B1,(0,1,X),(R,R,R))\n",208,207,208,207);
	printf("	%c(B1,(%c,1,%c)->(B1,(%c,1,X),(S,R,R))			%c(B1,(%c,0,%c)->(B1,(%c,0,X),(S,R,R))\n",208,207,207,207,208,207,207,207);
	printf("	%c(B1,(1,%c,%c)->(B1,(1,%c,X),(R,S,R))			%c(B1,(0,%c,%c)->(B1,(0,%c,X),(R,S,R))\n",208,207,207,207,208,207,207,207);
	printf("	%c(B1,(%c,%c,%c)->(q0,(%c,%c,%c),(L,L,L))\n",208,207,207,207,207,207,207);
	//q0-q1-q0
		//a1 y b1
		printf("	%c(q0,(1,1,X)->(q1,(1,1,0),(S,S,L))			%c(q0,(1,1,1)->(q1,(1,1,1),(S,S,L))\n",208,208);
		printf("	%c(q1,(1,1,X)->(q0,(1,1,1),(L,L,S))			%c(q1,(1,1,X)->(q0,(1,1,1),(L,L,S))\n",208,208);
		// b2 y b3
		printf("	%c(q0,(1,0,1)->(q1,(1,0,0),(S,S,L))			%c(q0,(0,1,1)->(q1,(0,1,0),(S,S,L))\n",208,208);
		printf("	%c(q1,(1,0,X)->(q0,(1,0,1),(L,L,S))			%c(q1,(0,1,X)->(q0,(0,1,1),(L,L,S))\n",208,208);
		//b5 y b6
		printf("	%c(q0,(1,%c,1)->(q1,(1,%c,0),(S,S,L))			%c(q0,(%c,1,1)->(q1,(%c,1,0),(S,S,L))\n",208,207,207,208,207,207);
		printf("	%c(q1,(1,%c,X)->(q0,(1,%c,1),(L,S,S))			%c(q1,(%c,1,X)->(q0,(%c,1,1),(S,L,S))\n",208,207,207,208,207,207);
	//solo q0
		//a2 y a3
		printf("	%c(q0,(1,0,X)->(q0,(1,0,1),(L,L,L))			%c(q0,(0,1,X)->(q0,(0,1,1),(L,L,L))\n",208,208);
		//a4 yb4
		printf("	%c(q0,(0,0,X)->(q0,(0,0,0),(L,L,L))			%c(q0,(0,0,1)->(q0,(0,0,1),(L,L,L))\n",208,208);
		//a5 yb6
		printf("	%c(q0,(1,%c,X)->(q0,(1,%c,1),(L,S,L))			%c(q0,(%c,1,X)->(q0,(%c,1,1),(S,L,L))\n",208,207,207,208,207,207);
		//a7 y a8
		printf("	%c(q0,(0,%c,X)->(q0,(0,%c,0),(L,S,L))			%c(q0,(%c,0,X)->(q0,(%c,0,0),(S,L,L))\n",208,207,207,208,207,207);
		//b7 y b8
		printf("	%c(q0,(0,%c,1)->(q0,(0,%c,1),(L,S,L))			%c(q0,(%c,0,1)->(q0,(%c,0,1),(S,L,L))\n",208,207,207,208,207,207);
	//q2
	printf("	%c(q0,(%c,%c,X)->(q2,(%c,%c,%c),(S,S,L))			%c(q0,(%c,%c,1)->(q2,(%c,%c,1),(S,S,L))\n",208,207,207,207,207,207,208,207,207,207,207);

}
void trancision(){
	initwindow(720,620,"graphics");
	char text[3],cor[40],fle[2];
	//B0
	line(0, 200,60, 200);
	circle(80, 200, 20);
	sprintf(text, "B0");
	sprintf(fle, ">");
	outtextxy(60, 190, fle);
    outtextxy(70, 190, text);
    //q1
    line(100, 200,270, 200);
	circle(290, 200, 20);
	sprintf(cor, "1;1,S|1;1,S|%c;X,R",164,164,164);
	outtextxy(100, 70, cor);
	sprintf(cor, "1;1,S |0;0,S |%c;X,R",164);
	outtextxy(100, 85, cor);
	sprintf(cor, "0;0,S |1;1,S |%c;X,R",164);
	outtextxy(100, 100, cor);
	sprintf(cor, "0;0,S |0;0,S |%c;X,R",164);
	outtextxy(100, 115, cor);
	sprintf(cor, "1;1,S |%c;%c,S |%c;X,R",164,164,164);
	outtextxy(100, 130, cor);
	sprintf(cor, "0;0,S |%c;%c,S |%c;X,R",164,164,164);
	outtextxy(100, 145, cor);
	sprintf(cor, "%c;%c,S |1;1,S |%c;X,R",164,164,164);
	outtextxy(100, 160, cor);
	sprintf(cor, "%c;%c,S |0;0,S |%c;X,R",164,164,164);
	outtextxy(100, 175, cor);
	sprintf(text, "B1");
	sprintf(fle, ">");
	outtextxy(270, 190, fle);
    outtextxy(280, 190, text);
    //rep B1
    
    sprintf(cor, "1;1,R |1;1,R |%c;X,R",164,164,164);
	outtextxy(230, 50, cor);
	sprintf(cor, "1;1,R |0;0,R |%c;X,R",164);
	outtextxy(230, 65, cor);
	sprintf(cor, "0;0,R |1;1,R |%c;X,R",164);
	outtextxy(230, 80, cor);
	sprintf(cor, "0;0,R |0;0,R |%c;X,R",164);
	outtextxy(230, 95, cor);
	sprintf(cor, "1;1,R |%c;%c,S |%c;X,R",164,164,164);
	outtextxy(230, 110, cor);
	sprintf(cor, "0;0,R |%c;%c,S |%c;X,R",164,164,164);
	outtextxy(230, 125, cor);
	sprintf(cor, "%c;%c,S |1;1,R |%c;X,R",164,164,164);
	outtextxy(230, 140, cor);
	sprintf(cor, "%c;%c,S |0;0,R |%c;X,R",164,164,164);
	outtextxy(230, 155, cor);
	sprintf(fle, "v");
	outtextxy(295, 170, fle);
	arc(290,175, 315,225,10);
    //B1 a q0
    line(310, 200,500, 200);
	circle(520, 200, 20);
	sprintf(cor, "%c;%c,L |%c;%c,L |%c;%c,L",164,164,164,164,164,164);
	outtextxy(360, 175, cor);
	sprintf(fle, ">");
	outtextxy(490, 190, fle);
	sprintf(text, "q0");
	outtextxy(510, 190, text);
	//REP q0
    setcolor(YELLOW);
	sprintf(fle, "<");
	outtextxy(535, 200, fle);
	arc(545,200,205, 135,10);
		//LLL
		sprintf(cor, "0;0,L |0;0,L |X;0,L");
		outtextxy(560, 50, cor);
		sprintf(cor, "0;0,L |0;0,L |1;1,L");
		outtextxy(560, 65, cor);
		sprintf(cor, "1;1,L |0;0,L |X;1,L");
		outtextxy(560, 80, cor);
		sprintf(cor, "0;0,L |1;1,L |X;1,L");
		outtextxy(560, 95, cor);
		//LSL
		sprintf(cor, "1;1,L |%c;%c,S |X;1,L",164,164);
		outtextxy(560, 110, cor);
		sprintf(cor, "0;0,L |%c;%c,S |X;0,L",164,164);
		outtextxy(560, 125, cor);
		sprintf(cor, "0;0,L |%c;%c,S |1;1,L",164,164);
		outtextxy(560, 140, cor);
		//SLL
		sprintf(cor, "%c;%c,S |1;1,L |X;1,L",164,164);
		outtextxy(560, 155, cor);
		sprintf(cor, "%c;%c,S |0;0,L |X;0,L",164,164);
		outtextxy(560, 170, cor);
		sprintf(cor, "%c;%c,S |0;0,L |1;1,L",164,164);
		outtextxy(560, 185, cor);
	
	//q1
	//line(520, 220,520, 410);
	circle(520, 430, 20);
	sprintf(text, "q1");
	outtextxy(510, 420, text);
	arc(450,315,310, 50,140);
	arc(600,315,130, 230,140);
	sprintf(fle, "<");
	outtextxy(533, 410, fle);
	sprintf(fle, ">");
	outtextxy(502, 410, fle);
	//q0->q1
	sprintf(cor, "1;1,S |1;1,S |X;0,L");
	outtextxy(540, 277, cor);
	sprintf(cor, "1;1,S |1;1,S |1;1,L");
	outtextxy(540, 292, cor);
	sprintf(cor, "1;1,S |0;0,S |1;0,L");
	outtextxy(540, 307, cor);
	sprintf(cor, "0;0,S |1;1,S |1;0,L");
	outtextxy(540, 322, cor);
	sprintf(cor, "1;1,S |%c;%c,S |1;0,L",164,164);
	outtextxy(540, 337, cor);
	sprintf(cor, "%c;%c,S |1;1,S |1;0,L",164,164);
	outtextxy(540, 352, cor);
	//q1->q0
	sprintf(cor, "1;1,L |1;1,L |X;1,S");
	outtextxy(400, 277, cor);
	sprintf(cor, "1;1,L |1;1,L |X;1,S");
	outtextxy(400, 292, cor);
	sprintf(cor, "1;1,L |0;0,L |X;1,S");
	outtextxy(400, 307, cor);
	sprintf(cor, "0;0,L |1;1,L |X;1,S");
	outtextxy(400, 322, cor);
	sprintf(cor, "1;1,L |%c;%c,S |X;1,S",164,164);
	outtextxy(400, 337, cor);
	sprintf(cor, "%c;%c,S |1;1,L |X;1,S",164,164);
	outtextxy(400, 352, cor);
	//q2
	line(505, 215,90, 415);
	circle(80, 430, 20);
	circle(80, 430, 25);
	sprintf(fle, "<");
	outtextxy(85, 410, fle);
	sprintf(text,"q2");
	outtextxy(70, 420, text);
	sprintf(cor, "%c;%c,S |%c;%c,S |X;%c,S",164,164,164);
	outtextxy(190, 307, cor);
	sprintf(cor, "%c;%c,S |%c;%c,S |1;1,L",164,164);
	outtextxy(190, 322, cor);
}
