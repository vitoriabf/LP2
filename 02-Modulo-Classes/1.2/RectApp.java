public class RectApp {
    public static void main(String[] args) {
        Rect r1 = new Rect(1, 1, 10, 10);
        r1.print();
        r1.area();
        r1.drag(8, 6);
    }
}

class Rect {
    int x, y;
    int w, h;

    Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void print() {
        System.out.format("Retangulo de tamanho (%d, %d) na posicao (%d, %d)\n", this.w, this.h, this.x, this.y);
    }

    int area() {
        System.out.format("Area: %d\n", this.w * this.h);
        return this.w * this.h;
    }

    void drag(int dx, int dy) {
        System.out.format("Objeto arrastado para a posicao: (%d, %d)\n", (this.x + dx), (this.y + dy));
    }
}