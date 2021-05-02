#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);
typedef int  (* Figure_Area)  (struct Figure*);
typedef int  (* Figure_Perimetro)  (struct Figure*);


typedef struct {
    void (* print) (struct Figure*);
    int  (* area)  (struct Figure*);
    int  (* perimetro)  (struct Figure*);
} Figure_vtable;

typedef struct Figure {
    int x, y;
    Color fg, bg;
    Figure_vtable* vtable;
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d); area %d, perimetro %d.\n\n",
           this->w, this->h, sup->x, sup->y, sup->vtable->area(sup), sup->vtable->perimetro(sup));
}

int rect_area (Rect* this) {
    Figure* sup = (Figure*) this;
    return this->w * this->h;
}

int rect_perimetro (Rect* this) {
    Figure* sup = (Figure*) this;
    return this->w*2 + this->h*2;
}

Figure_vtable rect_vtable = {
    (Figure_Print) rect_print,
    (Figure_Area)  rect_area,
    (Figure_Perimetro) rect_perimetro
};

Rect* rect_new (int x, int y, int w, int h) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->vtable = &rect_vtable;
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

void ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d); area: %d e perimetro: %d.\n\n",
           this->w, this->h, sup->x, sup->y, sup->vtable->area(sup),sup->vtable->perimetro(sup));
}

int ellipse_area (Ellipse* this) {
    Figure* sup = (Figure*) this;
    return this->w * this->h;
}

int ellipse_perimetro (Ellipse* this) {
    Figure* sup = (Figure*) this;
    return this->w*2 + this->h*2;
}

Figure_vtable ellipse_vtable = {
    (Figure_Print) ellipse_print,
    (Figure_Area)  ellipse_area,
    (Figure_Perimetro) ellipse_perimetro
};

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->vtable = &ellipse_vtable;
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

void arc_print (Arc* this) {
    Figure* sup = (Figure*) this;
    printf("Arc de tamanho (%d,%d) na posicao (%d,%d) e angulo (%d,%d); area: %d e perimetro: %d.\n\n",
           this->w, this->h, sup->x, sup->y, this->startAngle, this->endAngle,sup->vtable->area(sup), sup->vtable->perimetro(sup));
}

int arc_area (Arc* this) {
    Figure* sup = (Figure*) this;
    return this->w * this->h;
}

int arc_perimetro (Arc* this) {
    Figure* sup = (Figure*) this;
    return this->w*2 + this->h*2;
}

Figure_vtable arc_vtable = {
    (Figure_Print) arc_print,
    (Figure_Area)  arc_area,
    (Figure_Perimetro) arc_perimetro
};


Arc* arc_new (int x, int y, int w, int h, int startAngle, int endAngle) {
    Arc* this = malloc(sizeof(Arc));
    Figure* sup = (Figure*) this;
    sup->vtable = &arc_vtable;
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
		"ponto de controle: (%d,%d), segundo ponto de controle: (%d, %d). Area %d e Perimetro: %d \n\n",
           this->w, this->h, sup->x, sup->y, this->ctrlx1, this->ctrly1, this->ctrlx2, this->ctrly2, sup->vtable->area(sup),sup->vtable->perimetro(sup));
}

int cubicCurve_area (cubicCurve* this) {
    Figure* sup = (Figure*) this;
    return (this->w+sup->x) * (this->h+sup->y);
}

int cubicCurve_perimetro (cubicCurve* this) {
    Figure* sup = (Figure*) this;
    return (sup->x+this->w)*2 + (sup->y+this->h)*2;
}

Figure_vtable cubicCurve_vtable = {
    (Figure_Print) cubicCurve_print,
    (Figure_Area)  cubicCurve_area,
    (Figure_Perimetro) cubicCurve_perimetro
};

cubicCurve* cubic_curve_new (int x, int y, int w, int h, int ctrlx1, int ctrly1, int ctrlx2, int ctrly2) {
    cubicCurve* this = malloc(sizeof(cubicCurve));
    Figure* sup = (Figure*) this;
    sup->vtable = &cubicCurve_vtable;
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
        figs[i]->vtable->print(figs[i]);
    }

    ///

    for (i=0; i<4; i++) {
        free(figs[i]);
    }
}
