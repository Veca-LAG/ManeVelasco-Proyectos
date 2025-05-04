#include<graphics.h>
#include<conio.h>
#include <iostream>
#include <set>
#include <string.h>
#include <math.h>
int main(){
	int na,ca,a[100],ne,fe,nf,ff,nc;
	char e[10],i,f[10],trans[10][10],c[10];
	char fin;
	/*alfabeto*/
	printf("MAXIMO 10\n");
	printf("Alfabeto(solo numeros)\n");
	printf("cantidad de alfabetos    ");
	scanf("%d",&na);
	for(ca=0;ca<na;ca++){
		scanf("%d",&a[ca]);
	}
	/*estado*/
	printf("estado(solo caracteres)\n");
	printf("cantidad de estados    ");
	scanf("%d",&ne);
	for(fe=0;fe<ne;fe++){
		fflush(stdin);
		scanf("%c",&e[fe]);
	}	
	/*estado inicial*/
	printf("estado inical(solo caracteres)   \n");
	fflush(stdin);
	scanf("%c",&i);
	/*estados finales*/
	printf("estado final(solo caracteres)\n");
	printf("cantidad de estados finales menores o igual a %d=  ",ne);
	scanf("%d",&ff);
	for(nf=0;nf<ff;nf++){
		fflush(stdin);
		scanf("%c",&f[nf]);
		printf("%c",f[nf]);
	}
	/*transiciones*/
	printf("tranciones\n ");
	fflush(stdin);
	for(fe=0;fe<ne;fe++){
		for(ca=0;ca<na;ca++){
				printf("%c( %c , %d ) -> ",245,e[fe],a[ca]);
				fflush(stdin);
				scanf ("%c",&trans[fe][ca]);
		}
	}	
	/*tabla*/
	printf("    "); 
	for(ca=0;ca<na;ca++){   
		printf("%d      ",a[ca]);
	}
	printf("\n");
	for(fe=0;fe<ne;fe++){
		
		if(e[fe]==i){
			printf("->");
		}
		else{
			printf("  ");
		}
	
		for(nf=0;nf<ff;nf++){
			if(e[fe]==f[nf]){
				printf(" * ");
			}
		}
		if(e[fe]!=f[nf]){
				printf("  ");
		}
		
		printf(" %c      ",e[fe]);
			for(ca=0;ca<na;ca++){
				printf("%c      ",trans[fe][ca]);
			}
			printf("\n");
	}
	printf("\n\n");
	/*cadena*/
	printf("cadena\n ");
	fflush(stdin);
	scanf("%s",c);
	/*cadena valida*/
	char EstadoActual , EstadoSiguiente;
    int simbolo,dato, final;
    final = 0;
    EstadoActual = i;
    for (nc = 0; c[nc] != '\0'; nc++) {
        simbolo = -1;
        for (ca = 0; ca < na; ca++) {
            if (a[ca] == (c[nc] - '0')) {
                simbolo = ca;    break;
            }
        }
        if (simbolo == -1) {
            printf("Cadena no valida: Carácter '%c' no esta en el alfabeto.\n", c[nc]);
            return 0;
        }
        for(dato=0;dato<ne;dato++){
			if(EstadoActual==e[dato]){
				break;
			}
		}
        EstadoSiguiente = trans[dato][simbolo];
        if (EstadoSiguiente == '0') {
            printf("Cadena no valida: No hay transicion definida desde el estado '%c' con simbolo '%c'.\n", EstadoActual , c[nc]);    return 0;
        }
        EstadoActual  = EstadoSiguiente;
    }
    for (nf = 0; nf < ff; nf++) {
        if (EstadoActual  == f[nf]) {
            final = 1;    break;
        }
    }
    if (final==1) {
        printf("\nCadena valida.\n");
    } else {
        printf("Cadena no valida: La cadena no llego a un estado final.\n");
    }
    /*grafo impreso*/
	char falsoe[10],falsoa[10],texto[]=",",ejemplo[]="x";
	char error[]="Error";
	int x=60,y=60,k,j,l,m,r=-1,t,ekm;
	initwindow(1000,1000,"graphics");
	setcolor(WHITE);
	for (k = 0; k < ne; k++) {
        if(e[k]==i){
		    line(x-60,y, x-50, y);
		    line(x-50, y-10, x-50, y+10);
		    line(x-50, y+10, x-40, y);
		    line(x-40, y,x-50, y-10);
		}
        falsoe[0]=e[k];
        falsoe[1]='\0';
        for(l=0;l<ff;l++)
		{
			if(e[k]==f[l]){
        		circle(x, y, 35);
        		circle(x, y, 25);
			}
			else{
        		circle(x, y, 35);
			}
		}
        outtextxy(x-7, y-7, falsoe);
		for(j = 0; j < na; j++){
			t=0;
			for (m=1;m<ne;m++) {
				itoa(a[j], falsoa, 10);
		       	if(trans[k][j]==e[k]){
						arc(x+40,y-20,-90,135,20);
						line(x+45,y-15, x+40, y);
						line(x+40, y,x+50,y);
						outtextxy((x+67)+(5*j), y-20, texto);
						outtextxy((x+65)+(15*j), y-20, falsoa);
				}
				if(trans[k][j]==e[k+m]){
					arc(x+(30+(-46*t)),y+(70+(130*t)),15,75,50+(150*t));
					line(x+(65+(102*t)),y+(45+(100*t)), x+(75+(102*t)), y+(55+(100*t)));
					line(x+(75+(102*t)),y+(55+(100*t)), x+(80+(102*t)), y+(45+(100*t)));
					
					outtextxy((x+(62+(80*t)))+(5*j), y+(10+(30*t)), texto);
					outtextxy((x+(60+(80*t)))+(15*j), y+(10+(30*t)), falsoa);
				}	
				if(k-m>=0&&trans[k][j]==e[k-m]){
					line(x-(65+(102*t)),y-(45+(100*t)), x-(75+(102*t)), y-(55+(100*t)));
					line(x-(75+(102*t)),y-(55+(100*t)), x-(80+(102*t)), y-(45+(100*t)));
					arc(x-(30+(-46*t)),y-(70+(130*t)),195,255,50+(150*t));
					outtextxy((x-(82+(80*t)))+(5*j), y-(40+(30*t)), texto);
					outtextxy((x-(80+(80*t)))+(15*j), y-(40+(30*t)), falsoa);
				}t++;
			}t=0;
       	}x = x + 90;    y = y + 90;
    }getch();
    t=0;
    /*animacion*/
    getch();
    final=0;    EstadoActual = i;	x=60;	y=60;	
	    for (nc = 0; c[nc] != '\0'; nc++) {
	        simbolo = -1;
	        for (ca = 0; ca < na; ca++) {
	            if (a[ca] == (c[nc] - '0')) {
	                simbolo = ca;  
					break;
	        }
	        if (simbolo == -1) {   outtextxy(0, 500, error);    return 0;    }
	        for(dato=0;dato<ne;dato++){
				if(EstadoActual==e[dato]){	break;	}
			}
	        EstadoSiguiente = trans[dato][simbolo];
	        if (EstadoSiguiente == '0') {
	            outtextxy(0, 500, error);    
				return 0;
	        }
	        EstadoActual  = EstadoSiguiente;
	        getch();
	        delay(100);
	        for (m=1;m<ne;m++) {
	        	setcolor(YELLOW);
			       	if(EstadoActual==e[dato]){
							arc(x+40,y-20,-90,135,20);
							line(x+45,y-15, x+40, y);
							line(x+40, y,x+50,y);
							outtextxy((x+67)+(5*j), y-20, texto);
							outtextxy((x+65)+(15*j), y-20, falsoa);
					}
					if(EstadoActual==e[dato+m]){
						arc(x+(30+(-46*t)),y+(70+(130*t)),15,75,50+(150*t));
						line(x+(65+(102*t)),y+(45+(100*t)), x+(75+(102*t)), y+(55+(100*t)));
						line(x+(75+(102*t)),y+(55+(100*t)), x+(80+(102*t)), y+(45+(100*t)));
						
						outtextxy((x+(62+(80*t)))+(5*j), y+(10+(30*t)), texto);
						outtextxy((x+(60+(80*t)))+(15*j), y+(10+(30*t)), falsoa);
					}	
					if(dato-m>=0&&EstadoActual==e[dato-m]){
						line(x-(65+(102*t)),y-(45+(100*t)), x-(75+(102*t)), y-(55+(100*t)));
						line(x-(75+(102*t)),y-(55+(100*t)), x-(80+(102*t)), y-(45+(100*t)));
						arc(x-(30+(-46*t)),y-(70+(130*t)),195,255,50+(150*t));
						outtextxy((x-(82+(80*t)))+(5*j), y-(40+(30*t)), texto);
						outtextxy((x-(80+(80*t)))+(15*j), y-(40+(30*t)), falsoa);
					}t++;
					break;
	            }t=0;
	    }x = x + 90;    y = y + 90;/**/
	}
	for (nf = 0; nf < ff; nf++) {
        if (EstadoActual  == f[nf]) {
            final = 1;    break;
        }
    }
	if (final!=1) {
        outtextxy(0, 500, error);
    }
    getch();
	closegraph();
	return 0;
}
