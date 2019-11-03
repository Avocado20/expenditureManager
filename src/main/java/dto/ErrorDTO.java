package dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ErrorDTO to provide human-readable rest error response.
 */
@Data
@Accessors(chain = true)
public class ErrorDTO {

    private int statusCode;
    private String status;
    private String message;
}