package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class ErrorResponse {

    private final String type;

    private final String errorMessage;
}
