public class ArcApp {
    public static void main(String[] args) {
        Arc a = new Arc(1, 1, 5, 5, 90, 135);
        a.print();
    }
}

class Arc {
    int x, y;
    int rectwidth, rectheight;
    int radius1, radius2;
    char type;

    Arc(int x, int y, int rectwidth, int rectheight, int radius1, int radius2) {
        this.x = x;
        this.y = y;
        this.rectwidth = rectwidth;
        this.rectheight = rectheight;
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    void print() {
        System.out.format("Arco de coordenadas (%d,%d) e tamanho (%d,%d), radius (%d, %d).", this.x, this.y,
                this.rectwidth, this.rectheight, this.radius1, this.radius2);
    }

}