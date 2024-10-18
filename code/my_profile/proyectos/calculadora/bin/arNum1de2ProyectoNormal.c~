/*****************************************************
This program was produced by the
CodeWizardAVR V2.05.0 Professional
Automatic Program Generator
© Copyright 1998-2010 Pavel Haiduc, HP InfoTech s.r.l.
http://www.hpinfotech.com

Project :
Version :
Date : 03/06/2024
Author : NeVaDa
Company :
Comments:

Chip type : ATmega328P
Program type : Application
AVR Core Clock frequency: 0.125000 MHz
Memory model : Small
External RAM size : 0
Data Stack size : 512
*****************************************************/

#include <mega328p.h>
#include <delay.h>


// Declare your global variables here

void main(void)
{
// Declare your local variables here

// Crystal Oscillator division factor: 8
#pragma optsize-
CLKPR=0x80;
CLKPR=0x03;
#ifdef OPTIMIZE_SIZE
#pragma optsize+
#endif

// Input/Output Ports initialization
// Port B initialization
// Func7=In Func6=In Func5=In Func4=In Func3=In Func2=In Func1=In Func0=In
// State7=T State6=T State5=T State4=T State3=T State2=T State1=T State0=T
PORTB=0xFF;
DDRB=0x00;

// Port C initialization
// Func6=In Func5=Out Func4=Out Func3=Out Func2=Out Func1=Out Func0=Out
// State6=T State5=0 State4=0 State3=0 State2=0 State1=0 State0=0
PORTC=0x00;
DDRC=0x3F;

// Port D initialization
// Func7=In Func6=In Func5=In Func4=In Func3=In Func2=In Func1=In Func0=In
// State7=T State6=T State5=T State4=T State3=T State2=T State1=T State0=T
PORTD=0xFF;
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
// INT0: Off
// INT1: Off
// Interrupt on any change on pins PCINT0-7: Off
// Interrupt on any change on pins PCINT8-14: Off
// Interrupt on any change on pins PCINT16-23: Off
EICRA=0x00;
EIMSK=0x00;
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

while (1)
{

// Place your code here
// Leer los botones y enviar el valor correspondiente por el puerto C

        PORTC.5 = 0;
        if (PINB.0 == 0) {
            PORTC = 0B00001;
        } else if (PINB.1 == 0) {
            PORTC = 0B00010;
        } else if (PINB.2 == 0) {
            PORTC = 0B00011;
        } else if (PINB.3 == 0) {
            PORTC = 0B00100;
        } else if (PINB.4 == 0) {
            PORTC = 0B00101;
        } else if (PINB.5 == 0) {
            PORTC = 0B00110;
        } else if (PINB.6 == 0) {
            PORTC = 0B00111;
        } else if (PINB.7 == 0) {
            PORTC = 0B101000;
        } else if (PIND.0 == 0) {
            PORTC = 0B01001;
        } else if (PIND.1 == 0) {
            PORTC = 0B01010;
        } else if (PIND.2 == 0) {
            PORTC = 0B01011;
        } else if (PIND.3 == 0) {
            PORTC = 0B01100;
        } else if (PIND.4 == 0) {
            PORTC = 0B01101;
        } else if (PIND.5 == 0) {
            PORTC = 0B01110;
        } else if (PIND.6 == 0) {
            PORTC = 0B01111;
        } else if (PIND.7 == 0) {
            PORTC = 0B10000;
        } else {
            PORTC = 0x00;
        }

        // Activar la señal de envío
        if (PORTC != 0x00) {
            PORTC.5 = 1;
            delay_ms(300);
            PORTC = 0;
        } 

    }
}