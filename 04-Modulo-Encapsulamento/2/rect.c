#include <stdlib.h>
#include <stdio.h>
#include "rect.h"
typedef struct Rect {
	int x, y;
	int w, h;
} Rect;

Rect* rect_new (void) {
	Rect* this = malloc(sizeof(Rect));
	this->x = 20;
	this->y = 20;
	this->w = 20;
	this->h = 20;
};

void rect_drag (Rect* this, int dx, int dy){
	this->x += dx;
	this->y += dy;
};
void rect_print (Rect* this) {
	printf("Retangulo na posicao (%d, %d) e tamanho (%d, %d) \n", this->x,this->y,this->w,this->h);
};
