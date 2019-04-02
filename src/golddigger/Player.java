
package golddigger;

/**
 * Class contains information about Player
 * @author Duc Nguyen 17974984
 */
public class Player {
    private String name;
    private int score;
    private int life;
    private int[] pos;
    
    public Player(String name)
    {
        this.setName(name);
        this.setScore(0);
        this.setLife(3);
        this.setPos(new int[] {2, GameMain.gameWidth / 2});
    }

    public Player(String name, int score) {
        this.setName(name);
        this.setScore(score);
        this.setLife(3);
        this.setPos(new int[] {2, GameMain.gameWidth / 2});
    }
    
    public void moveLeft()
    {
        int[] tmp = {this.getPos()[0], this.getPos()[1] - 1};
        if (this.setPos(tmp))
        {
            Utils.log("Player moved to (" + this.getPos()[0] + ", " + this.getPos()[1] + ")");
        }
    }
    
    public void moveDown()
    {
        int[] tmp = {this.getPos()[0] + 1, this.getPos()[1]};
        if (this.setPos(tmp))
        {
            Utils.log("Player moved to (" + this.getPos()[0] + ", " + this.getPos()[1] + ")");
        }
    }
    
    public void moveRight()
    {
        int[] tmp = {this.getPos()[0], this.getPos()[1] + 1};
        if (this.setPos(tmp))
        {
            Utils.log("Player moved to (" + this.getPos()[0] + ", " + this.getPos()[1] + ")");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score >= 0)
            this.score = score;
        else
            Utils.log("Score " + score + " is not valid");
    }

    public int[] getPos() {
        return pos;
    }

    public boolean setPos(int[] pos) {
        if (pos[0] >= 0 && pos[1] >= 0 && pos[0] < GameMain.gameHeight && pos[1] < GameMain.gameWidth)
        {
            this.pos = pos;
            return true;
        }
        else
        {
            Utils.log("Position not valid");
            return false;
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        if (life >= 0 && life <= 5)
            this.life = life;
        else
            Utils.log("Life not valid");
    }
    
    @Override
    public String toString()
    {
        return "Name: " + this.getName() + " Lives: " + this.getLife() + " Score: " + this.getScore() + " Position: (" + (this.getPos()[0] + 1) + ", " + (this.getPos()[1] + 1) + ")";
    }
}
