package domain;

public class OperationResult<T> {
    private String message;
    private boolean success;
    private T data;

    public OperationResult(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static <T> OperationResult<T> success(String message, T data) {
        return new OperationResult<>(message, true, data);
    }

    public static <T> OperationResult<T> failure(String message) {
        return new OperationResult<>(message, false, null);
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }
}