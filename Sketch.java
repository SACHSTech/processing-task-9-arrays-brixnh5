import processing.core.PApplet;

public class Sketch extends PApplet {

	int[] intCircleX = new int[20];
  int[] intCircleY = new int[20];
  boolean[] ballHideStatus = new boolean[20];

  int intSnowballSpeed = 4;

  float fltPlayerX = 400;
  float fltPlayerY = 400;
  int intLives = 3;

  boolean blnMouseClicked = false;
  boolean blnUpPressed = false;
  boolean blnDownPressed = false;
  boolean blnLeftPressed = false;
  boolean blnRightPressed = false;

  boolean blnIsAlive = true;

  public void settings() {
    // window size
    size(800, 800);
  }

  public void setup() {
    // fill array with random values for snowball coordinates
    for (int i = 0; i < intCircleX.length; i++){
      intCircleX[i] = (int) random(0, 800 - 50);
      intCircleY[i] = (int) random(0, 200);
      ballHideStatus[i] = false;
    }
  }

  public void draw() {
    // check if player is still alive
	  if (blnIsAlive) {

      background(50);

      // checks if each snowball needs to be hidden, if not, the y position is incremented by the speed
      for (int i = 0; i < intCircleY.length; i++) {
        if (!ballHideStatus[i]){
          fill(255);
          ellipse(intCircleX[i], intCircleY[i], 50, 50);
          intCircleY[i] += intSnowballSpeed;
        }
    
        // draws each snowball back at the top if it goes past the window size
        if (intCircleY[i] > height - 25) {
          intCircleY[i] = 0;
        }

        // checks if the player collides with a snowball, a life is removed and the snowball is hidden
        if (dist(fltPlayerX, fltPlayerY, intCircleX[i], intCircleY[i]) <= 37.5 && ballHideStatus[i] == false) {
          ballHideStatus[i] = true;
          intLives--;
        }
        
        // checks if a snowball is clicked, the snowball is hidden
        if (dist(mouseX, mouseY, intCircleX[i], intCircleY[i]) <= 25 && blnMouseClicked) {
            ballHideStatus[i] = true;
        }
      }

      //draw the player
      fill(0, 0, 255);
      ellipse(fltPlayerX, fltPlayerY, 25, 25);

      // keyboard controls for the player, edge detection
      if (blnUpPressed) {
        if (fltPlayerY <= (float)12.5){
          fltPlayerY = (float)12.5;
        }
        else{
          fltPlayerY -= 3;
        }
      }
      if (blnDownPressed) {
        if (fltPlayerY >= 800 - (float)12.5){
          fltPlayerY = 800 - (float)12.5;
        }
        else{
          fltPlayerY += 3;
        }
      }
      if (blnLeftPressed) {
        if (fltPlayerX <= (float)12.5){
          fltPlayerX = (float)12.5;
        }
        else{
          fltPlayerX -= 3;
        }
      }
      if (blnRightPressed) {
        if (fltPlayerX >= 800 - (float)12.5){
          fltPlayerX = 800 - (float)12.5;
        }
        else{
          fltPlayerX += 3;
        }
      }

      // draw lived
      for (int i = 1; i <= intLives; i++) {
        fill(246, 7, 17);
        rect(45 * i, 26, 26, 26);
      }

      // game ends when lives are 0
      if (intLives == 0) {
        blnIsAlive = false;
      }
    }
  }

  // if movement keys are pressed, the player moves in the corresponding direction
  // if up or down keys are pressed, the speed at which snowballs are falling changes
  public void keyPressed() {
    if (keyCode == UP) {
      intSnowballSpeed = 1;
    }

    if (keyCode == DOWN) {
      intSnowballSpeed = 10;
    }

    if (key == 'w') {
      blnUpPressed = true;
    }
    if (key == 'a') {
      blnLeftPressed = true;
    }
    if (key == 's') {
      blnDownPressed = true;
    }
    if (key == 'd') {
      blnRightPressed = true;
    }
  }

  // once a key is released, the boolean values are cahnged back to default
  public void keyReleased() {
    if (keyCode == UP) {
      intSnowballSpeed = 4;
    }
    if (keyCode == DOWN) {
      intSnowballSpeed = 4;
    }
    if (key == 'w') {
      blnUpPressed = false;
    }
    if (key == 'a') {
      blnLeftPressed = false;
    }
    if (key == 's') {
      blnDownPressed = false;
    }
    if (key == 'd') {
      blnRightPressed = false;
    }
  } 

  // boolean value of mouseclick is set to true when use clicks
  public void mousePressed() {
    blnMouseClicked = true;
  }

  // boolean value is reset
  public void mouseReleased() {
    blnMouseClicked = false;
  }
}