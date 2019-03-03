package lasermaze.dto;

public class PieceDto {
    private String direction;
    private String imagePath;

    public PieceDto() {
    }

    public PieceDto(String direction, String imagePath) {
        this.direction = direction;
        this.imagePath = imagePath;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
