import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class w4_04 extends PApplet {

/*
 * Creative Coding
 * Week 4, 04 - sound toy
 * by Indae Hwang and Jon McCormack
 * Copyright (c) 2014 Monash University
 *
 * This sketch is a simple generative sound toy. 
 * It shows how to read and play sound samples in a sketch
 * using the Minim library.
 *
 */

// setup the minim library and audio player
      // import the minim libraries into Processing
Minim minim;             // declare a variable "minim" of type "Minim"
AudioPlayer[] soundFx;   // an array of audio players


float x, y, dx, size, gap;
int selectSound;

public void setup() {
  size(1000, 500);

  // create sound player and load samples
  minim = new Minim(this);
  soundFx = new AudioPlayer[3];  // 3 audio players - 1 for each sound
  soundFx[0] = minim.loadFile("sound01.wav");
  soundFx[1] = minim.loadFile("sound02.wav");
  soundFx[2] = minim.loadFile("sound03.wav");

  // randomly select a sound player and play the sound
  selectSound = (int) random(3);
  soundFx[selectSound].play();

  gap = 0.8f;
  x = random(width); 
  y = 0;
  size = random(10, 500);
  dx = size;

  background(0);
  smooth(8);
}

public void draw() {

  if (frameCount%10 == 0) {
    // every 10 frames
    y = y + dx * gap;
    size *= 0.5f;      // same as: size = size / 2;
    dx = size;
    noStroke();
    fill(0, 10);
    rect(0, 0, width, height);
  }

  if (size < 1) {   
    // if the size has become too small, remap gap between 0.1 and 2, based on the frame number
    gap = map(frameCount%100, 0, 100, 0.1f, 2 );
    x = random(width); 
    y = 0;
    size = random(10, 500);
    dx = size;
    selectSound = (int) random(3);
    soundFx[selectSound].rewind();
    soundFx[selectSound].play();
  }

  // draw the shapes associated with the sound
  noFill();
  stroke(255, 50);
  ellipse(x, y, size, size);
  stroke(255, 10);
  line(x, y, x, height);
}

/*
 * stop
 * Clean up minim when sketch is stopped
 */
public void stop() {
  minim.stop();
  super.stop();
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "w4_04" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
