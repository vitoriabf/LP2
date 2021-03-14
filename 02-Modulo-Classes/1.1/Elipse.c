#include <stdio.h>
typedef struct {
    int x, y;
    int rectwidth, rectheight;
} Ellipse2D;

void print (Ellipse2D* this) {
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
    this->rectwidth, this->rectheight, this->x, this->y);
}

void main(void)
{
    Ellipse2D e1 = {1,1, 10,10};
    print(&e1);
}


