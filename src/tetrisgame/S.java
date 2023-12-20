package tetrisgame;

public class S extends Tetromino {
    public S() {
        cells[0] = new Cell(0, 4, Tetris.S);
        cells[1] = new Cell(0, 5, Tetris.S);
        cells[2] = new Cell(1,3, Tetris.S);
        cells[3] = new Cell(1, 4, Tetris.S);

        //共计有两种旋转状态
        states = new State[2];
        //初始化两种状态相对坐标
        states[0] = new State(0, 0, 0, 1, 1, -1, 1, 0);
        states[1] = new State(0, 0, 1, 0, -1, -1, 0, -1);
    }
}
