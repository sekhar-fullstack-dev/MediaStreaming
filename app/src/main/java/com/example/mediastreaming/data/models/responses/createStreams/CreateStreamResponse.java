package com.example.mediastreaming.data.models.responses.createStreams;

import java.io.Serializable;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateStreamResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("data")
    @Expose
    private CreateStreamResponseData createStreamResponseData;
    private final static long serialVersionUID = 4384634999691140182L;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateStreamResponse() {
    }

    /**
     *
     * @param createStreamResponseData
     * @param errorMessage
     * @param message
     * @param status
     */
    public CreateStreamResponse(String status, String message, String errorMessage, CreateStreamResponseData createStreamResponseData) {
        super();
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
        this.createStreamResponseData = createStreamResponseData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CreateStreamResponseData getData() {
        return createStreamResponseData;
    }

    public void setData(CreateStreamResponseData createStreamResponseData) {
        this.createStreamResponseData = createStreamResponseData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CreateStreamResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("errorMessage");
        sb.append('=');
        sb.append(((this.errorMessage == null)?"<null>":this.errorMessage));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.createStreamResponseData == null)?"<null>":this.createStreamResponseData));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.errorMessage == null)? 0 :this.errorMessage.hashCode()));
        result = ((result* 31)+((this.message == null)? 0 :this.message.hashCode()));
        result = ((result* 31)+((this.createStreamResponseData == null)? 0 :this.createStreamResponseData.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CreateStreamResponse)) {
            return false;
        }
        CreateStreamResponse rhs = ((CreateStreamResponse) other);
        return Objects.equals(this.errorMessage, rhs.errorMessage) && Objects.equals(this.message, rhs.message) && Objects.equals(this.createStreamResponseData, rhs.createStreamResponseData) && Objects.equals(this.status, rhs.status);
    }

}


