package com.example.gamesolitarycelta_v10;

public class GameCelta {

    private static final int[][] tablet_init ={
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},

    };
    private int[][] tablet;
    public static final int zise =7;
    private enum State{
        to_select, select, selected
    }
    private State state;

    public GameCelta(){
        tablet = new int[zise][zise];
        for (int f = 0; f < zise; f++){
            System.arraycopy(tablet_init[f], 0, tablet[f], 0, zise);
            tablet[zise / 2][zise / 2] = 0;   // posición central
        }
        state = State.to_select;

    }
    public int getToken(int i, int j){
        return tablet[i][j];
    }

    private int fSelected;
    private int cSelected;

    public void play(int f,int c){
        if(state == State.to_select){
            this.fSelected = f;
            this.cSelected = c;
            state = State.select;
            System.out.println("origin ["+this.fSelected+"]["+this.cSelected+"]");
        }else if(state == State.select){
            if(checkMovement(this.fSelected,this.cSelected,f,c)){

                tablet[this.fSelected][this.cSelected] = 0;
                tablet[this.fJumped][this.cJumped] = 0;
                tablet[f][c] = 1;
                System.out.println("destino [" + f + "][" + c + "]");
                System.out.println("+ el movimiento es aceptable");
                state = State.to_select;
                if(gameOver()){
                    state=State.selected;
                }
            }else{
                this.fSelected = f;
                this.cSelected = c;
                System.out.println("destino [" + f + "][" + c + "]");
                System.out.println("- movimiento no es permitido");

            }
        }
    }

    private int fJumped;
    private int cJumped;
    public boolean checkMovement(int fOrigin, int cOrigin, int fDestiny, int cDestiny){
        if(tablet[fOrigin][cOrigin] == 0 || tablet[fDestiny][cDestiny] == 1){
            return false;
        } else {
            if ((fOrigin == fDestiny && Math.abs(cOrigin - cDestiny) == 2 )||
                    (cOrigin == cDestiny && Math.abs(fOrigin - fDestiny) == 2) ) {
                fJumped = (fOrigin + fDestiny) / 2;
                cJumped = (cOrigin + cDestiny) / 2;
                if (tablet[fJumped][cJumped] == 1) return true;
            }
            return false;
        }
    }

    private static final int num_movement = 4;
    private static final int[][] displacement = {
            {0, 2},   // Dcha
            {0, -2},   // Izda
            {2, 0},   // Abajo
            {-2, 0}    // Arriba
    };
    public boolean gameOver() {
        for (int f = 0; f < zise; f++){
            for (int c = 0; c < zise; c++){
                if (tablet[f][c] == 1) {
                    for (int k = 0; k < num_movement; k++) {
                        int p = f + displacement[k][0];
                        int q = c + displacement[k][1];
                        System.out.println("posision => "+f+c);
                        System.out.println("movimiento => "+p+q);
                        if (p >= 0 && p < zise && q >= 0 && q < zise && tablet[p][q] == 0 && tablet_init[p][q] == 1){
                            if (checkMovement(f, c, p, q)){
                                System.out.println("entra");
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public int getFile() {
        int count = 0;
        for (int f = 0; f < zise; f++){
            for (int c = 0; c < zise; c++){
                if (tablet[f][c] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
