#include<stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <time.h>
#define VERDE_F  "\x1b[42m"
#define VERDE_T        "\x1b[32m"
#define ROJO_F   "\x1b[41m"
#define ROJO_T     "\x1b[31m"
#define RESET_COLOR    "\x1b[0m"
void gotoxy(int x, int y) {
    HANDLE hcon;
    hcon = GetStdHandle(STD_OUTPUT_HANDLE);
    COORD dwPos;
    dwPos.X = x;
    dwPos.Y = y;
    SetConsoleCursorPosition(hcon, dwPos);
}
void cuadra(){
	int x,y;
	for(y=0;y<=105;y++){
		for(x=0;x<=29;x++){
			if(y==105&&x!=y){
				gotoxy(y,x);	printf("%c",179);
			}
			if(y==0&&x!=y){
				gotoxy(y,x);	printf("%c",179);
			}
			if(x==0){
				if(y==0){
					gotoxy(y,x);	printf("%c",201);
				}
				else{
					if(y==105){
						gotoxy(y,x);	printf("%c",187);
					}
					else{
						gotoxy(y,x);	printf("%c",196);
					}
				}
			}
			if(x==29){
				if(y==0){
					gotoxy(y,x);	printf("%c",200);
				}
				else{
					if(y==105){
						gotoxy(y,x);	printf("%c",188);
					}
					else{
						gotoxy(y,x);	printf("%c",196);
					}
				}
			}
			
		}	
	}
}
void fecha(){
	time_t tiempo_actual;
			time(&tiempo_actual);
			struct tm *info_tiempo = localtime(&tiempo_actual);
			gotoxy(5,2); printf(ROJO_F"  %02d/%02d/%02d  "RESET_COLOR, info_tiempo->tm_mday,info_tiempo->tm_mon+1,info_tiempo->tm_year+1900);
			
}
