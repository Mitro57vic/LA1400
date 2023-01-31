package Filip.Mitrovic;
import robocode.*;

public class Krassbro extends JuniorRobot
{

int moveCounter = 0;
    int moveDirection = 1;
    int moveDistance = 133;

    public void run() {
        setColors(black, orange, yellow);
        while (true) {
			int checkPlayers = others;
			if ( checkPlayers > 20) {
				defense();
				} 
			else if ( checkPlayers < 20) {
				balanced();
				}
			else if ( checkPlayers < 10) {
				attack();
				}
        }
    }

    public void onScannedRobot() {
	
     turnGunTo(scannedAngle);
        if (scannedAngle>100){
        fire(4);
        }
        if (scannedAngle<20){
        fire(8);
        }
        if(scannedAngle<=100){
        fire(1);
        }

        if (scannedDistance < fieldHeight / 4) {
            back(fieldHeight / 2);
			}
        }
    
    public void onHitByBullet() {
        if (hitByBulletAngle != -1) {
            turnRight(hitByBulletAngle);
			turnRight(90);
            ahead(fieldHeight / 2);
        }
    }

    public void onHitWall() {
        if (hitWallAngle != -1) {
           	turnRight(hitWallAngle);
           	back(fieldHeight / 4);
            turnRight(90);
        }
    }

	public void defense() {
        double movement = fieldHeight - robotY;
		int head = heading;
        if (head < 90) {
            ahead((int)movement);
            turnRight(90 - head);
        } else if (head > 90) {
            back((int)movement);
            turnLeft(head - 90);
        } else {
            ahead((int)movement);
        }
        health();
    }

	public void balanced() {
        if (moveCounter % 20 == 0) {
            moveDirection *= -1;
        }
        ahead(moveDistance * moveDirection);
        turnRight(90);
        moveCounter++;
        health();
        moveDistance += 3;
    }

	public void attack() {
		int scannedDist = scannedDistance;
		int scannedBear = scannedBearing;
		if (scannedDist > 0) {
           int angle = scannedBear + heading - gunHeading;
           bearGunTo(angle);
           ahead(scannedDist - 50);
           turnRight(scannedBear);
         }
         else {
            turnGunRight(360);
            ahead(fieldHeight/2);
         }
		 health();
	}

	public void health() {
        if (energy < 20) {
            back(fieldHeight / 2);
            turnRight(180);
            ahead(fieldHeight / 4);
            turnRight(90);
        }
    }
}