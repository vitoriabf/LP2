#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    Color fg, bg;
    void (* print) (struct Figure*);
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Rect* rect_new (int x, int y, int w, int h) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) rect_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Ellipse_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
    int startAngle, endAngle;
} Arc;

void Arc_print (Arc* this) {
    Figure* sup = (Figure*) this;
    printf("Arc de tamanho (%d,%d) na posicao (%d,%d) e angulo (%d,%d).\n",
           this->w, this->h, sup->x, sup->y, this->startAngle, this->endAngle);
}

Arc* arc_new (int x, int y, int w, int h, int startAngle, int endAngle) {
    Arc* this = malloc(sizeof(Arc));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Arc_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    this->startAngle = startAngle;
    this->endAngle = endAngle;
    
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
    int ctrlx1, ctrly1, ctrlx2, ctrly2;
} cubicCurve;

void cubicCurve_print (cubicCurve* this) {
    Figure* sup = (Figure*) this;
    printf("Cubic Curve com coordenadas, primeiro ponto: (%d,%d), segundo ponto: (%d,%d) e coordenadas do primeiro " 
		"ponto de controle: (%d,%d), segundo ponto de controle: (%d, %d).\n",
           this->w, this->h, sup->x, sup->y, this->ctrlx1, this->ctrly1, this->ctrlx2, this->ctrly2);
}

cubicCurve* cubic_curve_new (int x, int y, int w, int h, int ctrlx1, int ctrly1, int ctrlx2, int ctrly2) {
    cubicCurve* this = malloc(sizeof(cubicCurve));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) cubicCurve_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    this->ctrlx1 = ctrlx1;
    this->ctrly1 = ctrly1;
    this->ctrlx2 = ctrlx2;
    this->ctrly2 = ctrly2;
    
}



void main (void) {
	int i;
	
    Figure* figs[4] = {
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(40,10,140,300),
        (Figure*) arc_new(10,10,100,100,45,90),
        (Figure*) cubic_curve_new(210,110,305,130,45,90,50,81)
    };

    ///

    for (i=0; i<4; i++) {
        figs[i]->print(figs[i]);
    }

    ///

    for (i=0; i<4; i++) {
        free(figs[i]);
    }
}
