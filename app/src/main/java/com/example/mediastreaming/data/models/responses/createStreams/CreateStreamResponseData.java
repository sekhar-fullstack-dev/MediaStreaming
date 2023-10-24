package com.example.mediastreaming.data.models.responses.createStreams;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class CreateStreamResponseData implements Serializable {

    @SerializedName("stream")
    @Expose
    private String stream;
    private final static long serialVersionUID = -3505265731234845264L;

    /**
     * No args constructor for use in serialization
     **/

    /**
     * @param stream
     **/
    public CreateStreamResponseData(String stream) {
        super();
        this.stream = stream;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CreateStreamResponseData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("stream");
        sb.append('=');
        sb.append(((this.stream == null) ? "<null>" : this.stream));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.stream == null) ? 0 : this.stream.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CreateStreamResponseData)) {
            return false;
        }
        CreateStreamResponseData rhs = ((CreateStreamResponseData) other);
        return (Objects.equals(this.stream, rhs.stream));
    }

}
