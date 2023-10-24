package com.example.mediastreaming.data.models.responses.streams;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveStreamsResponse implements Serializable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("data")
    @Expose
    private List<Streams> data;
    private final static long serialVersionUID = -1934206644519740022L;

    /**
     * No args constructor for use in serialization
     *
     */
    public LiveStreamsResponse() {
    }

    /**
     *
     * @param data
     * @param errorMessage
     * @param message
     * @param error
     */
    public LiveStreamsResponse(String message, String error, String errorMessage, List<Streams> data) {
        super();
        this.message = message;
        this.error = error;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Streams> getData() {
        return data;
    }

    public void setData(List<Streams> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LiveStreamsResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
        sb.append(',');
        sb.append("errorMessage");
        sb.append('=');
        sb.append(((this.errorMessage == null)?"<null>":this.errorMessage));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
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
        result = ((result* 31)+((this.error == null)? 0 :this.error.hashCode()));
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LiveStreamsResponse) == false) {
            return false;
        }
        LiveStreamsResponse rhs = ((LiveStreamsResponse) other);
        return (((((this.errorMessage == rhs.errorMessage)||((this.errorMessage!= null)&&this.errorMessage.equals(rhs.errorMessage)))&&((this.message == rhs.message)||((this.message!= null)&&this.message.equals(rhs.message))))&&((this.error == rhs.error)||((this.error!= null)&&this.error.equals(rhs.error))))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))));
    }

}