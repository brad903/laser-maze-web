package lasermaze.domain.game.piece.common;

public enum ImagePath {

    DUMMY("/img/pieces/dummy.png", 0),
    BLACK_KING("/img/pieces/black_king.png", 1),
    BLACK_LASER("/img/pieces/black_laser.png", 2),
    BLACK_SPLITTER("/img/pieces/black_splitter.png", 3),
    BLACK_SQUARE_KNIGHT("/img/pieces/black_squareKnight.png", 4),
    BLACK_TRIANGLE_KNIGHT("/img/pieces/black_triangleKnight.png", 5),

    WHITE_KING("/img/pieces/white_king.png", 6),
    WHITE_LASER("/img/pieces/white_laser.png", 7),
    WHITE_SPLITTER("/img/pieces/white_splitter.png", 8),
    WHITE_SQUARE_KNIGHT("/img/pieces/white_squareKnight.png", 9),
    WHITE_TRIANGLE_KNIGHT("/img/pieces/white_triangleKnight.png", 10);

    private String imagePath;
    private int imageNum;

    ImagePath(String imagePath, int imageNum) {
        this.imagePath = imagePath;
        this.imageNum = imageNum;
    }

    public static ImagePath getImagePath(int imageNum) {
        for (ImagePath value : values()) {
            if (value.imageNum == imageNum) return value;
        }
        throw new IllegalArgumentException();
    }

    public String getImagePath() {
        return imagePath;
    }

    public ImagePath makeEnemy() {
        return getImagePath(imageNum + 5);
    }
}
