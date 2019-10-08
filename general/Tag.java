package general;
import java.util.Random;

public class Tag {

	private int randomNumber;
	
	public int getRandomNumber(int frameSize) {
		return new Random().nextInt(frameSize);
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	 
}
