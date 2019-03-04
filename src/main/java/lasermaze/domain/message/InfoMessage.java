package lasermaze.domain.message;

import lasermaze.dto.ResponseDto;

public class InfoMessage {
    private String ErrorMessage;

    public InfoMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.ErrorMessage = errorMessage;
    }

    public ResponseDto<InfoMessage> _toResponseDto() {
        return new ResponseDto<>(MessageType.INFO, this);
    }
}
