package lasermaze.dto;

public class PieceDto {
    private int direction;
    private String imagePath;

    public PieceDto() {
    }

    public PieceDto(int direction, String imagePath) {
        this.direction = direction;
        this.imagePath = imagePath;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
