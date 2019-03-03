package lasermaze.dto;

import java.util.ArrayList;
import java.util.List;

public class PieceLineDto {
    private List<PieceDto> pieceDtos = new ArrayList<>();

    public PieceLineDto() {
    }

    public void add(PieceDto pieceDto) {
        pieceDtos.add(pieceDto);
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }

    public void setPieceDtos(List<PieceDto> pieceDtos) {
        this.pieceDtos = pieceDtos;
    }
}
