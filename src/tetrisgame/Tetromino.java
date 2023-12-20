package tetrisgame;

/*
编写四方格父类
    属性：Cell数组用于创建4个小方块
    方法：左移一格、右移一格、下落一格、变形(暂时不考虑)
 */
public class Tetromino {
    protected Cell[] cells = new Cell[4];
    //编写旋转状态
    protected State[] states;
    //声明旋转次数
    protected int count = 10000;

    //编写顺时针旋转四方格方法
    public void rotateRight(){
        if(states.length == 0){
            return;
        }
        //旋转次数加1
        count++;
        State s = states[count % states.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

    //编写逆时针旋转四方格方法
    public void rotateLeft(){
        //旋转次数减1
        count--;
        State s = states[count % states.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

    //编写四方格旋转状态的内部类
    class State{
        //编写属性：存储四方格各元素的相对位置
        int row0,col0,row1,col1,row2,col2,row3,col3;

        public State() {
        }

        public State(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3) {
            this.row0 = row0;
            this.col0 = col0;
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.row3 = row3;
            this.col3 = col3;
        }

        public int getRow0() {
            return row0;
        }

        public void setRow0(int row0) {
            this.row0 = row0;
        }

        public int getCol0() {
            return col0;
        }

        public void setCol0(int col0) {
            this.col0 = col0;
        }

        public int getRow1() {
            return row1;
        }

        public void setRow1(int row1) {
            this.row1 = row1;
        }

        public int getCol1() {
            return col1;
        }

        public void setCol1(int col1) {
            this.col1 = col1;
        }

        public int getRow2() {
            return row2;
        }

        public void setRow2(int row2) {
            this.row2 = row2;
        }

        public int getCol2() {
            return col2;
        }

        public void setCol2(int col2) {
            this.col2 = col2;
        }

        public int getRow3() {
            return row3;
        }

        public void setRow3(int row3) {
            this.row3 = row3;
        }

        public int getCol3() {
            return col3;
        }

        public void setCol3(int col3) {
            this.col3 = col3;
        }

        @Override
        public String toString() {
            return "State{" +
                    "row0=" + row0 +
                    ", col0=" + col0 +
                    ", row1=" + row1 +
                    ", col1=" + col1 +
                    ", row2=" + row2 +
                    ", col2=" + col2 +
                    ", row3=" + row3 +
                    ", col3=" + col3 +
                    '}';
        }
    }

    //编写左移方法
    public void moveLeft(){
        for (Cell cell : cells) {
            cell.left();
        }
    }
    //编写右移方法
    public void moveRight(){
        for (Cell cell : cells) {
            cell.right();
        }
    }
    //编写下落方法
    public void softDrop(){
        for (Cell cell : cells) {
            cell.drop();
        }
    }

    //编写随机生成四方格的方法
    public static Tetromino randomOne(){
        int num = (int)(Math.random() * 7);
        Tetromino tetromino = null;
        switch (num){
            case 0:
                tetromino = new I();
                break;
            case 1:
                tetromino = new J();
                break;
            case 2:
                tetromino = new L();
                break;
            case 3:
                tetromino = new O();
                break;
            case 4:
                tetromino = new S();
                break;
            case 5:
                tetromino = new T();
                break;
            case 6:
                tetromino = new Z();
                break;
        }
        return tetromino;
    }
}
