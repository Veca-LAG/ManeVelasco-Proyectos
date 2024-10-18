/*****************************************************
This program was produced by the
CodeWizardAVR V2.05.0 Professional
Automatic Program Generator
� Copyright 1998-2010 Pavel Haiduc, HP InfoTech s.r.l.
http://www.hpinfotech.com

Project : 
Version : 
Date    : 21/06/2024
Author  : NeVaDa
Company : 
Comments: 


Chip type               : ATmega328P
Program type            : Application
AVR Core Clock frequency: 1.000000 MHz
Memory model            : Small
External RAM size       : 0
Data Stack size         : 512
*****************************************************/

#include <mega328p.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <delay.h>
// Alphanumeric LCD Module functions
#include <alcd.h>
 void AH(void);
 void BH(void); 
// Declare your global variables here   
float AA=0,BB=0,CC=0,cx=0,frac=0; 
int xpuntoa=0,xpuntob=0, centero=0,cpunto=0,CH=0;
 int conta=0,contb=0,v1,v2,v1=0,v2=0,vo=0,maxp=0;
 char numA[12]="",numB[12]="",numF[19]="",lugar='A';
 char varA[50],varC[50];
char binario,operacion;
// External Interrupt 0 service routine
interrupt [EXT_INT0] void ext_int0_isr(void)
{
// OPCION DE BORRA EN CALCULADORA
    AA=0;
    BB=0;
    CC=0;
    v2=0;
    vo=0;     
    v1=0;  
    conta=0;
    contb=0;   
    xpuntoa=0;
    xpuntob=0;
    memset(numA, 0, 12);//borrar todo numA[10]
    memset(numB, 0, 12);//borrar todo numB[10] 
    memset(numF, 0, 22);//borrar todo numC[20] 
    lugar='A';
    operacion='';
    lcd_clear();
}

// External Interrupt 1 service routine
interrupt [EXT_INT1] void ext_int1_isr(void)
{
// LEE Y GUARDA PUERTO C0-4 
      binario=PINC&0X1F;
}

// Declare your global variables here

