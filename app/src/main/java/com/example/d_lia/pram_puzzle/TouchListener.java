package com.example.d_lia.pram_puzzle;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Random;

public class TouchListener implements View.OnTouchListener {
    private float xDelta;
    private float yDelta;



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getRawX();
        float y = motionEvent.getRawY();


        final double tolerance = Math.sqrt(Math.pow(view.getWidth(), 2) + Math.pow(view.getHeight(), 2)) / 10;

        PuzzlePiece piece = (PuzzlePiece) view;
        if (!piece.canMove) {
            return true;
        }


        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xDelta = x - lParams.leftMargin;
                yDelta = y - lParams.topMargin;
                piece.bringToFront();
                break;
            case MotionEvent.ACTION_MOVE:
                lParams.leftMargin = (int) (x - xDelta);
                lParams.topMargin = (int) (y - yDelta);
                view.setLayoutParams(lParams);
                break;
            case MotionEvent.ACTION_UP:
                int xDiff = Math.abs(piece.xCoord - lParams.leftMargin);
                int yDiff = Math.abs(piece.yCoord - lParams.topMargin);
                if (xDiff <= tolerance && yDiff <= tolerance) {
                    lParams.leftMargin = piece.xCoord;
                    lParams.topMargin = piece.yCoord;
                    piece.setLayoutParams(lParams);
                    piece.canMove = false;
                    sendViewToBack(piece);
                    activity.checkGameOver();
                }else {
                    int xMax = piece.getxMax();
                    int yMax = piece.getyMax();

                    lParams.leftMargin = (int)(xMax*0.1)+(int)(Math.random()*(xMax*0.5));
                    lParams.topMargin = (int)(yMax*0.65)+(int)(Math.random()*(yMax*0.05));
                   /* lParams.topMargin = (int)(xMax*0.1)+(int)(Math.random()*(xMax*0.5));
                    lParams.leftMargin = (int)(yMax*0.65)+(int)(Math.random()*(yMax*0.05));
                   */
                    piece.canMove = true;

                    piece.setLayoutParams(lParams);
                }
                break;

        }
        if(piece.canMove){
            //lParams.leftMargin = (int)x;
            //lParams.topMargin = (int)y;
            /*piece.xCoord = (int)x;
            piece.yCoord = (int)y;*/
        }

        return true;
    }


    public void sendViewToBack(final View child) {
        final ViewGroup parent = (ViewGroup)child.getParent();
        if (null != parent) {
            parent.removeView(child);
            parent.addView(child, 0);
        }
    }
    private PuzzleActivity activity;

    public TouchListener(PuzzleActivity activity) {
        this.activity = activity;
    }
}
