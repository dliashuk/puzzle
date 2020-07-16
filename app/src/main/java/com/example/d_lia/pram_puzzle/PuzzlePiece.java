package com.example.d_lia.pram_puzzle;

import android.content.Context;

public class PuzzlePiece extends android.support.v7.widget.AppCompatImageView {
    public int xCoord;
    public int yCoord;
    public int pieceWidth;
    public int pieceHeight;
    public boolean canMove = true;
    public int xMax;
    public int yMax;

    public PuzzlePiece(Context context) {
        super(context);
    }

    public PuzzlePiece(Context context,int xMax,int yMax){
        super(context);
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }
}