void main(void)
{
// Declare your local variables here

// Crystal Oscillator division factor: 8
#pragma optsize-
CLKPR=0x80;
CLKPR=0x03;
#ifdef _OPTIMIZE_SIZE_
#pragma optsize+
#endif

// Input/Output Ports initialization
// Port B initialization
// Func7=Out Func6=Out Func5=Out Func4=Out Func3=In Func2=Out Func1=Out Func0=Out 
// State7=0 State6=0 State5=0 State4=0 State3=T State2=0 State1=0 State0=0 
PORTB=0x00;
DDRB=0xFF;

// Port C initialization
// Func6=In Func5=In Func4=In Func3=In Func2=In Func1=In Func0=In 
// State6=T State5=T State4=T State3=T State2=T State1=T State0=T 
PORTC=0x00;
DDRC=0x00;

// Port D initialization
// Func7=In Func6=In Func5=In Func4=In Func3=In Func2=In Func1=In Func0=In 
// State7=T State6=T State5=T State4=T State3=T State2=T State1=T State0=T 
PORTD=0x04;
DDRD=0x00;

// Timer/Counter 0 initialization
// Clock source: System Clock
// Clock value: Timer 0 Stopped
// Mode: Normal top=0xFF
// OC0A output: Disconnected
// OC0B output: Disconnected
TCCR0A=0x00;
TCCR0B=0x00;
TCNT0=0x00;
OCR0A=0x00;
OCR0B=0x00;

// Timer/Counter 1 initialization
// Clock source: System Clock
// Clock value: Timer1 Stopped
// Mode: Normal top=0xFFFF
// OC1A output: Discon.
// OC1B output: Discon.
// Noise Canceler: Off
// Input Capture on Falling Edge
// Timer1 Overflow Interrupt: Off
// Input Capture Interrupt: Off
// Compare A Match Interrupt: Off
// Compare B Match Interrupt: Off
TCCR1A=0x00;
TCCR1B=0x00;
TCNT1H=0x00;
TCNT1L=0x00;
ICR1H=0x00;
ICR1L=0x00;
OCR1AH=0x00;
OCR1AL=0x00;
OCR1BH=0x00;
OCR1BL=0x00;

// Timer/Counter 2 initialization
// Clock source: System Clock
// Clock value: Timer2 Stopped
// Mode: Normal top=0xFF
// OC2A output: Disconnected
// OC2B output: Disconnected
ASSR=0x00;
TCCR2A=0x00;
TCCR2B=0x00;
TCNT2=0x00;
OCR2A=0x00;
OCR2B=0x00;

// External Interrupt(s) initialization
// INT0: On
// INT0 Mode: Low level
// INT1: On
// INT1 Mode: Rising Edge
// Interrupt on any change on pins PCINT0-7: Off
// Interrupt on any change on pins PCINT8-14: Off
// Interrupt on any change on pins PCINT16-23: Off
EICRA=0x0C;
EIMSK=0x03;
EIFR=0x03;
PCICR=0x00;

// Timer/Counter 0 Interrupt(s) initialization
TIMSK0=0x00;

// Timer/Counter 1 Interrupt(s) initialization
TIMSK1=0x00;

// Timer/Counter 2 Interrupt(s) initialization
TIMSK2=0x00;

// USART initialization
// USART disabled
UCSR0B=0x00;

// Analog Comparator initialization
// Analog Comparator: Off
// Analog Comparator Input Capture by Timer/Counter 1: Off
ACSR=0x80;
ADCSRB=0x00;
DIDR1=0x00;

// ADC initialization
// ADC disabled
ADCSRA=0x00;

// SPI initialization
// SPI disabled
SPCR=0x00;

// TWI initialization
// TWI disabled
TWCR=0x00;

// Alphanumeric LCD initialization
// Connections specified in the
// Project|Configure|C Compiler|Libraries|Alphanumeric LCD menu:
// RS - PORTB Bit 0
// RD - PORTB Bit 1
// EN - PORTB Bit 2
// D4 - PORTB Bit 4
// D5 - PORTB Bit 5
// D6 - PORTB Bit 6
// D7 - PORTB Bit 7
// Characters/line: 16
lcd_init(16);

// Global enable interrupts
#asm("sei")

while (1)
      {
      //CODIGO DE CALCULADORA
           //AH();
           if(lugar=='A'){  
                AH();
                if(conta!=0){  
                    v1=1;  
                } 
            }else if(lugar=='B'){  
                if(vo==1){
                    BH();
                    if(contb!=0){
                        v2=1;     
                    } 
                }
            }
            if(v1==1){
            //OPERACIONES + - / * 
                if(binario==0B01101){
                   operacion='+';
                   lugar='B'; 
                   vo=1;   
                   v2=0;
                   binario=0B00000;
                }  
                if(binario==0B01110){
                   operacion='-';
                   lugar='B'; 
                   vo=1;
                   v2=0;    
                   binario=0B00000;
                }   
                if(binario==0B01111){
                   operacion='x'; 
                   lugar='B';
                   vo=1;     
                   v2=0;  
                   binario=0B00000;
                }  
                if(binario==0B10000){
                   operacion='/'; 
                   lugar='B';
                   vo=1;    
                   v2=0;    
                   binario=0B00000;
                } 
            //FIN OPERACIONES 
                // RESULTADO 
                if(v2==1&&binario==0B01100){
                    if(operacion=='+'){ 
                            AA=atof(numA);
                            BB=atof(numB);
                            CC=AA+BB;   
                            centero=CC; 
                            frac=CC-centero;
                            cx=abs(frac*1000);
                            CH=abs(cx);
                            cpunto=CH;  //+1
                                if(cpunto<100){
                                    if(cpunto<10){
                                      sprintf(numF,"%d.00%d", abs(centero),abs(cpunto));
                                    }   
                                    sprintf(numF,"%d.0%d", abs(centero),abs(cpunto));
                                }else{
                                    sprintf(numF,"%d.%d", abs(centero),abs(cpunto));   
                                } 
                            binario=0B00000;
                    }
                    if(operacion=='-'){ 
                            AA=atof(numA);
                            BB=atof(numB);
                            CC=AA-BB;  
                            centero=CC; 
                            frac=CC-centero;
                            cx=abs(frac*1000);
                            CH=abs(cx);
                            cpunto=CH;  //+1
                                if(cpunto<100){
                                    if(cpunto<10){
                                      sprintf(numF,"%d.00%d", centero,abs(cpunto));
                                    }   
                                    sprintf(numF,"%d.0%d", centero,abs(cpunto));
                                }else{
                                    sprintf(numF,"%d.%d", centero,abs(cpunto));   
                                }   
                            binario=0B00000; 
                    }  
                    if(operacion=='x'){
                            AA=atof(numA);
                            BB=atof(numB);
                            CC=AA*BB;   
                            centero=CC; 
                            frac=CC-centero;
                            cx=abs(frac*1000);
                            CH=abs(cx);
                            cpunto=CH;  //+1
                                if(cpunto<100){
                                    if(cpunto<10){
                                      sprintf(numF,"%d.00%d", centero,abs(cpunto));
                                    }   
                                    sprintf(numF,"%d.0%d", centero,abs(cpunto));
                                }else{
                                    sprintf(numF,"%d.%d", centero,abs(cpunto));   
                                }  
                            binario=0B00000;
                    }    
                    if(operacion=='/'){ 
                            AA=atof(numA);
                            BB=atof(numB);
                            CC=AA/BB;   
                            centero=CC; 
                            frac=CC-centero;
                            cx=abs(frac*1000);
                            CH=abs(cx);
                            cpunto=CH;  //+1
                                if(cpunto<100){
                                    if(cpunto<10){
                                      sprintf(numF,"%d.00%d", centero,abs(cpunto));
                                    }   
                                    sprintf(numF,"%d.0%d", centero,abs(cpunto));
                                }else{
                                    sprintf(numF,"%d.%d", centero,abs(cpunto));   
                                }
                            binario=0B00000;
                    }   
                    lcd_clear();
                }  
                //FIN RESULTADO
                
            }
             
            lcd_gotoxy(0,0);//0-19 y0-3
            sprintf(varA,"%s%c%s",numA,operacion,numB); //mensaje
            lcd_puts(varA);   
              
            /*lcd_gotoxy(0,1);//0-19 y0-3
            sprintf(varO,"%c",operacion); //mensaje
            lcd_puts(varO); 
            
            lcd_gotoxy(0,2);//0-19 y0-3
            sprintf(varB,"%s",numB); //mensaje
            lcd_puts(varB);*/
            
            lcd_gotoxy(0,1);//0-19 y0-3
            sprintf(varC,"=%s",numF); //mensaje
            lcd_puts(varC);  
            
            delay_ms(250); 
      }
}
//CADENA A
void AH(){
    int ex=0; 
    int band=conta;
    if(xpuntoa==1){
        ex=maxp+2;
    }else{
        ex=7;
    }
    if(conta<=ex){ 
            if(binario==0B00001){
               numA[conta]='0';
               conta++; 
            } else if(binario==0B00010){
               numA[conta]='1'; 
               conta++; 
               //numA[conta]='\0';
            }  else if(binario==0B00011){
               numA[conta]='2'; 
               conta++; 
            }  else if(binario==0B00100){   
               numA[conta]='3'; 
               conta++; 
            } else if(binario==0B00101){   
               numA[conta]='4'; 
               conta++; 
            } else if(binario==0B00110){  
               numA[conta]='5'; 
               conta++; 
            } else if(binario==0B00111){
               numA[conta]='6'; 
               conta++; 
               //numA[conta]='\0';
            } else if(binario==0B01000){
               numA[conta]='7'; 
               conta++; 
            } else if(binario==0B01001){
               numA[conta]='8'; 
               conta++; 
            }else if(binario==0B01010){
               numA[conta]='9'; 
               conta++; 
            }else if(binario==0B01011){
                if(xpuntoa==0){ 
                    numA[conta]='.';//no el mismo tama�o-1 
                    conta++; 
                    xpuntoa=1;
                    maxp=conta;
                } 
            } 
            if(conta!=band){
                binario=0B00000; 
            }
            
    }  
}
//CADENA B
void BH(){
    int ex=0; 
    int band=contb;
    if(xpuntob==1){
        ex=maxp+2;
    }else{
        ex=7;
    }
    if(contb<=ex){ 
            if(binario==0B00001){
               numB[contb]='0';
               contb++; 
            } else if(binario==0B00010){
               numB[contb]='1'; 
               contb++; 
            }  else if(binario==0B00011){
               numB[contb]='2'; 
               contb++; 
            }  else if(binario==0B00100){   
               numB[contb]='3'; 
               contb++; 
            } else if(binario==0B00101){   
               numB[contb]='4'; 
               contb++; 
            } else if(binario==0B00110){  
               numB[contb]='5'; 
               contb++; 
            } else if(binario==0B00111){
               numB[contb]='6'; 
               contb++; 
            } else if(binario==0B01000){
               numB[contb]='7'; 
               contb++; 
            } else if(binario==0B01001){
               numB[contb]='8'; 
               contb++; 
            }else if(binario==0B01010){
               numB[contb]='9'; 
               contb++; 
            }else if(binario==0B01011){
                if(xpuntob==0){ 
                    numB[contb]='.';//no el mismo tama�o-1 
                    contb++; 
                    xpuntob=1;
                    maxp=contb;
                } 
            } 
            if(contb!=band){
                binario=0B00000; 
            }
            
    }  
}
