package bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private ResponseStatus responseStatus;
    private String message;
    private Object data; // برای خود ابجکی که کار روز انحام شده استفاده میشه

    public Response(ResponseStatus responseStatus, String message) {
        this.responseStatus = responseStatus;
        this.message = message;
    }
}
